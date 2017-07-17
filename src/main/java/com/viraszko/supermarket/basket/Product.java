package com.viraszko.supermarket.basket;

import net.jcip.annotations.Immutable;

import static com.viraszko.supermarket.basket.utility.Utility.round;


/**
 * Created by Laz on 14/06/2017.
 */
@Immutable
public class Product {
    // TODO name is treated as unique ID !!!
    private final String name;
    private final double unitPrice;
    private final double amount;

    public Product(String name, double unitPrice) {
        this(name, unitPrice, 1);
    }

    public Product(String name, double unitPrice, double amount) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getPrice() {
        return round(unitPrice * amount, 2);
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                '}';
    }
}
