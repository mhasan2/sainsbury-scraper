package com.sainsbury.scraper.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Totals {

    public static final BigDecimal VAT_PERCENT = new BigDecimal("20");
    private static final BigDecimal VAT_DIVISOR;
    static {
        BigDecimal oneHundred = new BigDecimal("100.00");
        VAT_DIVISOR = oneHundred.add(VAT_PERCENT).divide(VAT_PERCENT, RoundingMode.HALF_UP);
    }

    private final BigDecimal gross;

    private final BigDecimal vat;

    public Totals(BigDecimal gross) {
        this.gross = gross;
        this.vat = gross.divide(VAT_DIVISOR, 2, RoundingMode.HALF_UP);
    }
}
