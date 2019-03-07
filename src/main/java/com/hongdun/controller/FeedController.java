package com.hongdun.controller;

import com.hongdun.entity.FeedOrigin;
import com.hongdun.entity.Response;
import com.hongdun.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/addFeed")
    public Response addFeed(FeedOrigin feedOrigin){
        return feedService.addFeed(feedOrigin);
    }

    @PostMapping("/updateFeed")
    public Response updateFeed(FeedOrigin feedOrigin){
        return feedService.updateFeed(feedOrigin);
    }
}
