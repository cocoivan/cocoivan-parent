package com.cocoivan.account.service;

import com.cocoivan.account.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

	User getUserById (Long uid);

	User getUserByAccount (String account);

	/**
	 *
	 * @param phone  -- 手机号
	 * @param uid
	 * @param req
	 * @param res
	 * @return
	 */
	String writeLoginCookie(String phone, long uid,
			HttpServletRequest req, HttpServletResponse res);

}
