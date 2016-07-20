package com.wyy.ijandan.data.loader;

/**
 * Created by yayun.wei on 2016/7/8.
 */
public interface PageLoader<T> {

    T load(String url) throws Exception;
}
