package com.cost168.costaudit.shiro;

import org.apache.shiro.SecurityUtils;

import com.cost168.costaudit.pojo.sys.SysUser;

/**
 * 
 * ClassName: shiroUtil 
 * @Description: TODO
 * @author lixiang
 * @date 2019-1-4上午9:27:25
 * @Company  广东华联软件科技有限公司
 */
public class shiroUtil {
	
	
	private static volatile shiroUtil instance;

    private shiroUtil() {}

    public static shiroUtil getInstance() {
        if (instance == null) {
            synchronized (shiroUtil.class) {
                if (instance == null) {
                    instance = new shiroUtil();
                }
            }
        }
        return instance;
    }
	
	public SysUser currentUser(){
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		return currentUser;
	}

}
