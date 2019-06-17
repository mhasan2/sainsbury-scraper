package com.sainsbury.scraper;

import com.sainsbury.scraper.Service.ScrapeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SainsburyScraperCommandLineRunner implements CommandLineRunner {

    private static final String GROCERY_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
    private ScrapeService scrapeService;

    @Override
    public void run(String... args) {
        System.out.println(scrapeService.getProducts(GROCERY_URL));
    }
}
