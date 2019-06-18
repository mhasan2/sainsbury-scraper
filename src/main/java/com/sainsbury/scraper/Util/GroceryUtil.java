package com.sainsbury.scraper.Util;

import com.sainsbury.scraper.Model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class GroceryUtil {

    private static final String RELEVANT_PRODUCT_LINK_SELECTOR = ".productNameAndPromotions a";
    private static final String TITLE_SELECTOR = ".productTitleDescriptionContainer";
    private static final String ENERGY_SELECTOR = ".nutritionTable tr.tableRow0 :first-child";
    private static final String ALTERNATIVE_ENERGY_SELECTOR = ".nutritionTable th:contains(kcal)+td";
    private static final String PRICE_SELECTOR = ".pricePerUnit";
    private static final String DESCRIPTION_SELECTOR = ".productText p:not(.statements)";

    private static final Logger LOG = LoggerFactory.getLogger(GroceryUtil.class);

    /**
     * Method to extract product page links from search page
     * @param document search page
     * @return list of product page links
     */
    public static List<String> parseSearchPage(Document document) {
        return JsoupUtil.getElementsLinks(document, RELEVANT_PRODUCT_LINK_SELECTOR);
    }

    /**
     * Method to extract the product information from a grocery page
     * @param document product page
     * @return internal representation of a product
     */
    public static Product parseGroceryPage(Document document) {
        String title = getTitle(document);

        Integer energy = getEnergy(document);

        BigDecimal price = getPrice(document);

        String description = getDescription(document);

        return new Product(title, energy, price, description);
    }


    private static String getTitle(Document document) {
        Elements titleElements = document.select(TITLE_SELECTOR);

        if (titleElements.isEmpty()) {
            LOG.error(String.format("Unable to parse a title for product at url: %s", document.baseUri()));
            return null;
        }

        String title = titleElements.first().text();

        if (!StringUtils.hasText(title)) {
            LOG.error(String.format("Unable to parse a title for product at url: %s", document.baseUri()));
            return "";
        }

        return title;
    }


    private static String getDescription(Document document) {
        Elements descriptionElements = document.select(DESCRIPTION_SELECTOR);

        if (descriptionElements.isEmpty()) {
            LOG.error(String.format("Unable to parse a description for product at url: %s", document.baseUri()));
            return null;
        }

        String description = descriptionElements.first().text();

        if (!StringUtils.hasText(description)) {
            LOG.error(String.format("Unable to parse a description for product at url: %s", document.baseUri()));
            return "";
        }

        return description;
    }


    private static Integer getEnergy(Document document) {
        try {
            Elements energyElements = document.select(ENERGY_SELECTOR);
            if (!energyElements.isEmpty()) {
                String energyText = energyElements.first().text();
                return Integer.parseInt(removeChars(energyText, "kcal"));
            }

            Elements alternativeEnergyElements = document.select(ALTERNATIVE_ENERGY_SELECTOR);
            if (!alternativeEnergyElements.isEmpty()) {
                String energyText = alternativeEnergyElements.first().text();
                return Integer.parseInt(removeChars(energyText, "kcal"));
            }
        }
        catch (NumberFormatException e){
            LOG.error(String.format("Unable to parse an energy value for product at url: %s", document.baseUri()), e.getMessage());
        }

        return null;
    }


    private static BigDecimal getPrice(Document document) {
        try {
            String priceText = JsoupUtil.getElementText(document, PRICE_SELECTOR);
            String removedPoundSign = removeChars(priceText, "£");
            String removedUnits = removeChars(removedPoundSign, "/unit");
            return new BigDecimal(removedUnits);
        }
        catch (NumberFormatException | NullPointerException e) {
            LOG.error(String.format("Unable to parse a price for product at url: %s", document.baseUri()), e.getMessage());
            return BigDecimal.ZERO;
        }
    }

    private static String removeChars(String input, String remove) {
        return StringUtils.replace(input, remove, "");
    }
}
