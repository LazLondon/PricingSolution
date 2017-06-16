package com.viraszko.supermarket.pricing.steps;

import com.viraszko.supermarket.pricing.Pricing;
import com.viraszko.supermarket.pricing.PricingSummary;
import com.viraszko.supermarket.pricing.Product;
import com.viraszko.supermarket.pricing.support.KnowsTheDomain;
import com.viraszko.supermarket.pricing.support.StringToMapConverter;
import cucumber.api.DataTable;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

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

    @When("^I receive a list of (.*)$")
    public void addSelectedProductsToPricing(List<String> selectedProductsNameAmount) throws Throwable {
        List<Product> selectedProducts = new ArrayList<>();
        for (String selectedProductNameAmount : selectedProductsNameAmount) {
            String nameAmount[] = selectedProductNameAmount.split("\\s*-\\s*");
            selectedProducts.add(new Product(nameAmount[0], pricelist.get(nameAmount[0]), nameAmount.length > 1 ? Double.parseDouble(nameAmount[1]) : 1));
        }
        pricing.addProducts(selectedProducts);
    }

    @Then("^I want to create a pricing summary containing (.*), (.*), (.*), (.*), (.*)$")
    public void createPricingSummary(List<Double> pricelist, double subtotal, @Transform(StringToMapConverter.class ) Map<String, Double> savings, double totalSavings, double totalToPay) throws Throwable {

        PricingSummary pricingSummary = pricing.createPricingSummary();

        assertEquals(pricelist, pricingSummary.getPricelist().stream().map(Product::getPrice).collect(Collectors.toList()));
        assertEquals(subtotal, pricingSummary.getSubtotal(), 0);
        assertEquals(savings, pricingSummary.getSavings());
        assertEquals(totalSavings, pricingSummary.getTotalSavings(), 0);
        assertEquals(totalToPay, pricingSummary.getTotalToPay(), 0);

    }
}
