package com.hongdun.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jiwei.zhang
 * @date 2019-03-01 下午 17:58
 */
@Getter
@Setter
public class FeedOrigin implements Serializable{

    private String name;
    private MultipartFile file;
}
