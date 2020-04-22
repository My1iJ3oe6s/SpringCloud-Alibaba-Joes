package joes.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 过滤器没有直接的方式来访问对方。 它们可以使用RequestContext共享状态，
 * 这是一个类似Map的结构，具有一些显式访问器方法用于被认为是Zuul的原语，内部是使用ThreadLocal实现的，有兴趣的同学可以看下源码。
 */
@Component
public class BaseFilter extends ZuulFilter {

    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * pre：     在请求被路由之前调用
     * route：   在路由请求时候被调用
     * error：   处理请求时发生错误时被调用
     * post：    在route和error过滤器之后被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
        if (count.get() % 2 == 0 && count.compareAndSet(count.get(), (int)(count.get()) + 1)) {
            // 如果请求的参数不为空，且值为chhliu时，则通过
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            try {
                throw new Exception("任务繁忙");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else if (count.compareAndSet(count.get(), (int)(count.get()) + 1)){
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
        return null;
    }

}
