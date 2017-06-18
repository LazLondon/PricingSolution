package com.viraszko.supermarket.basket.support;

import com.viraszko.supermarket.basket.Basket;

/**
 * Created by Laz on 14/06/2017.
 */
public class KnowsTheDomain {
    private Basket basket;

    public Basket getBasket() {
        if (basket == null) {
            basket = new Basket();
        }
        return basket;
    }
}
