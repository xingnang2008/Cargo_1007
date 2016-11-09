package com.cargo.util;

import java.security.MessageDigest;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Md5Util {
	 /*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
 // //////////////////////////////////////////////////////////////////////////
	/**
	* 创建密匙
	* 
	* @param algorithm
	*            加密算法,可用 DES,DESede,Blowfish
	* @return SecretKey 秘密（对称）密钥
	*/
	public SecretKey createSecretKey(String algorithm) {
		// 声明KeyGenerator对象
		KeyGenerator keygen;
		// 声明 密钥对象
		SecretKey deskey = null;
		try {
			// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
			keygen = KeyGenerator.getInstance(algorithm);
			// 生成一个密钥
			deskey = keygen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 返回密匙
		return deskey;
	}
    /**
	* 根据密匙进行DES加密
	* 
	* @param key
	*            密匙
	* @param info
	*            要加密的信息
	* @return String 加密后的信息
	*/
	public String encryptToDES(String info) {
		// 定义 加密算法,可用 DES,DESede,Blowfish
		String Algorithm = "DES";
		SecretKey  key = createSecretKey(Algorithm);
		// 加密随机数生成器 (RNG),(可以不写)
		SecureRandom sr = new SecureRandom();
		// 定义要生成的密文
		byte[] cipherByte = null;
		try {
			// 得到加密/解密器
			Cipher c1 = Cipher.getInstance(Algorithm);
			// 用指定的密钥和模式初始化Cipher对象
			// 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
			c1.init(Cipher.ENCRYPT_MODE, key, sr);
			// 对要加密的内容进行编码处理,
			cipherByte = c1.doFinal(info.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回密文的十六进制形式
		return byte2hex(cipherByte);
	}
	/**
	* 根据密匙进行DES解密
	* 
	* @param key
	*            密匙
	* @param sInfo
	*            要解密的密文
	* @return String 返回解密后信息
	*/
	public String decryptByDES(String sInfo) {
		// 定义 加密算法,
		String Algorithm = "DES";
		SecretKey  key = createSecretKey(Algorithm);
		// 加密随机数生成器 (RNG)
		SecureRandom sr = new SecureRandom();
		byte[] cipherByte = null;
		try {
			// 得到加密/解密器
			Cipher c1 = Cipher.getInstance(Algorithm);
			// 用指定的密钥和模式初始化Cipher对象
			c1.init(Cipher.DECRYPT_MODE, key, sr);
			// 对要解密的内容进行编码处理
			cipherByte = c1.doFinal(hex2byte(sInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return byte2hex(cipherByte);
		return new String(cipherByte);
	}
	/**
	* 将二进制转化为16进制字符串
	* 
	* @param b
	*            二进制字节数组
	* @return String
	*/
	public String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	/**
	* 十六进制字符串转化为2进制
	* 
	* @param hex
	* @return
	*/
	public byte[] hex2byte(String hex) {
		byte[] ret = new byte[8];
		byte[] tmp = hex.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
	/**
	* 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	* 
	* @param src0
	*            byte
	* @param src1
	*            byte
	* @return byte
	*/
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}
    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("ss");
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5Encode(str));
        
        Md5PasswordEncoder md5 = new Md5PasswordEncoder(); 
        String result = md5.encodePassword("wu", "sssss");
        System.out.println(result);
        
        Md5Util mu = new Md5Util();
       
        String info ="89266-892-869";
        String mi = mu.encryptToDES(info);
        System.out.println("mi encode:"+mi);
        
        String mi2 = mu.decryptByDES(mi);
        System.out.println("mi decode:"+mi2);
        
        
        
    }
}
