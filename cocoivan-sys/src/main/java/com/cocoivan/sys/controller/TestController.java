package com.cocoivan.sys.controller;

import com.cocoivan.account.domain.User;
import com.cocoivan.sys.facade.UserServiceSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Desc TODO
 * @Author ivan
 * @Date 2020/4/5 16:20
 * @Version 1.0
 **/
@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	private UserServiceSys userServiceSys;

	@RequestMapping("getUser")
	public User getUserById(Long uid){
		User userById = userServiceSys.getUserById(uid);
		return userById;
	}
}
