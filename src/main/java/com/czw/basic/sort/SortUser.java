package com.czw.basic.sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @auth czw
 * @date 2019-03-30
 * @time 11:23
 */
public class SortUser {
    public static void main(String[] args) {
            User zhansan = new User("zhansan", 0.00110D);
            User lisi = new User("lisi", 0.1D);
            User wangwu = new User("wangwu", 0.2D);

            List<User> users = new ArrayList<User>();
            users.add(zhansan);
            users.add(lisi);
            //users.add(wangwu);
            Set set = new HashSet();
            for (User user : users) {
                set.add(user.getId());
            }
            System.out.println(set.size());
        }
}
