package com.dhx.dem0828.service.impl;

import com.dhx.dem0828.model.UserEntity;
import com.dhx.dem0828.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author adorabled4
 * @className UserServiceImpl
 * @date : 2023/08/28/ 16:40
 **/
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void printUser(UserEntity user) {
        System.out.println(user.toString());
    }
}
