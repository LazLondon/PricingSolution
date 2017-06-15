package com.viraszko.supermarket.pricing;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by Laz on 14/06/2017.
 */
@ThreadSafe
public class Product {
    private String name;
    private volatile double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
