package com.viraszko.supermarket.basket;

import com.viraszko.supermarket.basket.discount.Discount;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.viraszko.supermarket.basket.utility.Utility.round;
import static java.util.stream.Collectors.toMap;

/**
 * Created by Laz on 14/06/2017.
 */
@ThreadSafe
public final class Basket {
    private final List<Product> products;
    private final Set<Discount> discounts;

    public Basket() {
        products = new CopyOnWriteArrayList<>();
        discounts = ConcurrentHashMap.newKeySet();
    }

    public void addProducts(List<Product> selectedProducts) {
        products.addAll(selectedProducts);
    }

    public void addDiscounts(Set<Discount> discounts) {
        this.discounts.addAll(discounts);
    }

    public PricingSummary createPricingSummary() {
        Map<String, Double> savings = new HashMap<>();
        double subTotal = products.stream().mapToDouble(Product::getPrice).sum();

        Map<String, Double> tempSavings = discounts.stream().collect(toMap(d -> d.getProduct().getName() + " " + d.getMessage(), d -> d.savings(products)));
        tempSavings.forEach((k, v) -> {
            if (v > 0) {
                savings.put(k, round(v, 2));
            }
        });
        if (savings.isEmpty()) {
            savings.put("-", 0.0);
        }

        double totalSavings = savings.values().stream().mapToDouble(s -> s).sum();

        return new PricingSummary.Builder().setPricelist(products).setSubtotal(subTotal).
                setSavings(savings).setTotalSavings(totalSavings).setTotalToPay(subTotal - totalSavings).createPricingSummary();
    }
}
