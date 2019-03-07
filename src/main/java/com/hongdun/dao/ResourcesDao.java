package com.hongdun.dao;


import com.hongdun.entity.Resources;
import com.hongdun.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 验证权限的User
 *
 * @author zhang
 * @date 2019/3/7 0005
 */
@Repository
public interface ResourcesDao {

    /**
     * 插入资源信息
     */
    int insertResource(Resources resources);

}
