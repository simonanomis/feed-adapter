package programmer.pesonal.feedadapter.service;

import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import programmer.pesonal.feedadapter.config.SpringBootConfig;
import programmer.pesonal.feedadapter.model.FeedEntry;
import programmer.pesonal.feedadapter.model.FeedEntryDescription;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.util.List;

@Log
@Service
@EnableScheduling
public class WebScraperService {

    private final FeedEntryService feedEntryService;

    @Autowired
    private SpringBootConfig config;

    public WebScraperService(FeedEntryService feedEntryService) {
        this.feedEntryService = feedEntryService;
    }
    @SneakyThrows
    @Scheduled(fixedDelayString = "${feedadapter.fixedDelay}")
    public void scrapAndSave() throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(config.getUrl())));
        for (int i=0;i<feed.getEntries().size();i++){
            if (i == 10) {
                break;
            }
            SyndEntry entry = (SyndEntry) feed.getEntries().get(i);
            FeedEntry feedEntry = getEntry(entry);
            setImageURL(entry, feedEntry);
            System.out.println("image URL: "+ feedEntry.getUrl());
            FeedEntry fe = feedEntryService.existsByUrl(feedEntry.getUrl());
            if (checkIfEntityNull(fe) && ((feedEntry.getPublicationDate()) != fe.getPublicationDate())){
                updateDbObject(fe, feedEntry);
                feedEntryService.save(fe);
            }
            else{
                feedEntryService.save(feedEntry);
            }

        }

//        feed.getEntries().forEach(entry -> {
//
////              SyndEntry e = (SyndEntry) entry;
////              System.out.println("*******Author******"+e.getAuthor());
////              System.out.println("*******Title*******"+e.getTitle());
////              System.out.println("*******Description****"+String.valueOf(e.getDescription()));
////              System.out.println("*******URI*******"+e.getUri());
////              System.out.println("*******PublishedDate*******"+e.getPublishedDate());
//
//            FeedEntry feedEntry = getEntry((SyndEntry) entry);
//
//            setImageURL((SyndEntry) entry, feedEntry);
//            System.out.println("image URL: "+ feedEntry.getUrl());
//            FeedEntry fe = feedEntryService.existsByUrl(feedEntry.getUrl());
//            if (checkIfEntityNull(fe) && ((feedEntry.getPublicationDate()) != fe.getPublicationDate())){
//                feedEntryService.deleteFeedEntryBy(fe);
//                feedEntryService.save(feedEntry);
//            }
//            else{
//                feedEntryService.save(feedEntry);
//            }
//
//
//        });


    }

    private void updateDbObject(FeedEntry fe, FeedEntry feedEntry) {
        fe.setImageUrl(feedEntry.getImageUrl());
        fe.setUrl(feedEntry.getUrl());
        fe.setTitle(feedEntry.getTitle());
        fe.setAuthor(feedEntry.getAuthor());
        fe.setPublicationDate(feedEntry.getPublicationDate());
        FeedEntryDescription feedEntryDescription = fe.getFeedEntryDescription();
        feedEntryDescription.setValue(feedEntry.getFeedEntryDescription().getValue());
        feedEntryDescription.setType(feedEntry.getFeedEntryDescription().getType());
        feedEntryDescription.setMode(feedEntry.getFeedEntryDescription().getMode());
    }

    //    @org.jetbrains.annotations.NotNull
    FeedEntry getEntry(SyndEntry e) {
        FeedEntryDescription feedEntryDescription = new FeedEntryDescription();
        feedEntryDescription.setMode(e.getDescription().getMode());
        feedEntryDescription.setType(e.getDescription().getType());
        feedEntryDescription.setValue(e.getDescription().getValue());
        FeedEntry feedEntry = new FeedEntry();
        feedEntry.setFeedEntryDescription(feedEntryDescription);
        feedEntry.setAuthor(e.getAuthor());
        feedEntry.setTitle(e.getTitle());
        feedEntry.setPublicationDate(e.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        feedEntry.setUrl(e.getUri());
        return feedEntry;
    }

    private void setImageURL(SyndEntry e, FeedEntry feedEntry) {
        List<SyndEnclosure> enclosures = e.getEnclosures();
        if(enclosures!=null) {
            for(SyndEnclosure enclosure : enclosures) {
                if(enclosure.getType()!=null && enclosure.getType().equals("image/jpeg")){
//                        System.out.println("image URL: "+enclosure.getUrl());
                    feedEntry.setImageUrl(enclosure.getUrl());
                }
            }

        }
    }

    public boolean checkIfEntityNull(Object entity){
        return entity != null;
    }
}
