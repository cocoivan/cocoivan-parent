package com.cocoivan.base.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

/**
 * BlowFish 算法用来加密 64Bit 长度的字符串 用 BlowFish 算法加密信息,需要两个过程: 1.密钥预处理 2.信息加密
 */

public class BlowFishUtil {

	public static Logger log = LoggerFactory.getLogger(BlowFishUtil.class);

	// 密钥
	public static final String ENCRYPT_KEY = "ZfosZD0gQpb%";
	//public static final String ENCRYPT_KEY = "WkoxWT0kJik=";

	// 初始化向量
	public static final String INITIALIZATION_VECTOR = "zpGFeC6X";
	//public static final String INITIALIZATION_VECTOR = "cnBHdE9F";

	// 转换模式
	public static final String TRANSFORMATION = "Blowfish/CBC/PKCS5Padding";

	// 密钥算法名称
	public static final String BLOWFISH = "Blowfish";

	/**
	 * 
	 * 加密
	 * @param key 密钥
	 * @param text 加密文本
	 * @param initializationVector 初始化向量
	 */
	public static String encrypt(String key, String text, String initializationVector)

			throws Exception {

		// 根据给定的字节数组构造一个密钥 Blowfish-与给定的密钥内容相关联的密钥算法的名称
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), BLOWFISH);

		// 使用 initializationVector 中的字节作为 IV 来构造一个 IvParameterSpec 对象
		AlgorithmParameterSpec iv = new IvParameterSpec(initializationVector.getBytes());

		// 返回实现指定转换的 Cipher 对象
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);

		// 用密钥和随机源初始化此 Cipher
		cipher.init(Cipher.ENCRYPT_MODE, sksSpec, iv);

		// 加密文本
		byte[] encrypted = cipher.doFinal(text.getBytes());

		return new String(Hex.encodeHex(encrypted));

	}

	/**
	 * 
	 * 解密
	 * @param key 密钥
	 * @param text 加密文本
	 * @param initializationVector 初始化向量
	 */
	public static String decrypt(String key, String text, String initializationVector)
			throws Exception {

		byte[] encrypted = null;

		try {
			encrypted = Hex.decodeHex(text.toCharArray());
		}
		catch (Exception e) {
			log.error("Hex.decodeHex error", e);
		}

		SecretKeySpec skeSpect = new SecretKeySpec(key.getBytes(), BLOWFISH);

		AlgorithmParameterSpec iv = new IvParameterSpec(initializationVector.getBytes());

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);

		cipher.init(Cipher.DECRYPT_MODE, skeSpect, iv);

		byte[] decrypted = cipher.doFinal(encrypted);

		return new String(decrypted);

	}

	/**
	 * 
	 * Base64字符解码
	 * @param base64String 被解码字符
	 * @return 解码后字符
	 */

	public static String base64Decoder(String base64String) {

		if (StringUtils.isEmpty(base64String)) {
			return base64String;
		}
		else {
			return new String(Base64.decode(base64String));
		}
	}

	/**
	 * Base64字符编码
	 * @param sourceString
	 *            -- 字符
	 * @return 编码后字符
	 */

	public static String base64Encoder(String sourceString) {

		if (StringUtils.isEmpty(sourceString)) {
			return sourceString;
		}
		else {
			return Base64.encode(sourceString.getBytes());
		}

	}

	public static String encryptCookie(Long uid, String name, String expireDate, String cookieMd5) {
		String ENCRYPT_STR = "";
		try {
			ENCRYPT_STR = uid + CookieUtil.SPLIT + name + CookieUtil.SPLIT + expireDate
					+ CookieUtil.SPLIT + cookieMd5;
			return base64Encoder(encrypt(ENCRYPT_KEY, ENCRYPT_STR, INITIALIZATION_VECTOR));
		}
		catch (Exception e) {
			log.error(String.format("======== encryptCookie error:%s ========", ENCRYPT_STR), e);
		}
		return "";
	}

	public static String decryptCookie(String encryptStr) {
		try {
			return decrypt(ENCRYPT_KEY, base64Decoder(encryptStr), INITIALIZATION_VECTOR);
		}
		catch (Exception e) {
			e.printStackTrace();
			// ignore
			log.warn("======== decryptCookie error:{} ========", encryptStr);
		}
		return "";
	}

	public static String encryptShare(Long uid, String expireDate) {
		try {
			String ENCRYPT_STR = uid + CookieUtil.SPLIT + expireDate;
			return base64Encoder(encrypt(ENCRYPT_KEY, ENCRYPT_STR, INITIALIZATION_VECTOR));
		}
		catch (Exception e) {
			log.error("encryptShare error", e);
		}
		return "";
	}

	public static String decryptShare(String encryptStr) {
		try {
			return decrypt(ENCRYPT_KEY, base64Decoder(encryptStr), INITIALIZATION_VECTOR);
		}
		catch (Exception e) {
			log.warn("decryptShare error: " + encryptStr , e);
		}
		return "";
	}
	

}