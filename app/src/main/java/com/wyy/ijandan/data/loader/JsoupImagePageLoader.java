package com.wyy.ijandan.data.loader;

import android.util.Log;

import com.wyy.ijandan.data.JandanUrl;
import com.wyy.ijandan.data.htmlparser.ImagePageParser;
import com.wyy.ijandan.data.htmlparser.JsoupImagePageParser;
import com.wyy.ijandan.data.model.ImagePage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by yayun.wei on 2016/7/8.
 */
public class JsoupImagePageLoader implements ImagePageLoader{

    private static final String TAG = "JsoupImagePageLoader";

    @Override
    public ImagePage load(String url) throws Exception{
        Log.d(TAG, "load url = " + url);
        Document doc = Jsoup.connect(url)
                .userAgent("Chrome")
                .get();
        if (doc != null) {
            ImagePageParser imagePageParser = new JsoupImagePageParser(doc);
            return imagePageParser.parse();
        }
        return null;
    }
}
