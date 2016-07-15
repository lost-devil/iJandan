package com.wyy.ijandan.data.htmlparser;

import android.text.TextUtils;
import android.util.Log;

import com.wyy.ijandan.data.model.ImageFeed;
import com.wyy.ijandan.data.model.ImageFeedPic;
import com.wyy.ijandan.utils.ImageUtil;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yayun.wei on 2016/7/14.
 */
public class JsoupImageFeedParser extends ImageFeedParser<Element>{

    private static final String TAG = "JsoupImageFeedParser";

    public static final String TAG_FEED = "li";

    public static final String ID_AD = "adsense";

    public static final String QUERY_AUTHOR = "strong";

    public static final String QUERY_TIME = "small > a";

    public static final String QUERY_OO_COUNT = "span#cos_support-%d";

    public static final String QUERY_XX_COUNT = "span#cos_unsupport-%d";

    public static final String QUERY_TUCAO_COUNT = "span.ds-thread-count";

    public static final String QUERY_PICS = "div.text > p";

    public JsoupImageFeedParser(Element parent) {
        super(parent);
    }

    @Override
    public Element getParent() {
        return (Element)mParent;
    }

    @Override
    public List<ImageFeed> parseFeeds() {
        Elements feedEles = getParent().getElementsByTag(TAG_FEED);
        if (feedEles != null && feedEles.size() > 0) {
            List<ImageFeed> feedList = new ArrayList<>();
            for (Element element :feedEles) {
                if (element.id().equals(ID_AD)) {
                    continue;
                }
                ImageFeed feed = parseFeed(element);
                if (feed != null) {
                    feedList.add(feed);
                }
            }
            return feedList;
        }
        return null;
    }

    @Override
    public ImageFeed parseFeed(Element feedNode) {
        Log.d(TAG, "parseFeed node = " + feedNode.toString());
        ImageFeed feed = new ImageFeed();
        feed.id = parseFeedId(feedNode);
        parseAuthorAndTime(feed, feedNode);
        feed.pics = parsePics(feedNode);
        parseVoteCount(feed, feedNode);
        Log.i(TAG, "parseFeed feed = " + feed.toString());
        return feed;
    }

    private int parseFeedId(Element feedNode) {
        String idString = feedNode.id();
        String[] idStrs = idString.split("-");
        int id  = Integer.valueOf(idStrs[idStrs.length -1]);
        Log.i(TAG, "parseFeedId id = " + id);
        return id;
    }

    private void parseAuthorAndTime(ImageFeed feed, Element feedNode) {
        feed.author = feedNode.select(QUERY_AUTHOR).first().text();
        feed.time = feedNode.select(QUERY_TIME).first().text();
    }

    private void parseVoteCount(ImageFeed feed, Element feedNode) {
        String ooCountStr = feedNode.select(String.format(QUERY_OO_COUNT, feed.id)).first().text();
        if (!TextUtils.isEmpty(ooCountStr)) {
            feed.ooCount = Integer.valueOf(ooCountStr);
        }
        String xxCountStr = feedNode.select(String.format(QUERY_XX_COUNT, feed.id)).first().text();
        if (!TextUtils.isEmpty(xxCountStr)) {
            feed.xxCount = Integer.valueOf(xxCountStr);
        }
//        String tucaoCountStr = feedNode.select(QUERY_TUCAO_COUNT).first().text();
//        if (!TextUtils.isEmpty(tucaoCountStr)) {
//            feed.tucaoCount = Integer.valueOf(tucaoCountStr.substring(1, tucaoCountStr.length() - 2));
//        }
    }

    private List<ImageFeedPic> parsePics(Element feedNode) {
        Elements picElements = feedNode.select(QUERY_PICS);
        if (picElements != null && picElements.size() > 0) {
            List<ImageFeedPic> pics = new ArrayList<>();
            for (Element picElement : picElements) {
                String content = picElement.text();
                if (!TextUtils.isEmpty(content)) {
                    content = content.replace("[查看原图]", "");
                }
                Elements largeElements = picElement.getElementsByTag("a");
                Elements srcElements = picElement.getElementsByTag("img");
                if (largeElements != null && srcElements != null
                        && largeElements.size() > 0
                        && srcElements.size() > 0) {//有图
                    for (int i = 0; i < srcElements.size(); i++) {
                        ImageFeedPic pic = new ImageFeedPic();
                        pic.content = content;
                        Element largeEle = largeElements.get(i);
                        Element srcEle = srcElements.get(i);
                        pic.coverUrl = srcEle.attr("src");
                        if (ImageUtil.isGif(pic.coverUrl)) {
                            pic.srcUrl = srcEle.attr("org_src");
                        } else {
                            pic.srcUrl = pic.coverUrl;
                        }
                        pic.largeUrl = largeEle.attr("href");
                        pics.add(pic);
                    }
                } else if (!TextUtils.isEmpty(content)) {//无图
                    ImageFeedPic pic = new ImageFeedPic();
                    pic.content = content;
                    pics.add(pic);
                }
            }
            return pics;
        }
        return null;
    }
}
