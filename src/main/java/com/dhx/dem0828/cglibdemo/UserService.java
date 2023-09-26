package com.dhx.dem0828.cglibdemo;

import com.dhx.dem0828.model.UserEntity;

/**
 * @author adorabled4
 * @className UserSerbice
 * @date : 2023/08/29/ 13:24
 **/
public class UserService {

    public void printUser(UserEntity user) {
        System.out.println(user.toString());
    }

    private UserEntity getUser(long userId) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setUserName("Job");
        user.setEmail("xxx@gmail.com");
        return user;
    }

}
