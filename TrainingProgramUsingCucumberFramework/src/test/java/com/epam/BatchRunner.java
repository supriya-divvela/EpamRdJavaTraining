package com.epam;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "com/epam/features",glue= {"com.epam.stepDefinition"},monochrome = true)
public class BatchRunner {
}