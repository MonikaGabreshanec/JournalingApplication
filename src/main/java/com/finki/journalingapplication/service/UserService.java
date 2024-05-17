package com.finki.journalingapplication.service;

import com.finki.journalingapplication.model.User;

public interface UserService {
    User registerAccount(String name, String surname, String username, String password, String rpassword);
    User loginAccount(String username, String password);
    //User updatePassword(String username, String newPassword);
}
