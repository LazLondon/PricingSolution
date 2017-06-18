package com.viraszko.supermarket.basket;

import com.viraszko.supermarket.basket.discount.Discount;
import net.jcip.annotations.ThreadSafe;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.viraszko.supermarket.basket.utility.Utility.round;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toMap;

/**
 * Created by Laz on 14/06/2017.
 */
@ThreadSafe
public final class Basket {
    private List<Product> products;
    private Map<String, Double> savings;
    private Set<Discount> discounts;

    public Basket() {
        products = new CopyOnWriteArrayList<>();
        savings = new ConcurrentHashMap<>();
        discounts = ConcurrentHashMap.newKeySet();
    }

    public void addProducts(List<Product> selectedProducts) {
        products.addAll(selectedProducts);
    }

    public void addDiscounts(Set<Discount> discounts) {
        this.discounts.addAll(discounts);
    }

    public PricingSummary createPricingSummary() {
        double subTotal = products.stream().collect(summingDouble(Product::getPrice));

        Map<String, Double> tempSavings = discounts.stream().collect(toMap(d -> d.getProduct().getName() + " " + d.getMessage(), d -> d.savings(products)));
        tempSavings.forEach((k, v) -> {
            if (v > 0) {
                savings.put(k, round(v, 2));
            }
        });
        if (savings.isEmpty()) {
            savings.put("-", 0.0);
        }

        double totalSavings = savings.values().stream().collect(summingDouble(s -> s));

        return new PricingSummary.Builder().setPricelist(products).setSubtotal(subTotal).
                setSavings(savings).setTotalSavings(totalSavings).setTotalToPay(subTotal - totalSavings).createPricingSummary();
    }
}
