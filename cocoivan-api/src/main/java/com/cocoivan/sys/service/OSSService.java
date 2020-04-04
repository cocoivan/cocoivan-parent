package com.cocoivan.sys.service;

import java.io.InputStream;

public interface OSSService {

	boolean checkIfFileExists(String bucketName, String key);

	String uploadImg(String bucketName,String key, InputStream resources, long length , String contentType);
	
}
	
	

