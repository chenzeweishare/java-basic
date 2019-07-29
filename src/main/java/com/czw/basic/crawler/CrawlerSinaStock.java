package com.czw.basic.crawler;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * @auth czw
 * @date 2019-04-24
 * @time 17:06
 */
public class CrawlerSinaStock {

    //抓取拉钩网数据地址
    private static final String FETCH_URL = "https://finance.sina.com.cn/realstock/company/sh600218/nc.shtml";


    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        String url = FETCH_URL;
        //1、获取总页数
        HttpRequest httpRequest = HttpRequest.get(url);
        HttpResponse httpResponse = httpRequest.send();
        String html = new String(httpResponse.bodyBytes(), "gbk");
        System.out.println(html);
        //Jerry doc = jerry(html);
    }
}
