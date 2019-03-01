package com.hongdun.util;

import java.util.Random;

import com.github.cage.IGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Created on 2019/2/28.
 *
 * @author zhang
 */
public class TokenGenerator implements IGenerator<String> {

    private static final Random random = new Random();

    private int length = 4;

//    private String charsetdir = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
    private String charsetdir = "0123456789";

    public TokenGenerator() {
    }

    public TokenGenerator(int length, String charsetdir) {
        this.length = length <= 0 ? this.length : length;
        this.charsetdir = StringUtils.isBlank(charsetdir) ? this.charsetdir : charsetdir;
    }

    public static void main(String[] args) {
        TokenGenerator tokenGenerator = new TokenGenerator(5, "           ");
        for (int i = 0; i < 100; i++) {
            System.out.println(tokenGenerator.next());
        }
    }

    @Override
    public String next() {
        StringBuffer sb = new StringBuffer();
        int len = charsetdir.length();
        for (int i = 0; i < length; i++) {
            sb.append(charsetdir.charAt(random.nextInt(len)));
        }
        return sb.toString();
    }
}
