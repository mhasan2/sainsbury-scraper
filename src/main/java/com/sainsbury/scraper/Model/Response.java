package com.sainsbury.scraper.Model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Response {

    private final List<Product> results;

    private final Totals total;
}
