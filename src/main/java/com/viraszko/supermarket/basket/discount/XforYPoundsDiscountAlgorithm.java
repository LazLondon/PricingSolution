package com.viraszko.supermarket.basket.discount;

import com.viraszko.supermarket.basket.Product;
import net.jcip.annotations.Immutable;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by Laz on 17/06/2017.
 */
@Immutable
public class XforYPoundsDiscountAlgorithm implements BiFunction<Product, List<Product>, Double> {
    private final long x;
    private final double y;

    public XforYPoundsDiscountAlgorithm(long x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Double apply(Product discountedProduct, List<Product> selectedProducts) {
        long discountedProductsCandidate = selectedProducts.stream().filter(p -> p.equals(discountedProduct)).count();
        long discountCanBeAppliedCount = discountedProductsCandidate / x;
        return discountCanBeAppliedCount * (x * discountedProduct.getPrice() - y);
    }

    @Override
    public String toString() {
        return "XforYPoundsDiscountAlgorithm{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
