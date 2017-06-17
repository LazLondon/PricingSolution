package com.viraszko.supermarket.pricing.discount;

import com.viraszko.supermarket.pricing.Product;
import net.jcip.annotations.Immutable;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by Laz on 17/06/2017.
 */
@Immutable
public class Discount {
    private final Product product;
    private final String message;
    private final BiFunction<Product, List<Product>, Double> algorithm;

    public Discount(Product product, String message, BiFunction<Product, List<Product>, Double> algorithm) {
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
