package com.viraszko.supermarket.basket;

import net.jcip.annotations.Immutable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Laz on 14/06/2017.
 */
@Immutable
public class PricingSummary {
    private final List<Product> pricelist;
    private final double subtotal;
    private final Map<String, Double> savings;
    private final double totalSavings;
    private final double totalToPay;

    private PricingSummary(List<Product> pricelist, double subtotal, Map<String, Double> savings, double totalSavings, double totalToPay) {
        this.pricelist = new CopyOnWriteArrayList<>(pricelist);
        this.subtotal = subtotal;
        this.savings = new ConcurrentHashMap<>(savings);
        this.totalSavings = totalSavings;
        this.totalToPay = totalToPay;
    }

    public List<Product> getPricelist() {
        return Collections.unmodifiableList(pricelist);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Map<String, Double> getSavings() {
        return Collections.unmodifiableMap(savings);
    }

    public double getTotalSavings() {
        return totalSavings;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public static class Builder {
        private List<Product> pricelist;
        private double subtotal = 0;
        private Map<String, Double> savings;
        private double totalSavings = 0;
        private double totalToPay = 0;

        public Builder setPricelist(List<Product> pricelist) {
            this.pricelist = pricelist;
            return this;
        }

        public Builder setSubtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder setSavings(Map<String, Double> savings) {
            this.savings = savings;
            return this;
        }

        public Builder setTotalSavings(double totalSavings) {
            this.totalSavings = totalSavings;
            return this;
        }

        public Builder setTotalToPay(double totalToPay) {
            this.totalToPay = totalToPay;
            return this;
        }

        public PricingSummary createPricingSummary() {
            return new PricingSummary(pricelist, subtotal, savings, totalSavings, totalToPay);
        }
    }

    @Override
    public String toString() {
        return "PricingSummary{" +
                "pricelist=" + pricelist +
                ", subtotal=" + subtotal +
                ", savings=" + savings +
                ", totalSavings=" + totalSavings +
                ", totalToPay=" + totalToPay +
                '}';
    }
}
