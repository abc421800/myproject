package com.cost168.costaudit.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;

import com.cost168.costaudit.pojo.sys.SysUser;

/**
 * 
 * ClassName: ShiroLoginFilter 
 * @Description: TODO
 * @author lixiang
 * @date 2019-1-3下午9:55:01
 * @Company  广东华联软件科技有限公司
 */
public class ShiroLoginFilter extends AdviceFilter {
	
	
	//返回false将中断后续拦截器链的执行
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
    	//System.out.println("====预处理/前置处理");
		HttpServletRequest request = (HttpServletRequest) servletRequest;  
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (null == currentUser && !StringUtils.contains(request.getRequestURI(), "/login")) {
            if (isAjax(request)) {//如果是ajax返回指定数据
            	//response.setStatus(700);
            	WebUtils.toHttp(response).sendError(700); 
            }else{//不是ajax进行重定向处理
            	String url = request.getServletPath();
				if("/home".equals(url) || "/".equals(url) ){
					//response.sendRedirect(xiang.init.Global.SITE_PATH+"login");
					WebUtils.issueRedirect(request, response, "/login"); 
				}else{
					//response.sendRedirect(xiang.init.Global.SITE_PATH+"ajaxLogin");
					WebUtils.issueRedirect(request, response, "/overTime"); 
				}
            }
            return false;
        }
        return true;
    }

    
	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		//System.out.println("====后处理/后置返回处理");
	}

	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)throws Exception {
		//System.out.println("====完成处理/后置最终处理"); 
	}

	//是否ajax请求
	private boolean isAjax(HttpServletRequest request) {  
        String header = request.getHeader("x-requested-with");  
        if (StringUtils.isNotEmpty(header) && StringUtils.equals(header, "XMLHttpRequest")) {  
            return true;  
        }  
        return false;  
    }  

}
