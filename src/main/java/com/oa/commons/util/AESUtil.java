/**  
 * @Project: jxoa
 * @Title: AESUtil.java
 * @Package com.oa.commons.util
 * @date 2013-6-2 下午5:42:38
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 
 * 类名：AESUtil
 * 功能：AES加密工具
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-2 下午5:42:38
 *
 */
public class AESUtil {
	
	private AESUtil(){}
	
    /**
     * 加密
     * 
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
            try {           
                    KeyGenerator kgen = KeyGenerator.getInstance("AES");
                    kgen.init(128, new SecureRandom(password.getBytes()));
                    SecretKey secretKey = kgen.generateKey();
                    byte[] enCodeFormat = secretKey.getEncoded();
                    SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                    Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                    byte[] byteContent = content.getBytes("utf-8");
                    cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
                    return parseByte2HexStr(cipher.doFinal(byteContent));//加密之后将二进制转化成16进制
                    
            } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
            } catch (InvalidKeyException e) {
                    e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
            } catch (BadPaddingException e) {
                    e.printStackTrace();
            }
            return null;
    }
    
    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(String content, String password) {
            try {
            		
                     KeyGenerator kgen = KeyGenerator.getInstance("AES");
                     kgen.init(128, new SecureRandom(password.getBytes()));
                     SecretKey secretKey = kgen.generateKey();
                     byte[] enCodeFormat = secretKey.getEncoded();
                     SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");            
                     Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                    cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
                    byte[] result = cipher.doFinal(parseHexStr2Byte(content));
                    return new String(result); // 解密
            } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
            } catch (InvalidKeyException e) {
                    e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
            } catch (BadPaddingException e) {
                    e.printStackTrace();
            }
            return null;
    }
    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < buf.length; i++) {
                    String hex = Integer.toHexString(buf[i] & 0xFF);
                    if (hex.length() == 1) {
                            hex = '0' + hex;
                    }
                    sb.append(hex.toUpperCase());
            }
            return sb.toString();
    }
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1)
                    return null;
            byte[] result = new byte[hexStr.length()/2];
            for (int i = 0;i< hexStr.length()/2; i++) {
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                    result[i] = (byte) (high * 16 + low);
            }
            return result;
    }
    public static void main(String[] args){
    	
        String content = "test";
        String password = "12345678";
        //加密
        System.out.println("加密前：" + content);
        String encryptResultStr = encrypt(content, password);
        System.out.println("加密后：" + encryptResultStr);
        //解密
        String decryptResult = decrypt(encryptResultStr,password);
        System.out.println("解密后：" + decryptResult);
    	
    }
    
    
    
	
}
