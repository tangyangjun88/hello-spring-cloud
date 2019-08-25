package com.tyj.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends ZuulFilter {
    private  static  final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
    /**
     * 过滤类型
     * 固定值：
     * pre：路由之前调用
     * routing：路由之时调用
     * post：路由之后调用
     * error:发送错误时调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否需要过滤，这里为true，需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤的具体业务代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("{}>>>{}",request.getMethod(),request.getRequestURI().toString());
        String token = request.getParameter("token");
        if(token==null){
            logger.warn("token is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            HttpServletResponse response = requestContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            try {
                response.getWriter().write("非法请求");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
