package com.hongdun.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * RSA密码工具类，用于
 *
 * @author zhang
 * @date 2019-03-04 下午 15:45
 */
@Component
public class RsaUtil {

    public static final String KEY_ALGORITHM = "RSA";

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX8UPyuWBRR6F/N3SSlvs8i/O2j2KL9U+MbAXqBgT4MfJrdJ4P8jyh2SbQZ/SZ8sRbBwshRDzXhAs898u/bC8+FrCWWHQfZCotKvnHh8lP1Znzggm3rJ2pyoqBvWt2fIm0aqVCx2JaYlEZ/uPfD1tq1TCmPTY3gb6c7iKCwY3ePQIDAQAB";

    @Value("${rsa.private.key}")
    public String PRIVATE_KEY;

    public byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    public String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }


    public byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public String decrypt(String data) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(PRIVATE_KEY);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }

    /**
     * 加密<br>
     * 用公钥加密
     */
    public byte[] encryptByPublicKey(String data, String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    /**
     * 初始化密钥
     */
    public static void initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        System.out.println("我是公钥:" + Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
        System.out.println("我是私钥:" + Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
    }

    public static void main(String[] args) throws Exception {
//        initKey();
        RsaUtil rsaUtil = new RsaUtil();
        byte[] encryptByPublicKey = rsaUtil.encryptByPublicKey("我是小白兔", PUBLIC_KEY);
        System.out.println(Base64.encodeBase64String(encryptByPublicKey));
        System.out.println("-----------------------------------");
        StringBuffer sb = new StringBuffer();
        for (byte b : encryptByPublicKey) {
            sb.append(b);
        }
        System.out.println(sb.toString());
        String str = "XCKkzOMJ84X8lcLy78YcIXALTkLELOMOP6oB9Q9G6BAic/hVF+yGNArHXwOLVFnWzOxGO2YTFwPETys3+4Mq4RRQmfg/Q7harym7BH317JjcTfuZr66sVKUqATzZQSRhzgCWP3UDQOy5QeJ0PbtegjQjhGt8yT6/LtRVorsyF4w=";

        byte[] decryptByPrivateKey = rsaUtil.decryptByPrivateKey(Base64.decodeBase64(str), rsaUtil.PRIVATE_KEY);
//        byte[] decryptByPrivateKey = decryptByPrivateKey(encryptByPublicKey, PRIVATE_KEY);
        System.out.println("-----------------------------------");
        System.out.println(new String(decryptByPrivateKey));

    }
}
