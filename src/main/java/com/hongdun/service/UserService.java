package com.hongdun.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.hongdun.dao.UserDao;
import com.hongdun.entity.Response;
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

    public Response getUser(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> users = userDao.getUser();
        PageSerializable<User> page = new PageSerializable<>(users);
        return Response.success(0, page);
    }
}
