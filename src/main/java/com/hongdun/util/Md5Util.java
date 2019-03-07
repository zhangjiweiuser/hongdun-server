package com.hongdun.util;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author jiwei.zhang
 * @date 2019-03-04 下午 18:41
 */
public class Md5Util {

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("我的").toUpperCase());
        System.out.println(DigestUtils.md5Hex("我的放").toUpperCase());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
    }
}
