package com.cocoivan.sys.facade;

import com.cocoivan.account.domain.User;
import com.cocoivan.account.service.UserService;
import com.cocoivan.sys.fallback.UserServiceFallBackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserServiceSys
 * @Desc TODO
 * @Author ivan
 * @Date 2020/4/5 16:18
 * @Version 1.0
 **/
@FeignClient(name = "cocoivan-account",fallbackFactory = UserServiceFallBackFactory.class)
public interface UserServiceSys extends UserService {

	@RequestMapping(value = "/user/getUserById",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	User getUserById (@RequestParam("userId") Long uid);

	@RequestMapping(value = "/user/getUserByAccount",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	User getUserByAccount (@RequestParam("account") String account);

	/**
	 *
	 * @param phone  -- 手机号
	 * @param uid
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/user/writeLoginCookie",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	String writeLoginCookie(@RequestParam("phone") String phone, @RequestParam("uid") long uid,
			@RequestParam("req") HttpServletRequest req, @RequestParam("res") HttpServletResponse res);
}
