package com.viraszko.supermarket.pricing;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Laz on 14/06/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin="pretty", snippets=SnippetType.CAMELCASE, features ="src/test/resources/features", glue={"com.viraszko.supermarket.pricing.steps"})
public class RunCukesTests {

}