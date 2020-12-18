package com.cloud.mkk.common.core.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * SpringMVC 参数过滤器Wrapper
 *
 * @author kuangjianyu
 * @date 2020-12-16
 */
public class RequestParameterWrapper extends HttpServletRequestWrapper {

    private Map<String , String[]> params = new HashMap<String, String[]>();
    
    public RequestParameterWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.params.putAll(request.getParameterMap());
    }
    /**
     * 重载一个构造方法
     * @param request
     * @param extendParams
     */
    public RequestParameterWrapper(HttpServletRequest request, Map<String , String[]> extendParams) throws IOException {
        this(request);
        addAllParameters(request, extendParams);
    }
    @Override
    public String getParameter(String name) {
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }
    public void addAllParameters(HttpServletRequest request, Map<String , String[]>otherParams) {
        for(Map.Entry<String , String[]>entry : otherParams.entrySet()) {
    		String pvalue = "";
    		String[] pvalues = null;
			if((entry.getValue() instanceof String[])){
				pvalues = entry.getValue();
				for (int i=0;i<pvalues.length;i++) {
//					pvalue = CryptUtil.unescape(pvalues[i]);
					pvalue = pvalues[i];
					//TODO：增加需要过滤的特殊字符
					pvalues[i] = pvalue;
				}
			}
            addParameter(entry.getKey() , pvalues);
        }
    }
    public void addParameter(String name , Object value) {
        if(value != null) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }
}