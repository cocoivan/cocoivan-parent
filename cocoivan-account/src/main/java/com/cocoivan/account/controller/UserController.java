package com.cocoivan.account.controller;

import com.cocoivan.account.domain.User;
import com.cocoivan.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserController
 * @Desc TODO
 * @Author ivan
 * @Date 2020/4/5 1:27
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "getUserById",method = RequestMethod.GET)
	public User getUserById(Long userId) {
		User userById = userService.getUserById(userId);
		return userById;
	}

	@RequestMapping(value = "getUserByAccount",method = RequestMethod.GET)
	public User getUserByAccount(String account) {
		User userById = userService.getUserByAccount(account);
		return userById;
	}

	@RequestMapping(value = "writeLoginCookie",method = RequestMethod.GET)
	public String getUserById(String phone, long uid,
			HttpServletRequest req, HttpServletResponse res) {
		String cookie = userService.writeLoginCookie(phone, uid, req, res);
		return cookie;
	}
}
