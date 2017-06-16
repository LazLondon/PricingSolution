package com.viraszko.supermarket.pricing;

import net.jcip.annotations.ThreadSafe;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Laz on 14/06/2017.
 */
@ThreadSafe
public class Product {
    private String name;
    private volatile double price;
    private volatile double unitPrice;
    private volatile double amount;

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

    public double getUnitPrice() {return unitPrice;}

    public double getPrice() {
        return round(unitPrice * amount, 2);
    }

    public double getAmount() {
        return amount;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
