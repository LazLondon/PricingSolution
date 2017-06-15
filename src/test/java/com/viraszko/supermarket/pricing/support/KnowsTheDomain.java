package com.viraszko.supermarket.pricing.support;

import com.viraszko.supermarket.pricing.Pricing;

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
