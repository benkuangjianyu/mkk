package com.cloud.mkk.common.core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloud.mkk.common.core.constant.CommonConstants;
import com.cloud.mkk.common.core.util.RedisUtil;
import com.cloud.mkk.common.core.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SpringMvc 拦截器
 *
 * @author kuangjianyu
 * @date 2020-12-16
 */
@Slf4j
public class SpringMvcInterceptor implements HandlerInterceptor {

	/*** 执行时机:在我们编写的处理器(控制器)工作之前执行 xxx 任务. * 使用场景:一般进行用户身份校验,看用户是否具有某些权限.
     ** 方法返回值: * true:不进行拦截,执行完该拦截器的工作之后,会进入到下一个拦截器或者处理器;
     ** false:进行拦截,不放行,后面的拦截器或者处理器都不能工作了.
     ** 执行顺序:首先执行该方法,该方法的执行顺序与注册顺序正相关,注册的早,就执行的 早!
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String requestUrl = request.getRequestURI().replaceFirst(request.getContextPath()+"/", "");
    	long startTime = System.currentTimeMillis();
    	request.setAttribute(CommonConstants.SPRINGMVC_INTERCEPTOR_REQUEST_START_TIME_STRING, startTime);
		log.info("<st>调用方法： "+requestUrl + "，ip="+ WebUtils.getIPAddr(request));
		return true;
    }

    /** modelAndView:来自于处理器适配器从 Handler 身上解析出来的对象.
     * 执行时机:是在处理器适配器已经处理完我们自己编写的处理器/控制器之后,返回 ModelAndView 之前工作的.
     * 使用场景:一般是对 ModelAndView 进行进一步的统一的修改/处理.
     ** 执行顺序:执行顺序与注册顺序逆相关,注册的越早,执行的越晚!
	 * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /*** 执行时机: 当 ModelAndView 成功的送达 DispatcherServlet 后,开始执行该方法. * 使用场景:一般在该方法中对整个请求过程中产生的异常信息进行整理收集,或者进 行日志的采集.
     ** 执行顺序:执行顺序与注册顺序逆相关,注册的越早,执行的越晚!
     ** 注意:afterCompletion 方法的执行与否,取决于 preHandle()是否成功的执行,且返回值 为 true!*/
    @Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		long startTime = (Long)request.getAttribute(CommonConstants.SPRINGMVC_INTERCEPTOR_REQUEST_START_TIME_STRING);
		String requestUrl = request.getRequestURI().replaceFirst(request.getContextPath()+"/", "");
		log.info("<ed>调用方法： "+requestUrl + "，ip="+ WebUtils.getIPAddr(request)+"，执行时间："+(System.currentTimeMillis()-startTime)+" 毫秒");
    }
}