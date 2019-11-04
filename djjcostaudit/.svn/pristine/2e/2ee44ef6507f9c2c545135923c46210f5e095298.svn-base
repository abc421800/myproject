package com.cost168.costaudit.shiro;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ShiroExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		
		System.out.println("==============异常开始=============");  
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url  
        if(ex instanceof UnauthorizedException){  
            ModelAndView mv = new ModelAndView("main/overtimelogin");  
            String requestedWith = request.getHeader("X-Requested-With");
            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//�����ajax����ָ�����
            	response.setStatus(701);
            } else {//不是ajax进行重定向处理
            	try {
            		String url = request.getServletPath();
    				if("/index.jsp".equals(url)){
    					//response.sendRedirect(xiang.init.Global.SITE_PATH+"login");
    					WebUtils.issueRedirect(request, response, "/login"); 
    				}else{
    					//response.sendRedirect(xiang.init.Global.SITE_PATH+"ajaxLogin");
    					WebUtils.issueRedirect(request, response, "/noAuthority"); 
    				}
            	} catch (IOException e) {
            		e.printStackTrace();
            	}
            }
            return mv;  
        }  
        ex.printStackTrace();  
        System.out.println("==============异常结束=============");  
        ModelAndView mv = new ModelAndView("main/overtimelogin");  
        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));  
        return mv; 
	}

}
