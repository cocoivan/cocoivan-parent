package com.cocoivan.base.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	public static Logger log = LoggerFactory.getLogger(CookieUtil.class);
	public static String SPLIT = "*";
	
    /**
     * 根据cookie的名称获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())){
                    //&& request.getServerName().equals(cookies[i].getDomain())) {
                return cookies[i];
            }
        }
        return null;
    }
 
    public static String getCookieValue(HttpServletRequest request, String name){
        Cookie ck = getCookie(request, name);
        if(ck!=null){
            //return PwdCrypt.decrypt(ck.getValue());
        	return ck.getValue();
        }else{
            return null;
        }
    }
    
    
    
    
    /**
     * 删除cookie
     * @param request
     * @param response
     * @param cookie
     */
    public static void deleteCookie(HttpServletRequest request,
            HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath(getPath(request));
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
    
    
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response,
			String name) {
    	
//    	String ua = request.getHeader("User-Agent");
//    	if (ua != null && ua.indexOf("iPhone") != -1 && ua.indexOf("Safari") != -1) {
    		//ios10/safari bug. 清除不了cookie，只能强制覆盖
    		setCookie(request, response, name, "", 30 * 24 * 3600);
//		}else{
//			setCookie(request, response, name, "", 0);
//		}
	}
 
    public static void clearCookie(HttpServletRequest request,
            HttpServletResponse response, String name, String value) {
        setCookie(request, response, name, value, 0);
    }
    
    /**
     * 设置cookie
     * @param request
     * @param response
     * @param name
     * @param value
     * 默认时间是0x278d00（一个月）
     */
    public static void setCookie(HttpServletRequest request,
    		HttpServletResponse response, String name, String value) {
    	setCookie(request, response, name, value, 0x278d00);
    }
    
    /**
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge 秒
     * 设置cookie，设定时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        	log.info("========cookie setCookie ========");
            String cookieValue = value;
            Cookie cookie = new Cookie(name, value == null ? "" : cookieValue.replaceAll("\r\n", ""));
            cookie.setMaxAge(maxAge);
//            cookie.setPath("/");
            //cookie.setSecure(true);
            cookie.setHttpOnly(true);
            String cookieDomain = "";
            String reqDomain = request.getServerName();
            
            log.info("cookie RequestDomain is {}",reqDomain);
            
            try {
            	String reqHost = request.getHeader("Host");
            	log.info("cookie reqHost is {}",reqHost);
			}
			catch (Exception e) {
				//ignore
			}
            
            if(reqDomain.contains("www.cocoivan.com")){
            	cookieDomain = "www.cocoivan.com";
            }else if(reqDomain.contains("localhost")){
            	cookieDomain = "localhost";
            }else if(reqDomain.contains("47.94.161.224")){
            	cookieDomain = "47.94.161.224";
            }
            
            log.info("cookie Domain is {}",cookieDomain);
            if(StringUtils.isNotBlank(cookieDomain)){
				cookie.setDomain(cookieDomain);
			}
            response.addCookie(cookie);
    }
    
    public static void setCookieWithMaxAge(HttpServletRequest request,
    		HttpServletResponse response, String name, String value, int maxAge) {
    	log.info("======== setCookieWithMaxAge ========");
    	String cookieValue = value;
    	Cookie cookie = new Cookie(name, value == null ? "" : cookieValue.replaceAll("\r\n", ""));
    	cookie.setMaxAge(maxAge);
    	//get Path
    	//cookie.setPath("/");
    	//cookie.setSecure(true);
    	cookie.setHttpOnly(false);
    	response.addCookie(cookie);
    }
    
    public static void setCookieWithMaxAge(HttpServletRequest request,
    		HttpServletResponse response, String name, String value, int maxAge , String path) {
    	log.info("======== setCookieWithMaxAge ========");
    	String cookieValue = value;
    	Cookie cookie = new Cookie(name, value == null ? "" : cookieValue.replaceAll("\r\n", ""));
    	cookie.setMaxAge(maxAge);
    	//get Path
    	//cookie.setPath("/");
    	//cookie.setSecure(true);
    	if(path != null){
    		cookie.setPath(path);
    	}
    	cookie.setHttpOnly(false);
    	response.addCookie(cookie);
    }
 
    public static String getPath(HttpServletRequest request) {
        String path = request.getContextPath();
        return (path == null || path.length() == 0) ? "/" : path;
    }
    public static void main(String[] args) {
         
    }

	

	
}