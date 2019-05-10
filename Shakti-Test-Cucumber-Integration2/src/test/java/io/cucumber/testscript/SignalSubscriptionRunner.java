package io.cucumber.testscript;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"classpath:io/cucumber/skeleton/SignalSubscription.feature"}  ,glue =  "io.cucumber.skeleton")
public class SignalSubscriptionRunner {

}
