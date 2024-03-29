package com.cost168.costaudit.shiro;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cost168.costaudit.pojo.sys.SysUser;
import com.cost168.costaudit.service.sys.SysUserService;

/**
 * 
 * ClassName: AuthcRealm 
 * @Description: TODO
 * @author lixiang
 * @date 2019-1-3下午9:54:19
 * @Company  广东华联软件科技有限公司
 */
public class AuthcRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;

	private static final Logger logger = LoggerFactory.getLogger(AuthcRealm.class);
			
	@Autowired 
	HttpServletRequest request; 
	
	@Override
	public String getName() {
		return "authcRealm";
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
		 UsernamePasswordToken token = (UsernamePasswordToken) at;
		 // 通过表单接收的用户名 
		 String account  = token.getUsername();
	     if(account!=null && !"".equals(account)){
	    	 SysUser user = sysUserService.getUserByAccount(account);  
             if (user != null) {  
            	 return new SimpleAuthenticationInfo(user, user.getPassword(), getName());  
             }  
	     }
	     return null;
	}

	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
			
			// 获取认证成功后的身份信息
			SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
			SimpleAuthorizationInfo simpleAuthorizationInfo = null;
		try {
			// 授权信息体
			List<String> permissionList = null;
			List<String> roleList = new ArrayList<String>();
			if (logger.isDebugEnabled()) {
				logger.debug("正在对用户["+currentUser.getAccount()+"]授权...");
			}
			simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			//获取权限信息
			//String  permissionStr= sysUserService.allPermByUserId(currentUser.getAccount());
			String permissionStr="";
			String per=(String) request.getAttribute("permissionStr");
			if(null==per || "".equals(per)){
				permissionStr= sysUserService.allPermByUserId(currentUser.getAccount());
				request.setAttribute("permissionStr", permissionStr);  
			}else{
				permissionStr=per;
			}
			if(permissionStr!=null && !"".equals(permissionStr)){
				permissionList=Arrays.asList(permissionStr.split(","));
			}
			//设置权限
			logger.debug("含有的权限集合:["+permissionList+"]");
			simpleAuthorizationInfo.addStringPermissions(permissionList);
			// 获取角色信息
			//roleList = sysUserService.allRoleByUserId(account);
			//设置角色
			simpleAuthorizationInfo.addRoles(roleList);
			if (logger.isDebugEnabled()) {
				logger.debug("对用户["+currentUser.getAccount()+"]授权正常结束.");
			}
		}catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("对用户["+currentUser.getAccount()+"]授权失败.");
			}
			e.printStackTrace();
		}
		return simpleAuthorizationInfo;
	}

}
