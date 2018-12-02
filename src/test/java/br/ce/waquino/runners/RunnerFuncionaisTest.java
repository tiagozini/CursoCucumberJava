package br.ce.waquino.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/main/resources/features/",
		glue="br.ce.waquino.steps",
		tags={"~@ignore","@funcionais"},
		plugin={"pretty", "html:target/report-html", "json:target/report.json"}, 
		monochrome=true,
		snippets=SnippetType.CAMELCASE,
		dryRun=false,
		strict=false)
public class RunnerFuncionaisTest {
	@BeforeClass
	public static void reset() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://srbarriga.herokuapp.com");	
		driver.findElement(By.id("email")).sendKeys("tiagozini@gmail.com");
		driver.findElement(By.name("senha")).sendKeys("12345678");	
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.linkText("reset")).click();
		driver.quit();
	}
}
