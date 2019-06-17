package com.sainsbury.scraper.Util;

import static com.sainsbury.scraper.Service.ScrapeServiceTest.BASE_PAGE_URL;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.CHARSET_UTF_8;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.CHERRY_200_DUMP_PATH;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.CHERRY_200_URL;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.CHERRY_STRAWBERRY_600_DUMP_PATH;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.CHERRY_STRAWBERRY_600_URL;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.MIXED_BERRY_200_DUMP_PATH;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.MIXED_BERRY_200_URL;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.RASPBERRY_150_DUMP_PATH;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.RASPBERRY_150_URL;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.REDUCED_BASE_PAGE_DUMP_PATH;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.STRAWBERRY_400_DUMP_PATH;
import static com.sainsbury.scraper.Service.ScrapeServiceTest.STRAWBERRY_400_URL;
import static org.junit.Assert.assertEquals;

import com.sainsbury.scraper.Model.Product;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.junit.Ignore;
import org.junit.Test;

public class GroceryUtilTest {

    @Test
    public void testBasePageParsedCorrectly() throws IOException {
        List<String> expected = Arrays.asList(STRAWBERRY_400_URL, RASPBERRY_150_URL, CHERRY_200_URL, MIXED_BERRY_200_URL, CHERRY_STRAWBERRY_600_URL);

        List<String> actual = GroceryUtil.parseSearchPage(Jsoup.parse(new File(REDUCED_BASE_PAGE_DUMP_PATH), CHARSET_UTF_8, BASE_PAGE_URL));

        assertEquals(expected, actual);
    }

    @Test
    public void testStrawberriesParsedCorrectly() throws IOException {
        Product expected = new Product("Sainsbury's Strawberries 400g", 33, new BigDecimal("1.75"), "by Sainsbury's strawberries");

        Product actual = GroceryUtil
                .parseGroceryPage(Jsoup.parse(new File(STRAWBERRY_400_DUMP_PATH), CHARSET_UTF_8, STRAWBERRY_400_URL));

        assertEquals(expected, actual);
    }

    @Test
    public void testRaspberriesParsedCorrectly() throws IOException {
        Product expected = new Product("Sainsbury's Raspberries, Taste the Difference 150g", 32, new BigDecimal("2.50"), "Ttd raspberries");

        Product actual = GroceryUtil.parseGroceryPage(Jsoup.parse(new File(RASPBERRY_150_DUMP_PATH), CHARSET_UTF_8, RASPBERRY_150_URL));

        assertEquals(expected, actual);
    }

    @Ignore
    @Test
    public void testCherriesParsedCorrectly() throws IOException {
        Product expected = new Product("Sainsbury's Cherry Punnet 200g", 52, new BigDecimal("1.50"), "Cherries");

        Product actual = GroceryUtil.parseGroceryPage(Jsoup.parse(new File(CHERRY_200_DUMP_PATH), CHARSET_UTF_8, CHERRY_200_URL));

        assertEquals(expected, actual);
    }

    @Test
    public void testMixedBerriesParsedCorrectly() throws IOException {
        Product expected = new Product("Sainsbury's Mixed Berry Twin Pack 200g", null, new BigDecimal("2.75"), "Mixed Berries");

        Product actual = GroceryUtil.parseGroceryPage(Jsoup.parse(new File(MIXED_BERRY_200_DUMP_PATH), CHARSET_UTF_8, MIXED_BERRY_200_URL));

        assertEquals(expected, actual);
    }

    @Test
    public void testCherriesAndStrawberriesParsedCorrectly() throws IOException {
        Product expected = new Product("Sainsbury's British Cherry & Strawberry Pack 600g", null, new BigDecimal("4.00"), "British Cherry & Strawberry Mixed Pack");

        Product actual = GroceryUtil.parseGroceryPage(Jsoup.parse(new File(CHERRY_STRAWBERRY_600_DUMP_PATH), CHARSET_UTF_8, CHERRY_STRAWBERRY_600_URL));

        assertEquals(expected, actual);
    }
}