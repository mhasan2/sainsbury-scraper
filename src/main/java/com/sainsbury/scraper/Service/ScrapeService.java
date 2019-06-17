package com.sainsbury.scraper.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sainsbury.scraper.Util.GroceryUtil;
import com.sainsbury.scraper.Model.Product;
import com.sainsbury.scraper.Model.Response;
import com.sainsbury.scraper.Model.Totals;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScrapeService {

    private final Scraper scraper;

    /**
     * Method to return a minimal json representation of the products found on the given website
     * @param url to be searched
     * @return json representation products
     */
    public JsonElement getProducts(final String url) {

        List<String> productLinks = GroceryUtil.parseSearchPage(scraper.scrape(url));

        List<Product> products = productLinks.stream().map(scraper::scrape).map(GroceryUtil::parseGroceryPage).collect(Collectors.toList());

        Totals totals = new Totals(products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        Response response = new Response(products, totals);

        Gson gson = new Gson();
        return gson.toJsonTree(response);
    }
}
