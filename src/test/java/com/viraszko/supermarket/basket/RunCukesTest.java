package com.viraszko.supermarket.basket;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Laz on 14/06/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty", "html:target/cucumber" }, snippets=SnippetType.CAMELCASE, features = "classpath:features/pricing.feature", glue="com.viraszko.supermarket.basket.steps")
public class RunCukesTest {

}