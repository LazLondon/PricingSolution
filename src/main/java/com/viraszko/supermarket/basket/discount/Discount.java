package com.viraszko.supermarket.basket.discount;

import com.viraszko.supermarket.basket.Product;
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
        this.product = product;
        this.message = message;
        this.algorithm = algorithm;
    }

    public Product getProduct() { return product; }

    public String getMessage() {
        return message;
    }

    public double savings(List<Product> products) {
        return algorithm.apply(product, products);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "product=" + product +
                ", message='" + message + '\'' +
                ", algorithm=" + algorithm +
                '}';
    }
}
