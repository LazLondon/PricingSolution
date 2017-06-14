package com.viraszko.supermarket.pricing.steps;

import com.viraszko.supermarket.pricing.support.KnowsTheDomain;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Laz on 14/06/2017.
 */
public class PricingSteps {
    Map<String, Double> pricelist;
    private Pricing pricing;

    public PricingSteps(KnowsTheDomain helper) {
        pricing = helper.getPricing();
    }

    @Given("^a list of products and their prices$")
    public void createPricelist(DataTable rawPricelist) throws Throwable {
        pricelist = new HashMap<>();
        for (List<String> row : rawPricelist.raw()) {
            String product = row.get(0);
            double price = Double.parseDouble(row.get(1));
            pricelist.put(product, price);
        }
    }

    @When("^I receive a list of ([^\"]*)$")
    public void addSelectedProductsToPricing(List<String> selectedProductsName) throws Throwable {
        List<Product> selectedProducts = new ArrayList<>();
        for (String selectedProductName : selectedProductsName) {
            selectedProducts.add(new Product(selectedProductName, pricelist.get(selectedProductName)));
        }
        pricing.addProducts(selectedProducts);
    }

    @Then("^I want to create a pricing summary containing ([^\"]*), a ([^\"]*), a ([^\"]*), a ([^\"]*) and a ([^\"]*)$")
    public void createPricingSummary(List<String> pricelist, double subtotal, double savings, double totalSavings, double totalToPay) throws Throwable {
        PricingSummary pricingSummary = pricing.createPricingSummary();

        assertEquals(pricelist, pricingSummary.getPricelist());
        assertEquals(subtotal, pricingSummary.getSubtotal(), 0);
        assertEquals(savings, pricingSummary.getSavings()), 0;
        assertEquals(totalSavings, pricingSummary.getTotalSavings(), 0);
        assertEquals(totalToPay, pricingSummary.getTotalToPay(), 0);

    }
}
