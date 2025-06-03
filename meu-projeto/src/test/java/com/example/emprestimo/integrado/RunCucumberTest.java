package com.example.emprestimo.integrado;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/features",
	glue = "com.example.emprestimo.integrado.steps",
	plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class RunCucumberTest {

}
