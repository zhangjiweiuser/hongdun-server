package com.hongdun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiwei.zhang
 * @date 2019-03-01 下午 17:23
 */
@Controller
public class ToHtmlController {

    @RequestMapping("/picUpload")
    public String picUpload() {
        return "picUpload";
    }
}
