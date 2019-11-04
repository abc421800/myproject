package com.cost168.costaudit.shiro;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * ClassName: MyCredentialsMatcher 
 * @Description: TODO
 * @author lixiang
 * @date 2019-1-3下午9:54:35
 * @Company  广东华联软件科技有限公司
 */
public class MyCredentialsMatcher implements CredentialsMatcher {

	private static final Logger logger = LoggerFactory.getLogger(MyCredentialsMatcher.class);
			

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		
		String password = null;// 明文

		String password1 = null;// 数据库密文
		
		String password2 = null;//明文加密后的密文

		password = new String((char[]) token.getCredentials());//获取明文
		
		
		password1 = (String) info.getCredentials();//获取密文

		if(password.length()>30){
			password2=password;
		}else{
			password2 = DigestUtils.md5Hex(password);//加密明文
		}

		//校验密码
		if (password1 != null && password1.equals(password2)) {
			if (logger.isDebugEnabled()) {
				logger.debug("用户["+token.getPrincipal()+"]密码与数据库匹配正确.");
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("用户["+token.getPrincipal()+"]密码与数据库不匹配.");
			}
			return false;
		}
	}
}
