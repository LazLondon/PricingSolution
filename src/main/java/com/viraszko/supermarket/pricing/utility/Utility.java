package com.viraszko.supermarket.pricing.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Laz on 18/06/2017.
 */
public class Utility {

    private Utility() {
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
