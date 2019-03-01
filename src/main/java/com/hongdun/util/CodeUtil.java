package com.hongdun.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author zhang
 * @date 2019-02-28 下午 18:06
 */
public class CodeUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

    public static void main(String[] args) {
        System.out.println(generateFeedCode("x201901a9999"));
        System.out.println(generateFeedCode("x201902a0001"));
        System.out.println(generateFeedCode("x201902a9999"));
        System.out.println(generateFeedCode("x201902z9999"));
        System.out.println(generateFeedCode("z201901a9999"));
        System.out.println(generateFeedCode("z201902a0001"));
        System.out.println(generateFeedCode("z201902a9999"));
        System.out.println(generateFeedCode("z201902z9999"));
    }

    public static String generateFeedCode(String origin) {
        //x201902b0001
        String firstStr = origin.substring(0,1);
        String now = LocalDate.now().format(formatter);
        String originDate = origin.substring(1, 7);
        if (!now.equals(originDate)) {
            return firstStr + now + "a0001";
        }
        String originNumStr = origin.substring(8);
        char originChar = origin.charAt(7);
        int originNum = Integer.valueOf(originNumStr);
        if (originNum == 9999 && originChar == 'z') {
            return null;
        }else if (originNum == 9999) {
            originChar += 1;
            originNum = 1;
        } else {
            originNum += 1;
        }
        return firstStr + now + originChar + String.format("%04d", originNum);
    }
}
