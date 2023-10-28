package com.nakul.user.service;

import com.nakul.user.model.User;

import java.util.List;

public interface UserService {
    List<User> get();
    User get(int id);
    User save (User user);

    void delete(int id);

}
