package com.hongdun.dao;


import com.hongdun.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 验证权限的User
 *
 * @author zhang
 * @date 2019/2/28 0005
 */
@Repository
public interface UserDao {

    /**
     * 根据ID查找User
     */
    User queryUserById(@Param("id") int id);

}
