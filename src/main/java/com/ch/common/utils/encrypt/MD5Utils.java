package com.ch.common.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Java消息摘要算法 MD5 工具类,其实其他摘要算法的实现也类似
 */
public class MD5Utils {
	/**
	 * 对文本执行 md5 摘要加密, 此算法与 mysql,JavaScript生成的md5摘要进行过一致性对比.
	 * @param plainText
	 * @return 返回值中的字母为小写
	 */
	public static String getMD5String(String plainText) {
		if (null == plainText) {
			plainText = "";
		}
		String MD5Str = "";
		try {
			// JDK 6 支持以下6种消息摘要算法，不区分大小写
			// md5,sha(sha-1),md2,sha-256,sha-384,sha-512
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuilder builder = new StringBuilder(32);
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					builder.append("0");
				builder.append(Integer.toHexString(i));
			}
			MD5Str = builder.toString();
			// LogUtil.println("result: " + buf.toString());// 32位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return MD5Str;
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD(String inStr){
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}
	// 一个简版测试
	public static void main(String[] args) {
		String m1 = getMD5String("123");
		String m2 = getMD5String(m1);
		/* 输出为
		 * m1=c4ca4238a0b923820dcc509a6f75849b
		 * m2=28c8edde3d61a0411511d3b1866f0636
		 */
		System.out.println("m1="+m1);
		System.out.println("m2="+m2);
	}
}