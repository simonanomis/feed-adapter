package programmer.pesonal.feedadapter.service;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import programmer.pesonal.feedadapter.model.FeedEntry;

import java.util.List;

@Log
@Service
public class RssGeneratorService {
    private FeedEntryService feedEntryService;

    public RssGeneratorService(FeedEntryService feedEntryService) {
        this.feedEntryService = feedEntryService;
    }
    @SneakyThrows
    public String generateRssFeed() {
        List<FeedEntry> entries = feedEntryService.findAll();
        return processRssFeed(entries);
    }

    private String processRssFeed(List<FeedEntry> entries) {
//        TO DO
        return " ";
    }


}
