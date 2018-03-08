package com.ray.library.utils;

import android.os.Build;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class Des3Util {
	private static final String encoding = "UTF-8";
	private static final String ALGORITHM = "DESede";
	public final static String BASE_TABLE = "0123456789ABCDEF";

	/**
	 * 把16进制字符串转换成字节数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) BASE_TABLE.indexOf(c);
		return b;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	public static String encryptToHex(String key, String src) {
		try {
			return bytesToHexString(encrypt(key.getBytes(), src.getBytes(encoding)));
		} catch (Exception ex) {
			return "";
		}
	}

	public static String decryptFromHex(String key, String src) {
		try {
			return new String(decrypt(key.getBytes(), hexStringToByte(src)), encoding);
		} catch (Exception ex) {
			return "";
		}
	}

	// 定义 加密算法,可用 DES,DESede,Blowfish
	// keybyte为加密密钥，长度24字节
	// src为被加密的数据缓冲区
	public static byte[] encrypt(byte[] keybyte, byte[] src) throws Exception {
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM);
		// 加密
		Cipher c1 = Cipher.getInstance(ALGORITHM);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		return c1.doFinal(src);
	}

	// keybyte为加密密钥，长度24字节
	// src为加密后的缓冲区
	public static byte[] decrypt(byte[] keybyte, byte[] src) throws Exception {
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM);
		// 解密
		Cipher c1 = Cipher.getInstance(ALGORITHM);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		return c1.doFinal(src);
	}

	public static String getUniquePsuedoID() {
		String serial = null;

		String m_szDevIDShort = "35" +
				Build.BOARD.length()%10+ Build.BRAND.length()%10 +

				Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +

				Build.DISPLAY.length()%10 + Build.HOST.length()%10 +

				Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +

				Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +

				Build.TAGS.length()%10 + Build.TYPE.length()%10 +

				Build.USER.length()%10 ; //13 位

		try {
			serial = Build.class.getField("SERIAL").get(null).toString();
			//API>=9 使用serial号
			return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
		} catch (Exception exception) {
			//serial需要一个初始化
			serial = "serial"; // 随便一个初始化
		}
		return null;
	}
		//使用硬件信息拼凑出来的15位号码

	public static void main(String[] args) {
		try {
			// 添加新安全算,如果用JCE就要把它添加进去
//			final String keys = "801313824543172280131382";
			final String keys = "mipai_l-try_107987828765";
			// 24字节的密
			String szSrc = "中文测试";
			// 24字节的密
			szSrc = "123456";
			System.out.println("加密前的字符:" + szSrc);
			String s1 = Des3Util.encryptToHex(keys, szSrc);
			System.out.println("加密后的字符:" + s1);
			System.out.println("解密后的字符:" + Des3Util.decryptFromHex(keys, s1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
