package com.cocoivan.account.converter;

import com.cocoivan.account.domain.User;
import com.cocoivan.account.model.UserVO;

public class UserConverter {

	public static UserVO toUserVO(User source) {
		if (source == null) return  null;
		UserVO target = new UserVO();
		target.setUid(source.getUid());
		target.setUserType(source.getUserType());
		target.setName(source.getName());
		target.setAvatar(source.getAvatar());
		return target;
	}

	public static User toUser(UserVO source) {
		if (source == null) return  null;
		User target = new User();
		target.setUid(source.getUid());
		target.setUserType(source.getUserType());
		target.setName(source.getName());
		target.setAvatar(source.getAvatar());
		return target;
	}
}
