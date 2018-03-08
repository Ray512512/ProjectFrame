package com.ray.library.utils;


import com.ray.library.common.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.ray.library.utils.SPUtils.getCommonSp;


public class MD5Util {

	private static final String KEY = "mipai_l-try_107987828765";
		/**
		 * 进行MD5加密
		 * 
		 * @param info
		 *            要加密的信息
		 * @return String 加密后的字符串
		 */
		private static String encryptToMD5(String info) {
			byte[] digesta = null;
			try {
				// 得到一个md5的消息摘要
				MessageDigest alga = MessageDigest.getInstance("MD5");
				// 添加要进行计算摘要的信息
				alga.update(info.getBytes());
				// 得到该摘要
				digesta = alga.digest();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			// 将摘要转为字符串
			if(null != digesta){
				return byte2hex(digesta);
			}else{
				return "";
			}
		}
		
		/**
		 * 将二进制转化为16进制字符串
		 * 
		 * @param b
		 *            二进制字节数组
		 * @return String
		 */
		private static String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1) {
					hs = hs + "0" + stmp;
				} else {
					hs = hs + stmp;
				}
			}
			return hs.toUpperCase();
		}

		public static String encryptToMD5(String id, String password){

			String passWord = Des3Util.decryptFromHex(KEY, password);
			String clientId = passWord.split(",")[1];
			String key = passWord.split(",")[0];
			String result =  clientId +","+ id +","+ System.currentTimeMillis()/1000;
			result = result + ","+ encryptToMD5(result);
			return Des3Util.encryptToHex(key, result);
		}
		public static String getClientId(String password){
			String passWord = Des3Util.decryptFromHex(KEY, password);
			return passWord.split(",")[1];
		}

//		public static String getTokenMoudel(String clientId,String menberId,String secret){
//			String passWord = Des3Util.decryptFromHex(KEY, secret);
//			String key = passWord.split(",")[0];
//			String result = clientId+","+menberId+","+System.currentTimeMillis()/1000;
//			result = result + ","+ encryptToMD5(result);
//			return  Des3Util.encryptToHex(key,result);
//		}
		public static String saveKey(String secret){
			String passWord = Des3Util.decryptFromHex(KEY, secret);
			L.e("saveKey:——passWord"+passWord);
			String key = passWord.split(",")[0];
			SPUtils.put(Constant.SP_KEY.TOKEN_KEY,key, getCommonSp());
			return key;
		}


	public static String getToken(String clientId, String menberId, String key){
		String result = clientId+","+menberId+","+ System.currentTimeMillis();
		result = result + ","+ encryptToMD5(result); 
		return  Des3Util.encryptToHex(key,result);
	}
}
