package com.sainsbury.scraper.Service;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Scraper {

    private static final Logger LOG = LoggerFactory.getLogger(Scraper.class);

    /**
     * Method to scrape html from a website
     * @param url to be scraped
     * @return {@code Document} of the given page
     */
    public Document scrape(final String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.error(String.format("Unable to scrape from url: %s", url));
            return new Document(url);
        }
    }
}
