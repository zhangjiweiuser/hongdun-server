package com.hongdun.service;

import com.hongdun.dao.FeedOriginDao;
import com.hongdun.dao.ResourcesDao;
import com.hongdun.entity.FeedOrigin;
import com.hongdun.entity.Resources;
import com.hongdun.entity.Response;
import com.hongdun.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * @param feed feed流
     * @return 返回执行结果
     */
    public Response updateFeed(FeedOrigin feed) {
        if(null == feed.getBid()){
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

}
