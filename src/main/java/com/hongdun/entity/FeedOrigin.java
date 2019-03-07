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
public class FeedOrigin implements Serializable {
    private Integer id;
    private String code;
    private String title;
    private String description;
    private Integer bid;
    private String burl;
    private String publishTime;
    private String source;
    private String sourceUrl;
    private String keys;
    private int level;
    private String content;
    private String editor;
    private String note;
}
