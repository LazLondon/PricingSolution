package com.viraszko.supermarket.pricing;

import java.util.List;

/**
 * Created by Laz on 17/06/2017.
 */
public enum DiscountAlgorithms implements TriFunction<List<Product>, Long, Number, Double> {
    XFORY((products, x, y) -> {
        long discountCanBeAppliedCount = (long) (products.size() / x);
        long remainder = products.size() % x;
        return new Double(discountCanBeAppliedCount * y.longValue() + remainder);
    });

    private TriFunction<List<Product>, Long, Number, Double> algorithm;

    DiscountAlgorithms(TriFunction<List<Product>, Long, Number, Double> algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Double apply(List<Product> products, Long x, Number y) {
        return algorithm.apply(products, x, y);
    }

}
