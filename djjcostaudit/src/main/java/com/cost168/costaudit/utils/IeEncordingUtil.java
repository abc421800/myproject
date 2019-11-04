package com.cost168.costaudit.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 解决IE浏览器下载excel模板文件名乱码问题
 * @author: ZYL
 * @created: 2019-07-01
 */
public class IeEncordingUtil {
    /**
     * 判断是否是IE浏览器
     *
     * @param request
     * @return
     */
    public static boolean isMSBrowser(HttpServletRequest request) {
        String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)) {
                return true;
            }
        }
        return false;
    }
}
