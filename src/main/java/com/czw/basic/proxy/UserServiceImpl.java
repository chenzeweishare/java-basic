package com.czw.basic.proxy;

/**
 * @auth czw
 * @date 2019-03-05
 * @time 11:50
 */
public class UserServiceImpl implements UserService {

    @Override
    public void addUser() {
        System.out.println("start insert user into database");
    }

    @Override
    public String findUserById() {
        System.out.println("start find user by userId");
        return null;
    }
}
