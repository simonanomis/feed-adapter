package programmer.pesonal.feedadapter.utils;


import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

public class RssUtils {
    public static SyndFeed getFeed() {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");
        return feed;
    }

    public static SyndContent getFeedEntryContent() {
        SyndContent content = new SyndContentImpl();
        content.setType("text/html");
        return content;
    }
}
