package com.hongdun.controller;

import com.hongdun.auth.AuthCode;
import com.hongdun.auth.AuthPermission;
import com.hongdun.entity.User;
import com.hongdun.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户controller
 */
@RestController
@Validated
@Log4j2
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/getUser")
    @AuthPermission(value = AuthCode.ADDUSER,perms={AuthCode.DEL_USER,AuthCode.RESET_USER})
    public User getUser(@Range(min = 1) int id) {
        return new User("zhangsan", "zhangsan", "admin");
        //return userService.queryUserById(id);
    }

}
