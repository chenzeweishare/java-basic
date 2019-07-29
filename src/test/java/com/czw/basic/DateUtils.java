package com.czw.basic;

import jodd.datetime.JDateTime;

/**
 * @auth czw
 * @date 2018-12-12
 * @time 16:00
 */
public class DateUtils {

    public static void main(String[] args) throws Exception {

        JDateTime jDateTime = new JDateTime();
        jDateTime.setHour(12);
        jDateTime.setMinute(0);
        jDateTime.setSecond(0);

        System.out.println(jDateTime.toString());
        if (jDateTime.getTimeInMillis() > System.currentTimeMillis()) {
            System.out.println("xxx");
        }
    }
}
