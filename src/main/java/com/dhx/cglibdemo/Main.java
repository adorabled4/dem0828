package com.dhx.cglibdemo;

import com.dhx.dem0828.model.UserEntity;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author adorabled4
 * @className Main
 * @date : 2023/08/29/ 13:28
 **/
public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new ServiceInterceptor());
        // create
        UserService service = (UserService)enhancer.create();
        // service only can invoke public method
        service.printUser(new UserEntity());
    }
}
