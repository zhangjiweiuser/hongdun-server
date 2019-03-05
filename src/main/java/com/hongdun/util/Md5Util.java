package com.hongdun.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;

/**
 * @author jiwei.zhang
 * @date 2019-03-04 下午 18:41
 */
public class Md5Util {

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("123456").toUpperCase());
    }
}
