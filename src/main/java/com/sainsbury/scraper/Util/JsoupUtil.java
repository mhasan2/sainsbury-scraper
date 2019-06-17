package com.sainsbury.scraper.Util;

import java.util.List;
import org.jsoup.nodes.Document;

public class JsoupUtil {
    private static final String ABSOLUTE_LINK_SELECTOR = "abs:href";

    /**
     * Method to return links from all the selected elements
     * @param document page to be searched
     * @param selector to find {@code a} elements
     * @return list of links found
     */
    public static List<String> getElementsLinks(Document document, String selector) {
        return document.select(selector).eachAttr(ABSOLUTE_LINK_SELECTOR);
    }

    /**
     * Method to return text inside the first selected element
     * @param document page to be searched
     * @param selector to find the element
     * @return text content of the first element found
     */
    public static String getElementText(Document document, String selector) {
        return document.select(selector).first().text();
    }
}
