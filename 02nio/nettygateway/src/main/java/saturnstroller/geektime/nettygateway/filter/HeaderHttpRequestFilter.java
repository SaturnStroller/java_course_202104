package saturnstroller.geektime.nettygateway.filter;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import saturnstroller.geektime.nettygateway.util.SignUtil;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx){
        try {
            /**
             * 请求验签
             */
            if (HttpMethod.POST.equals(fullRequest.method())) {
                String requestBody = fullRequest.content().toString(StandardCharsets.UTF_8);
                System.out.println("原始请求报文体：" + requestBody);

                String sign = fullRequest.headers().get("sign");
                System.out.println("原始请求报文签名sign:" + sign);

                if (!SignUtil.checkSignForPost(JSON.parseObject(requestBody), sign)) {
                    System.out.println("验签失败");
                } else {
                    System.out.println("验签成功");
                }
            } else if (HttpMethod.GET.equals(fullRequest.method())) {
                String uri = fullRequest.uri();
                String text = uri.substring(uri.indexOf("?")+1);
                text = URLDecoder.decode(text,"UTF-8");
                System.out.println("原始请求报文体：" + text);

                String sign = fullRequest.headers().get("sign");
                System.out.println("原始请求报文签名sign:" + sign);

                if (!SignUtil.checkSignForGet(text, sign)) {
                    System.out.println("验签失败");
                } else {
                    System.out.println("验签成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
