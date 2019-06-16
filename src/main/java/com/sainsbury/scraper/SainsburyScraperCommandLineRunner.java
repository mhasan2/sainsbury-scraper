package com.sainsbury.scraper;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SainsburyScraperCommandLineRunner implements CommandLineRunner {

    private final static String GROCERY_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
    private ScrapeService scrapeService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(scrapeService.getProducts(GROCERY_URL));
    }
}
