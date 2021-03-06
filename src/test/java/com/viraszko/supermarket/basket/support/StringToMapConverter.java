package com.viraszko.supermarket.basket.support;

import cucumber.api.Transformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laz on 15/06/2017.
 */
public class StringToMapConverter extends Transformer<Map<String, Double>> {
    @Override
    public Map<String, Double> transform(String s) {
        Map<String, Double> map = new HashMap<>();

        String discountsKeyValue[] = s.split("===");

        for (String discountKeyValue : discountsKeyValue) {
            String keyValue[] = discountKeyValue.split("->");
            String key = keyValue[0].trim();
            Double value = Double.valueOf(keyValue[1]);
            map.put(key, value);
        }
        return map;
    }
}
