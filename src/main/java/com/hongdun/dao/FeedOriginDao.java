package com.hongdun.dao;

import java.util.List;

import com.hongdun.entity.FeedOrigin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhang
 * @date 2019/3/7 22:04
 */
@Repository
public interface FeedOriginDao {

    /**
     * 获取最后一次插入的code
     *
     * @return 获取最后一次插入的code
     */
    String getLastCode();

    /**
     * 插入一条feed信息
     *
     * @param feedOrigin feed流原始信息
     * @return 返回影响的条数
     */
    int addFeedOrigin(FeedOrigin feedOrigin);


    /**
     * 更新一条feed信息
     *
     * @param feedOrigin feed流原始信息
     * @return 返回影响的条数
     */
    int updateFeedOrigin(FeedOrigin feedOrigin);

    /**
     * 查询符合条件的feed
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param status    状态
     * @param keyList   关键字
     * @return 符合条件的feed
     */
    List<FeedOrigin> getFeeds(@Param("startDate") String startDate,
                              @Param("endDate") String endDate,
                              @Param("status") Integer status,
                              @Param("keyList") List<String> keyList);

    /**
     * 审批feed信息
     *
     * @param idList feed流id
     * @param status 审批状态
     * @param note   审批内容
     * @return 返回影响的条数
     */
    int reviewFeedOrigin(@Param("idList") List<Integer> idList, @Param("status") int status, @Param("note") String note);
}
