package com.hongdun.entity;

import lombok.Data;

/**
 * @author zhang
 * @date 2019-03-08 下午 18:02
 */
@Data
public class Subject {

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
     * 信息重要级别
     */
    private int level;

    /**
     * 信息内容
     */
    private String content;


    /**
     * 审批状态
     */
    private int status;

    /**
     * 审批意见
     */
    private String note;

    /**
     * 专题类型 1 要闻  2 普通',
     */
    private int tag = 2;
}
