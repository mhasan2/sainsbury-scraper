package com.sainsbury.scraper.Model;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    private final String title;

    @SerializedName("kcal_per_100g")
    private final Integer energy;

    @SerializedName("unit_price")
    private final BigDecimal price;

    private final String description;

    public BigDecimal getPrice() {
        return price;
    }
}
