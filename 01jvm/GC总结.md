##### 1、Serial GC
```
//启动参数
-XX:+UseSerialGC
```
串行GC，单线程执行，年轻代和老年代的GC都会触发STW。  
年轻代使用 **mark-copy(标记-复制)** 算法，老年代使用 **mark-sweep-compact(标记-清除-整理)** 算法。  
适合单核CPU，JVM堆内存较小时使用。  
GC log示例:  
![serialGC][serialGCImg]  
其中，Allocation Failure表示触发GC的原因是对象内存分配失败，年轻代没有足够空间存放新对象。  
Times部分中user表示GC线程消耗的时间，sys表示系统调用和系统等待事件消耗的时间，real表示应用暂停的时间。real=user+sys  
例如第一次GC，年轻代空间从279M到了34M，而整个堆内存从279M到了88M，说明年轻代有大约54M的数据转移到了老年代  
##### 2、Parallel GC
```
//启动参数 并指定GC线程数，默认CPU核心数
-XX:+UseParallelGC
-XX:ParallelGCThreads=n 
```
并行GC，GC期间，可以多线程并发清理垃圾，所以暂停时间更短，年轻代和老年代的GC都会触发STW。  
年轻代使用 **mark-copy(标记-复制)** 算法，老年代使用 **mark-sweep-compact(标记-清除-整理)** 算法。  
GC log示例:  
![parallelGC][parallelGCImg]  
其中，年轻代GC的Times部分，real≈(user+sys)/GC线程数。可以发现STW时间相对串行GC大幅缩短了。  
日志最后发生了一次Full GC，Ergonomics表示触发GC的原因是JVM内部环境认为此时可以进行GC。从时间看出老年代的GC也是并行处理的，另外年轻代被清空  
##### 3、CMS GC
```
//启动参数
-XX:+UseConcMarkSweepGC
```
Mostly Concurrent Mark and Sweep Garbage Collector（最大并发-标记-清除垃圾收集器）  
年轻代使用 **mark-copy(标记-复制)** 算法，老年代使用 **mark-sweep(标记-清除)** 算法。  
CMS GC是针对老年代的GC，年轻代的GC使用ParNew GC,即串行GC的多线程优化版本  
为了避免在老年代GC时长时间卡顿，CMS GC采用了两种方式 
 - 不对老年代整理，使用空闲列表(free-lists)的方式管理内存的回收
 - CMS GC是分多个阶段进行的，绝大部分的阶段是可以与应用线程并发执行的  

所以，GC过程中没有明显的应用线程暂停，但是部分过程会触发STW，默认使用的并发线程数是CPU核心数的1/4。CMS GC能够有效减少停顿时间，但是由于在老年代GC后不压缩，会有内存碎片的问题，另外在某些情况下特别是堆内存较大的情况下会造成不可预测的停顿时间  
另外，在老年代的GC过程中，可能会伴随多次年轻代的GC  
CMS GC的几个阶段： 
 - 阶段1：初始标记（Initial Mark）  
触发STW，标记对象为跟对象及被年轻代存活对象所引用的对象
 - 阶段2：并发标记（Concurrent Mark）  
与应用线程并发执行，并发标记老年代存活对象，由于在并发标记过程中引用关系可能发生变化，JVM通过 **卡片标记(Card Marking)** 方式将发生改变的区域标记为“脏”区  
 - 阶段3：并发预清理（Concurrent Preclean）  
与应用线程并发执行，统计前一阶段的脏对象，card清空
 - 阶段4：可取消的并发预清理（Concurrent Abortable Preclean）  
最终标记前的准备工作  
 - 阶段5：最终标记（Final Remark）
触发STW，JVM会尝试在年轻代尽可能为空的情况下执行该阶段，避免连续触发多次STW，从GC log中可以看到在最终标记前发生过一次年轻代的GC。该阶段完成后老年代所有存活对象都被标记了  
 - 阶段6：并发清除（Concurrent Sweep）  
该阶段删除不再使用的对象，回收内存
 - 阶段7：并发重置（Concurrent Reset）  
重置CMS算法相关的内部数据，为下一次GC循环做准备  

GC log示例:  
![CMSGC][CMSGCImg]  
##### 4、G1 GC  
```
//启动参数
-XX:+UseG1GC
//GC启动的阈值，默认堆内存的45%
-XX:+InitiatingHeapOccupancyPercent
//GC停止回收的阈值，默认堆内存的5%
-XX:G1HeapWastePercent
//预期每次GC的停顿时间，默认200毫秒
-XX:MaxGCPauseMillis=50
```  
堆不再分成年轻代和老年代，而是划分为多个（通常是2048个）可以存放对象的小块堆区域(smaller heap regions)，每一个小块可能一会是Eden区，一会是存活区，一会是Old区。这样划分是为了G1不必每次都回收整个堆内存，而是以增量的方式处理，每次只处理一部分内存块。优先处理垃圾最多的小块，即Garbage First  
G1 GC适合大内存，需要较低延迟的场景
G1 GC的几个阶段：  
 - 阶段1：年轻代模式转移暂停（Evacuation Pause - young）  
应用刚启动时，G1处在初始的fully-young模式，当年轻代空间用满后，应用线程会被暂停，年轻代内存块中的存活对象被拷贝到存活区。如果还没有存活区，则任意选择一部分空闲的内存块作为存活区  
 - 阶段2：并发标记（Concurrent Marking）  
G1 GC的并发标记阶段与CMS基本一致，G1的并发标记通过Snapshot-At-The-Beginning(起始快照) 的方式，在标记阶段开始时记下所有的存活对象。G1的并发标记由多个阶段构成  
    1. 阶段2.1 初始标记（Initial Mark）  
在CMS是需要触发STW的，但在G1中通常在转移暂停同时处理，所以开销很小  
    2. 阶段2.2 Root区扫描（Root Region Scan）  
此阶段标记所有从"根区域"可达的存活对象。根区域包括：非空的区域，以及在标记过程中不得不收集的区域  
    3. 阶段2.3 并发标记（Concurrent Mark）  
此阶段和CMS的并发标记阶段非常类似：只遍历对象图，并在一个特殊的位图中标记能访问到的对象。为了确保标记开始时的快照准确性，所有应用线程并发对对象图执行引用更新，G1要求放弃前面阶段为了标记目的而引用的过时引用。  
    4. 阶段2.4 再次标记（Remark）  
触发STW，类似CMS，这一阶段也会执行某些额外的清理，如引用处理或者类卸载  
    5. 阶段2.5 清理（Cleanup）  
触发STW，最后这个清理阶段为即将到来的转移阶段做准备，统计小堆块中所有存活的对象，并将小堆块进行排序，以提升GC的效率。此阶段也为下一次标记执行必需的所有整理工作(house-keeping activities)：维护并发标记的内部状态。所有不包含存活对象的小堆块在此阶段都被回收了。有一部分任务是并发的：例如空堆区的回收，还有大部分的存活率计算。  
 - 阶段3：混合模式转移暂停（Evacuation Pause - mixed）  
清理年轻代和老年代。混合模式的转移暂停不一定紧跟并发标记阶段。有很多规则和历史数据会影响混合模式的启动时机。比如，假若在老年代中可以并发地腾出很多的小堆块，就没有必要启动混合模式。因此，在并发标记与混合转移暂停之间，很可能会存在多次young模式的转移暂停  
GC log示例:  
![G1GC][G1GCImg]
