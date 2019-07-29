package com.czw.basic.utils;

import jodd.datetime.JDateTime;
import jodd.datetime.Period;

/**
 * @auth czw
 * @date 2018-12-12
 * @time 16:00
 */
public class DateUtils {

    public static void main(String[] args) {

        JDateTime newjdt=new JDateTime(System.currentTimeMillis());


        long time = 1543593600000L;
        JDateTime dateTime = new JDateTime(time);
        JDateTime newjdtTow=new JDateTime("2018-11-03 00:10:22.23");
        Period newperiod=new Period(newjdtTow, dateTime);
        System.out.println(newperiod.getDays()); //获取两个时间中天数部分的差
    }
}
