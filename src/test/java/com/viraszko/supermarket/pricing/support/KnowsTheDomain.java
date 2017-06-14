package com.viraszko.supermarket.pricing.support;

/**
 * Created by Laz on 14/06/2017.
 */
public class KnowsTheDomain {
    private Pricing pricing;

    public Pricing getPricing() {
        if (pricing == null) {
            pricing = new Pricing();
        }
        return pricing;
    }
}
