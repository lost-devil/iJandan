package com.wyy.ijandan.data;

/**
 * Created by yayun.wei on 2016/7/8.
 */
public class JandanUrl {

    public static final String BORING_IMAGE = "http://jandan.net/pic";

    public static final String BORING_IMAGE_PAGE_FORMAT = "http://jandan.net/pic/page-%d";

    public static String boringImage(int page) {
        return String.format(BORING_IMAGE_PAGE_FORMAT, page);
    }
}
