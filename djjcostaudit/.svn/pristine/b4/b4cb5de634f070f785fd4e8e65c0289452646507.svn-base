package com.cost168.costaudit.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * ClassName: ForwardCotroller 
 * @Description: 仅用于跳转
 * @author lixiang
 * @date 2018-12-5下午2:09:59
 * @Company  广东华联软件科技有限公司
 */
@Controller
public class ForwardCotroller {
	
	@SuppressWarnings("unchecked")
	@RequestMapping("forward_{page}")
	public String forward(@PathVariable String page,HttpServletRequest request){
		if(null != page && page.length() > 0){
			Enumeration<String> parameterNames = request.getParameterNames();
			while(parameterNames.hasMoreElements()){
				String parameterName = parameterNames.nextElement();
				request.setAttribute(parameterName, request.getParameter(parameterName));
			}
			return page.replace("_", "/");
		}
		return null;
	}
}
