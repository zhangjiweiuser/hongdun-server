package com.hongdun.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.hongdun.constant.HConstant;
import com.hongdun.dao.FeedOriginDao;
import com.hongdun.dao.ResourcesDao;
import com.hongdun.entity.FeedOrigin;
import com.hongdun.entity.Resources;
import com.hongdun.entity.Response;
import com.hongdun.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhang
 * @date 2019-03-07 下午 17:58
 */
@Service
public class FeedService {

    @Autowired
    ResourcesDao resourcesDao;

    @Autowired
    FeedOriginDao feedOriginDao;

    /**
     * 新增一条feed流
     *
     * @param feed ffe流
     * @return 返回执行结果
     */
    public Response addFeed(FeedOrigin feed) {
        Resources resources = new Resources(feed.getBurl());
        int num = resourcesDao.insertResource(resources);
        if (num != 1) {
            return Response.error(-1, "插入失败，清重试");
        }
        // 获取资源id
        int rId = resources.getId();
        feed.setBid(rId);
        // 获取最后一次插入的code
        String code = feedOriginDao.getLastCode();
        if (null == code) {
            code = "x" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM")) + "a0001";
        } else {
            code = CodeUtil.generateFeedCode(code);
        }
        feed.setCode(code);
        int feedEffectNum = feedOriginDao.addFeedOrigin(feed);
        if (feedEffectNum != 1) {
            return Response.error(-1, "插入失败，请重试");
        }
        return Response.error(0, "插入成功");
    }

    /**
     * 更细一条feed流
     *
     * @param feed feed流
     * @return 返回执行结果
     */
    public Response updateFeed(FeedOrigin feed) {
        if (null == feed.getBid()) {
            Resources resources = new Resources(feed.getBurl());
            int num = resourcesDao.insertResource(resources);
            if (num != 1) {
                return Response.error(-1, "图片更新失败，请重试");
            }
            // 获取资源id
            int rId = resources.getId();
            feed.setBid(rId);
        }

        int feedEffectNum = feedOriginDao.updateFeedOrigin(feed);
        if (feedEffectNum != 1) {
            return Response.error(-1, "更新失败，请重试");
        }
        return Response.error(0, "更新成功");
    }


    public Response getFeed(String startDate, String endDate, Integer status, List<String> keyList, int pageNo, int pageSize) {
        // 第一个参数的第几页，第二个参数的每页显示条数
        PageHelper.startPage(pageNo, pageSize);
        List<FeedOrigin> feedOriginList = feedOriginDao.getFeeds(startDate, endDate, status, keyList);
        PageSerializable<FeedOrigin> page = new PageSerializable<>(feedOriginList);
        return Response.success(0, page);
    }

    /**
     * 审核feed流
     *
     * @param ids    feed流id，多个用,隔开
     * @param status 2 审核通过，3：审核不通过
     * @param note   3，审核不通过有审批意见
     * @return 返回是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Response reviewFeed(String ids, Integer status, String note) {
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::valueOf).collect(Collectors.toList());
        // 审批不通过
        if (status == HConstant.REVIEW_FAILED) {
            feedOriginDao.reviewFeedOrigin(idList, status, note);
        }
        if (status == HConstant.REVIEW_SUCCESS) {
            // 将状态置为通过，同时将数据转移到feed表中以及user_feed_state
            feedOriginDao.reviewFeedOrigin(idList, status, note);
            // TODO
        }
        return null;
    }
}
