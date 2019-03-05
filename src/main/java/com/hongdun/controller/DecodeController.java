package com.hongdun.controller;

import com.hongdun.util.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiwei.zhang
 * @date 2019-03-04 下午 18:19
 */
@RestController
public class DecodeController {

    @Autowired
    RsaUtil rsaUtil;

    @RequestMapping(path = "/decode")
    public String decode(String origin) throws Exception {
        return rsaUtil.decrypt(origin);
    }
}
