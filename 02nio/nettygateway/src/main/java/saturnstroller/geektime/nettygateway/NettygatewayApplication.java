package saturnstroller.geektime.nettygateway;

import saturnstroller.geektime.nettygateway.inbound.HttpInboundServer;

import java.util.Arrays;

public class NettygatewayApplication {
    //本服务端口
    final static int PORT = 8081;
    //后端服务Url
    final static String SERVERS = "http://localhost:8801,http://localhost:8802";

    public static void main(String[] args) {
        try {
            HttpInboundServer server = new HttpInboundServer(PORT, Arrays.asList(SERVERS.split(",")));
            System.out.println("netty服务启动，本机地址为http://localhost:" + PORT + "/,后端服务地址为" + SERVERS);
            server.run();
        }catch (Exception e){
            System.out.println("netty服务启动异常");
            e.printStackTrace();
        }
    }

}
