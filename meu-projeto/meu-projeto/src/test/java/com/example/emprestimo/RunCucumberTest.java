package com.example.emprestimo;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources",
	glue = "com.example.emprestimo.steps",
	plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class RunCucumberTest {

}
