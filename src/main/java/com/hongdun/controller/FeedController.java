package com.hongdun.controller;

import java.util.Arrays;
import java.util.List;

import com.hongdun.entity.FeedOrigin;
import com.hongdun.entity.Response;
import com.hongdun.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang
 * @date 2019-03-07 下午 18:19
 */
@RestController
@Validated
public class FeedController {

    @Autowired
    private FeedService feedService;

    /**
     * 新增feed
     *
     * @param feedOrigin feed
     * @return 成功信息
     */
    @PostMapping("/addFeed")
    public Response addFeed(FeedOrigin feedOrigin) {
        return feedService.addFeed(feedOrigin);
    }

    /**
     * 修改feed
     *
     * @param feedOrigin feed
     * @return 成功信息
     */
    @PostMapping("/updateFeed")
    public Response updateFeed(FeedOrigin feedOrigin) {
        return feedService.updateFeed(feedOrigin);
    }

    /**
     * 查询feed信息
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param status    处理状态
     * @param keys      关键字
     * @return 符合条件的feed信息
     */
    @RequestMapping(value = "/getFeeds")
    public Response getFeeds(String startDate, String endDate, Integer status, String keys, int pageNo, int pageSize) {

        List<String> keyList = Arrays.asList(keys.split(","));
        return feedService.getFeed(startDate, endDate, status, keyList, pageNo, pageSize);
    }

    /**
     * 审核feed流
     *
     * @param ids    feed流id，多个用,隔开
     * @param status 2 审核通过，3：审核不通过
     * @param note   3，审核不通过有审批意见
     * @return 返回是否成功
     */
    @RequestMapping(value = "/reviewFeed")
    public Response reviewFeed(String ids, Integer status, String note) {
        return feedService.reviewFeed(ids, status, note);
    }
}
