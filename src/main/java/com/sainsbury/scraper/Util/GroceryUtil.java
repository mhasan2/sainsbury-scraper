package com.sainsbury.scraper.Util;

import com.sainsbury.scraper.Model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

public class GroceryUtil {

    private static final String RELEVANT_PRODUCT_LINK_SELECTOR = ".productNameAndPromotions a";
    private static final String TITLE_SELECTOR = ".productTitleDescriptionContainer";
    private static final String ENERGY_SELECTOR = ".nutritionTable tr.tableRow0 :first-child";
    private static final String PRICE_SELECTOR = ".pricePerUnit";
    private static final String DESCRIPTION_SELECTOR = ".productText p";

    /**
     * Method to extract product page links from search page
     * @param document search page
     * @return list of product page links
     */
    public static List<String> parseSearchPage(Document document) {
        return JsoupUtil.getElementsLinks(document, RELEVANT_PRODUCT_LINK_SELECTOR);
    }

    //TODO parse energy correctly on cherry 200 page
    //TODO parse description correctly on blackcurrants 150 page
    /**
     * Method to extract the product information from a grocery page
     * @param document product page
     * @return internal representation of a product
     */
    public static Product parseGroceryPage(Document document) {
        String title = JsoupUtil.getElementText(document, TITLE_SELECTOR);

        Elements energyElements = document.select(ENERGY_SELECTOR);
        Integer energy = energyElements.isEmpty() ? null : getEnergy(energyElements);

        BigDecimal price = getPrice(JsoupUtil.getElementText(document, PRICE_SELECTOR));

        String description = JsoupUtil.getElementText(document, DESCRIPTION_SELECTOR);

        return new Product(title, energy, price, description);
    }

    private static Integer getEnergy(Elements energyElements) {
        String energyText = energyElements.first().text();
        return Integer.parseInt(StringUtils.replace(energyText, "kcal", ""));
    }

    private static BigDecimal getPrice(String priceText) {
        String removedPoundSign = StringUtils.replace(priceText, "£", "");
        String removedUnits = StringUtils.replace(removedPoundSign, "/unit", "");
        return new BigDecimal(removedUnits);
    }
}
