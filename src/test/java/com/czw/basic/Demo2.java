package com.czw.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author czw
 * @date 2019/5/27 18:10
 */
public class Demo2 {

    public static void main(String[] args) {
//        long c=299792458;//光速
//        String format = NumberUtil.decimalFormat(",###", c);//299,792,458
//        System.out.println(format);
//
//        double te1=123456.118;
//        String s = NumberUtil.roundStr(te1, 2);//结果:123456.12
//        System.out.println(s);
//        System.out.println(Double.parseDouble(s));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for (Integer o : list) {
            if (o == 1) {
                list.remove(0);
            }
        }

        System.out.println(list);
    }
}
