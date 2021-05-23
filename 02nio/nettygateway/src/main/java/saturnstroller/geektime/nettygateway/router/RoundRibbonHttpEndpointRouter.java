package saturnstroller.geektime.nettygateway.router;

import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class RoundRibbonHttpEndpointRouter implements HttpEndpointRouter {
    static LongAdder count = new LongAdder();

    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        //轮询
        count.increment();
        long times = count.longValue();
        return urls.get((int)(times%size));
    }
}
