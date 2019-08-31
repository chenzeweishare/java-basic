package com.czw.basic.stock;

/**
 * @auth czw
 * @date 2019-04-22
 * @time 15:04
 */
public class StockUtils {

    public static void main(String[] args) {

        System.out.println("大吉大利, 今晚吃鸡");
        getPrice(2.82);
        getPriceByDiscount(3.28, -9.5);
    }



    private static void getPrice(double baseAmount) {

        for (int i = 0; i < 11; i++) {
             double amount  = baseAmount * (100 - i) / 100;
             System.out.println("跌" + i + "点 :"  + amount );
        }

        System.out.println("------------------------------------");

        for (int i = 0; i < 11; i++) {
            double amount  = baseAmount * (100 + i) / 100;
            System.out.println("升" + i + "点 :"  + amount );
        }

    }


    private static void getPriceByDiscount(double baseAmount, double discount) {
        System.out.println("----------------指定点的价格, 折扣为" + discount +"---------------");
        double amount  = baseAmount * (100 + discount) / 100;
        System.out.println(amount);
        System.out.println("----------------指定点的价格---------------");
    }

}
