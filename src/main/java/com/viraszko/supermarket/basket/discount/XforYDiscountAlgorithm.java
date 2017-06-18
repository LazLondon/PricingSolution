package com.viraszko.supermarket.basket.discount;

import com.viraszko.supermarket.basket.Product;
import net.jcip.annotations.Immutable;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by Laz on 17/06/2017.
 */
@Immutable
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
        long discountCanBeAppliedCount = discountedProductsCandidate / x;
        return discountCanBeAppliedCount * (x - y) * discountedProduct.getPrice();
    }

    @Override
    public String toString() {
        return "XforYDiscountAlgorithm{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
