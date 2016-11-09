package com.cargo.util;

import java.io.IOException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class DesUtil {
private final static String DES = "DES";
    public static void main(String[] args) throws Exception {
        String data = "89266-892-869";
        String key = "free!@#$%";
        /**加密 **/
        System.err.println(encrypt(data, key));
        /**解密**/
        System.err.println(decrypt(encrypt(data, key), key));
    }
    /**
    *  根据键值进行加密
    */
    @SuppressWarnings("restriction")
	public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
    /**
    *  根据键值进行解密
    */
    public static String decrypt(String data, String key) throws IOException,
        Exception {
    if (data == null)
        return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }
    /**
    *  根据键值进行加密
    */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(data, key, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(data);
    }
    /**
    *  根据键值进行解密
    */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(data, key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(data);
    }
    private static Cipher cipherInit(byte[] data, byte[] key,int cipherValue)throws Exception {
        /** 生成一个可信任的随机数源**/
        SecureRandom sr = new SecureRandom();
        /** 从原始密钥数据创建DESKeySpec对象**/
        DESKeySpec dks = new DESKeySpec(key);
        /** 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象**/
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        /** Cipher对象实际完成加密或解密操作**/
        Cipher cipher = Cipher.getInstance(DES);
        /**用密钥初始化Cipher对象**/
        cipher.init(cipherValue, securekey, sr);
        return cipher;
    }
}