package com.hongdun.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jiwei.zhang
 * @date 2019-03-01 下午 17:58
 */
@Getter
@Setter
public class FeedOrigin implements Serializable {

    private Integer id;

    /**
     * 信息编号
     */
    private String code;

    /**
     * 信息标题
     */
    private String title;

    /**
     * 信息描述
     */
    private String description;

    /**
     * 头图id
     */
    private Integer bid;

    /**
     * 头图url
     */
    private String burl;

    /**
     * 信息发布时间
     */
    private String publishTime;

    /**
     * 信息来源
     */
    private String source;

    /**
     * 信息来源网址
     */
    private String sourceUrl;

    /**
     * 关键字
     */
    private String keys;

    /**
     * 信息重要级别
     */
    private int level;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 采编人
     */
    private String editor;

    /**
     * 审批状态
     */
    private int status;

    /**
     * 审批意见
     */
    private String note;
}
