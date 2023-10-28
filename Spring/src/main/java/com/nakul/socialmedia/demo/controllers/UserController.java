package com.nakul.socialmedia.demo.controllers;

import com.nakul.socialmedia.demo.dao.UserDao;
import com.nakul.socialmedia.demo.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.POST)
        //@PostMapping()
    void addUser(UserModel userModel) {
    }


    @RequestMapping(method = RequestMethod.GET)
        //@GetMapping
    void getUser() {
        userDao.getUsers();
    }

    @RequestMapping(method = RequestMethod.PUT)
        //@PutMapping
    void updateUser() {
    }

    @RequestMapping(method = RequestMethod.DELETE)
        //@PutMapping
    void deleteUser() {
    }

}
