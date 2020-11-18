package programmer.pesonal.feedadapter.controller;

import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import programmer.pesonal.feedadapter.service.WebScraperService;

import java.io.IOException;

@RestController
public class RssFeedController {
    @Autowired
    private WebScraperService webScraperService;

    @GetMapping("/scrap")
    @ResponseBody
    public void scrap() throws IOException, FeedException {
        webScraperService.scrapAndSave();
    }
}
