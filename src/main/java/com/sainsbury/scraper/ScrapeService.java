package com.sainsbury.scraper;

import com.google.gson.JsonObject;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScrapeService {

    private final static Logger LOG = LoggerFactory.getLogger(ScrapeService.class);

    public JsonObject getProducts(final String url) {

        Document website = scrape(url);

        return new JsonObject();
    }


    private Document scrape(final String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.error(String.format("Unable to scrape from url: %s", url));
            return new Document(url);
        }
    }
}
