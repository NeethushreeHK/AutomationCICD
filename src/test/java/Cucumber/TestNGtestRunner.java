package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",//feature file path
glue="rahul.stepDefinitions",//stepdefinition.java path
monochrome=true,//o/p will be in encrypted form, to convert to readable
tags= "@errovalidation",// tagging to run specific feature files
plugin= {"html:target/cucumber.html"})// which report we need and where it should be created
public class TestNGtestRunner extends AbstractTestNGCucumberTests {
}