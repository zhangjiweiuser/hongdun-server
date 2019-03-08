package com.hongdun.controller;

import com.hongdun.entity.Response;
import com.hongdun.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
//    @AuthPermission(value = AuthCode.ADDUSER,perms={AuthCode.DEL_USER,AuthCode.RESET_USER})
    public Response getUser(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        return userService.getUser(pageNo, pageSize);
    }

}
