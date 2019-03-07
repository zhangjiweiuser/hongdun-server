package com.hongdun.dao;

import com.hongdun.entity.FeedOrigin;
import org.springframework.stereotype.Repository;

/**
 * @author zhang
 * @date 2019/3/7 22:04
 */
@Repository
public interface FeedOriginDao {

    /**
     * @return 获取最后一次插入的code
     */
    String getLastCode();

    /**
     * 插入一条feed信息
     * @param feedOrigin feed流原始信息
     * @return 返回影响的条数
     */
    int addFeedOrigin(FeedOrigin feedOrigin);


    /**
     * 更新一条feed信息
     * @param feedOrigin feed流原始信息
     * @return 返回影响的条数
     */
    int updateFeedOrigin(FeedOrigin feedOrigin);
}
