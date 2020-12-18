package com.cloud.mkk.common.core.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * SpringMVC 参数过滤器
 *
 * @author kuangjianyu
 * @date 2020-12-16
 */
public class RequestParameterFilter implements Filter {
	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
//		String requestUrl = httpRequest.getRequestURI().replaceFirst(httpRequest.getContextPath()+"/", "");
//		String urlType = requestUrl.substring(requestUrl.lastIndexOf(".")+1);
//		if(!"json".equalsIgnoreCase(urlType)&&!"action".equalsIgnoreCase(urlType)){
//			response.getWriter().print("<script>alert('非法请求！');</script>");
//			return;
//		}
		Map<String,String[]> paramMap = new HashMap<String,String[]>(request.getParameterMap());
		RequestParameterWrapper requestWrapper = new RequestParameterWrapper(httpRequest, paramMap);
		chain.doFilter(requestWrapper, response);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
