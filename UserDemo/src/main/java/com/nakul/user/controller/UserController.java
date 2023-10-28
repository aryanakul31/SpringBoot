package com.nakul.user.controller;

import com.nakul.user.model.User;
import com.nakul.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> get() {
        return userService.get();
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/user")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "Employee Deleted";
    }
}
