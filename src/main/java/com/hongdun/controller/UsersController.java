package com.hongdun.controller;

import com.hongdun.entity.User;
import com.hongdun.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(path = "/getUser", method = RequestMethod.POST)
//    @AuthPermission(value = AuthCode.ADDUSER,perms={AuthCode.DEL_USER,AuthCode.RESET_USER})
    public User getUser(@Range(min = 1) String id) {
        return new User("zhangsan", "zhangsan", id);
        //return userService.queryUserById(id);
    }

}
