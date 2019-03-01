package com.hongdun.service;

import com.hongdun.dao.UserDao;
import com.hongdun.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zhang
 * @date 2019/2/28 0005
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

}
