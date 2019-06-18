package com.sainsbury.scraper.Service;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScrapeServiceTest {

    public static final String CHARSET_UTF_8 = "UTF-8";

    public static final String BASE_PAGE_DUMP_PATH = "src/test/resources/sainsbury-base.html";
    public static final String REDUCED_BASE_PAGE_DUMP_PATH = "src/test/resources/sainsbury-base-reduced.html";
    public static final String BASE_PAGE_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public static final String STRAWBERRY_400_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
    public static final String STRAWBERRY_400_DUMP_PATH = "src/test/resources/sainsbury-strawberries-400.html";

    public static final String RASPBERRY_150_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html";
    public static final String RASPBERRY_150_DUMP_PATH = "src/test/resources/sainsbury-raspberry-150.html";

    public static final String CHERRY_200_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html";
    public static final String CHERRY_200_DUMP_PATH = "src/test/resources/sainsbury-cherry-200.html";

    public static final String MIXED_BERRY_200_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html";
    public static final String MIXED_BERRY_200_DUMP_PATH = "src/test/resources/sainsbury-mixed-berry-200.html";

    public static final String CHERRY_STRAWBERRY_600_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html";
    public static final String CHERRY_STRAWBERRY_600_DUMP_PATH = "src/test/resources/sainsbury-cherry-strawberry-600.html";

    public static final String BLACKCURRANT_150_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html";
    public static final String BLACKCURRANT_150_DUMP_PATH = "src/test/resources/sainsbury-blackcurrant-150.html";

    private static final String expectedJson = "{\"results\":[{\"title\":\"Sainsbury's Strawberries 400g\",\"kcal_per_100g\":33,\"unit_price\":1.75,\"description\":\"by Sainsbury's strawberries\"},{\"title\":\"Sainsbury's Blackcurrants 150g\",\"unit_price\":1.75,\"description\":\"Union Flag\"},{\"title\":\"Sainsbury's Raspberries, Taste the Difference 150g\",\"kcal_per_100g\":32,\"unit_price\":2.50,\"description\":\"Ttd raspberries\"},{\"title\":\"Sainsbury's Cherry Punnet 200g\",\"kcal_per_100g\":52,\"unit_price\":1.50,\"description\":\"Cherries\"},{\"title\":\"Sainsbury's Mixed Berry Twin Pack 200g\",\"unit_price\":2.75,\"description\":\"Mixed Berries\"},{\"title\":\"Sainsbury's British Cherry & Strawberry Pack 600g\",\"unit_price\":4.00,\"description\":\"British Cherry & Strawberry Mixed Pack\"}],\"total\":{\"gross\":14.25,\"vat\":2.38}}";

    private ScrapeService scrapeService;

    @Mock
    private Scraper scraper;

    @Before
    public void setup() {
        scrapeService = new ScrapeService(scraper);
    }

    @Test
    public void getProducts() throws IOException {
        mockPages();

        String actual = scrapeService.getProducts(BASE_PAGE_URL).toString();

        Assert.assertEquals(expectedJson, actual);
    }

    private void mockPages() throws IOException {
        Mockito.when(scraper.scrape(BASE_PAGE_URL)).thenReturn(Jsoup.parse(new File(REDUCED_BASE_PAGE_DUMP_PATH), CHARSET_UTF_8, BASE_PAGE_URL));
        Mockito.when(scraper.scrape(STRAWBERRY_400_URL)).thenReturn(Jsoup.parse(new File(STRAWBERRY_400_DUMP_PATH), CHARSET_UTF_8, STRAWBERRY_400_URL));
        Mockito.when(scraper.scrape(RASPBERRY_150_URL)).thenReturn(Jsoup.parse(new File(RASPBERRY_150_DUMP_PATH), CHARSET_UTF_8, RASPBERRY_150_URL));
        Mockito.when(scraper.scrape(CHERRY_200_URL)).thenReturn(Jsoup.parse(new File(CHERRY_200_DUMP_PATH), CHARSET_UTF_8, CHERRY_200_URL));
        Mockito.when(scraper.scrape(MIXED_BERRY_200_URL)).thenReturn(Jsoup.parse(new File(MIXED_BERRY_200_DUMP_PATH), CHARSET_UTF_8, MIXED_BERRY_200_URL));
        Mockito.when(scraper.scrape(CHERRY_STRAWBERRY_600_URL)).thenReturn(Jsoup.parse(new File(CHERRY_STRAWBERRY_600_DUMP_PATH), CHARSET_UTF_8, CHERRY_STRAWBERRY_600_URL));
        Mockito.when(scraper.scrape(BLACKCURRANT_150_URL)).thenReturn(Jsoup.parse(new File(BLACKCURRANT_150_DUMP_PATH), CHARSET_UTF_8, BLACKCURRANT_150_URL));
    }
}