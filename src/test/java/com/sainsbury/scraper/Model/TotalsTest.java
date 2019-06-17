package com.sainsbury.scraper.Model;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class TotalsTest {

    @Test
    public void testSimpleCalculation() {
        BigDecimal expected = new BigDecimal("0.83");

        Totals actual = new Totals(new BigDecimal("5.00"));

        Assert.assertEquals(expected, actual.getVat());
    }

    @Test
    public void testAnotherCalculation() {
        BigDecimal expected = new BigDecimal("2.08");

        Totals actual = new Totals(new BigDecimal("12.50"));

        Assert.assertEquals(expected, actual.getVat());
    }
}