package com.cocoivan.base.support;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse extends HashMap<String, Object>{
	
	private static final long serialVersionUID = -5162873083105976921L;

	public static ApiResponse fail(){
		ApiResponse apiResult = new ApiResponse();
		apiResult.put("success", false);
		return apiResult;
	}
	
	public static ApiResponse success(){
		ApiResponse apiResult = new ApiResponse();
		apiResult.put("success", true);
		return apiResult;
	}
	
	public ApiResponse and(String key, Object object){
		this.put(key, object);
		return this;
	}
	
	public ApiResponse error(String msg) {
		this.put("msg", msg);
		return this;	
	}
	
	public ApiResponse error(ApiError error) {
		this.put("msg", error.getMsg());
		this.put("code", error.getCode());
		return this;	
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public static void main(String[] args) {
		ApiResponse and = ApiResponse.fail().and("msg", "xxx");
		
	}

	@SuppressWarnings("unchecked")
	public ApiResponse data(Map<String, Object> data) {
		if (this.containsKey("data")) {
			Map<String, Object> object = (Map<String, Object>) this.get("data");
			object.putAll(data);
		}else{
			this.put("data", data);
		}
		return this;
	}

	
}
