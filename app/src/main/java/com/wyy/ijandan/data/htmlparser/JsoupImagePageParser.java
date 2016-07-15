package com.wyy.ijandan.data.htmlparser;

import android.text.TextUtils;
import android.util.Log;

import com.wyy.ijandan.data.model.ImageFeed;
import com.wyy.ijandan.data.model.ImagePage;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by yayun.wei on 2016/7/14.
 */
public class JsoupImagePageParser extends ImagePageParser<Element>{

    private static final String TAG = "JsoupImagePageParser";

    public static final String QUERY_PAGE_NUM = "span.current-comment-page";

    public static final String QUERY_FEED_LIST = "ol.commentlist";

    public JsoupImagePageParser(Element parent) {
        super(parent);
    }

    @Override
    public Element getParent() {
        return (Element)mParent;
    }

    @Override
    public ImagePage parse() {
        ImagePage imagePage = new ImagePage();
        imagePage.pageNum = parsePageNum();
        imagePage.feeds = parseFeedList();
        return imagePage;
    }

    /**
     * <span class="current-comment-page">[9342]</span>
     * @return
     */
    private int parsePageNum() {
        Element pageNumEle = getParent().select(QUERY_PAGE_NUM).first();
        if (pageNumEle != null) {
            String pageNumStr = pageNumEle.text();
            if (!TextUtils.isEmpty(pageNumStr)) {
                int pageNum = Integer.valueOf(pageNumStr.substring(1, pageNumStr.length() - 2));
                Log.i(TAG, "parsePageNum pageNum = " + pageNum);
                return pageNum;
            }
        }
        return -1;
    }

    /**
     * <ol class="commentlist" style="list-style-type: none;">
     * @return
     */
    private List<ImageFeed> parseFeedList() {
        Element feedListEle = getParent().select(QUERY_FEED_LIST).first();
        if (feedListEle != null) {
            ImageFeedParser imageFeedParser = new JsoupImageFeedParser(feedListEle);
            return imageFeedParser.parseFeeds();
        }
        return null;
    }
}
