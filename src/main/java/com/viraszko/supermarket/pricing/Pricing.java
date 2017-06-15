package com.viraszko.supermarket.pricing;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.summingDouble;

/**
 * Created by Laz on 14/06/2017.
 */
public final class Pricing {
    private List<Product> products;
    private Map<String, Double> savings;

    public Pricing(){
        products = new CopyOnWriteArrayList<>();
        savings = new ConcurrentHashMap<>();
        savings.put("-", 0.00);
    }

    public void addProducts(List<Product> selectedProducts) {
        products = new CopyOnWriteArrayList<>(selectedProducts);
    }

    public PricingSummary createPricingSummary() {
        double subTotal = products.stream().collect(summingDouble(Product::getPrice));
        double totalSavings = savings.values().stream().collect(summingDouble(s->s));

        return new PricingSummary.Builder().setPricelist(products).setSubtotal(subTotal).
                setSavings(savings).setTotalSavings(totalSavings).setTotalToPay(subTotal + totalSavings).createPricingSummary();
    }
}
