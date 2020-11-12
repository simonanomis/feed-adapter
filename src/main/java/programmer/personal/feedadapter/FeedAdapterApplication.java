package programmer.personal.feedadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@SpringBootApplication
public class FeedAdapterApplication {

	public static void main(String[] args) throws IOException, FeedException {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(new URL("http://feeds.nos.nl/nosjournaal?format=xml")));
		System.out.println(feed.getEntries() );

		feed.getEntries().forEach( entry-> {
			SyndEntry e = (SyndEntry) entry;
			e.getTitle();
			System.out.println("**************"+e.getTitle());
		});

		SpringApplication.run(FeedAdapterApplication.class, args);
	}
}
