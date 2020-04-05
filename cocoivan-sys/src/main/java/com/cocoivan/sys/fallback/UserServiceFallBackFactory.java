package com.cocoivan.sys.fallback;

import com.cocoivan.account.domain.User;
import com.cocoivan.account.service.UserService;
import com.cocoivan.sys.facade.UserServiceSys;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserServiceFallBackFactory
 * @Desc TODO
 * @Author ivan
 * @Date 2020/4/5 17:05
 * @Version 1.0
 **/
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {

	@Override
	public UserService create(Throwable throwable) {
		return new UserServiceSys() {

			@Override
			public User getUserById(Long uid) {
				User user = new User();
				user.setUid(-1L);
				user.setAccount("异常");
				return user;
			}

			@Override
			public User getUserByAccount(String account) {
				User user = new User();
				user.setUid(-1L);
				user.setAccount("异常");
				return user;
			}

			@Override
			public String writeLoginCookie(String phone, long uid, HttpServletRequest req,
					HttpServletResponse res) {
				return "";
			}
		};
	}
}
