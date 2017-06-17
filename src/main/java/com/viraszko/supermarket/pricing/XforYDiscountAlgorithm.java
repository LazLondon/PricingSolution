package com.viraszko.supermarket.pricing;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by Laz on 17/06/2017.
 */
public class XforYDiscountAlgorithm implements BiFunction<Product, List<Product>, Double> {
    private long x;
    private long y;

    public XforYDiscountAlgorithm(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Double apply(Product discountedProduct, List<Product> selectedProducts) {
        long discountedProductsCandidate = selectedProducts.stream().filter(p -> p.equals(discountedProduct)).count();
        long discountCanBeAppliedCount = (long) (discountedProductsCandidate / x);
        return discountCanBeAppliedCount * (x - y) * discountedProduct.getPrice();
    }
}
