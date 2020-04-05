package com.cocoivan.account.service.impl;

import com.cocoivan.account.domain.User;
import com.cocoivan.account.domain.UserExample;
import com.cocoivan.account.mapper.UserMapper;
import com.cocoivan.account.service.UserService;
import com.cocoivan.base.util.BlowFishUtil;
import com.cocoivan.base.util.CookieUtil;
import com.cocoivan.base.util.DateUtils;
import com.cocoivan.base.util.Md5Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Value(value = "${security_cookie_name}")
	private String security_cookie_name;

	@Resource
	private UserMapper userMapper;

	@Cacheable(value = "common", key = "'user_'+#uid", unless = "#uid == null")
	public User getUserById (Long uid) {
		return userMapper.selectByPrimaryKey(uid);
	}

	@Cacheable(value = "common", key = "'user_'+#account", unless = "#account == null")
	public User getUserByAccount (String account) {
		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(account);
		List<User> users = userMapper.selectByExample(example);
		if (users.size() > 0) return users.get(0);
		return null;
	}

	/**
	 *
	 * @param phone  -- 手机号
	 * @param uid
	 * @param req
	 * @param res
	 * @return
	 */
	public String writeLoginCookie(String phone, long uid,
			HttpServletRequest req, HttpServletResponse res) {
		// 生成cookie
		//获取password,方便日后校验
		User user = userMapper.selectByPrimaryKey(uid);
		if(user == null) {
			return "";
		}
		String password= user.getPassword();
		if(password==null) {
			password="";
		}
		Date date = new Date();
		Date expireDate = DateUtils.addDay(date, 30);
		String expireStr = DateUtils.dateToString(expireDate);
		String md5_cookie = Md5Util.getMd5Cookie(phone, expireStr);
		String usercookie = BlowFishUtil
				.encryptCookie(uid, phone+ CookieUtil.SPLIT+password, expireStr,
				md5_cookie);
		CookieUtil.setCookie(req, res, security_cookie_name, usercookie);
		return usercookie;
	}

}
