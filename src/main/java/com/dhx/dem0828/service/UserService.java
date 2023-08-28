package com.dhx.dem0828.service;

import com.dhx.dem0828.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author adorabled4
 * @className UserService
 * @date : 2023/08/28/ 16:17
 **/
public interface  UserService {

    void printUser(UserEntity user);

}
