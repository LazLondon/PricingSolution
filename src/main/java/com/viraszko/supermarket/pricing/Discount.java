package com.viraszko.supermarket.pricing;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by Laz on 17/06/2017.
 */
public class Discount {
    private Product product;
    private String message;
    BiFunction<Product, List<Product>, Double> algorithm;

    public Discount(Product product, String message, BiFunction<Product, List<Product>, Double> algorithm) {//}, TriFunction<List<Product>, Long, Number, Double> algorithm) {
        this.product = new Product(product);
        this.message = message;
        this.algorithm = algorithm;
    }

    public Product getProduct() {
        return new Product(product);
    }

    public String getMessage() {
        return message;
    }

    public double savings(List<Product> products) {
        return algorithm.apply(product, products);
    }

}
