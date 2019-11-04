package com.cost168.costaudit.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;






/**
 * 
 * ClassName: SystemLogoutFilter 
 * @Description: TODO
 * @author lixiang
 * @date 2019-1-3下午9:55:24
 * @Company  广东华联软件科技有限公司
 */
public class SystemLogoutFilter extends LogoutFilter {
	
	private static Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);
			
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)throws Exception {
		// 在这里执行退出系统前需要清空的数据
		Subject subject = getSubject(request, response);
		String redirectUrl = getRedirectUrl(request, response, subject);
		try {
			subject.logout();
			if (logger.isDebugEnabled()) {
				logger.debug("用户 注销成功.");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("用户 注销出现异常.");
			}
			e.printStackTrace();
		}
		issueRedirect(request, response, redirectUrl);
		// 返回false表示不执行后续的过滤器，直接返回跳转到登录页面
		return false;
	}
}