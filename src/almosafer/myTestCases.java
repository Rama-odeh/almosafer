package almosafer;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	String url = "https://global.almosafer.com/en";
	String ExpectedLanguage = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";

	@BeforeTest
	public void mySetup() {

		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	}

	@Test
	public void TestTheLanguage() {
		WebElement HtmlTag = driver.findElement(By.tagName("html"));
		String ActualLanguage = HtmlTag.getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);

	}
	
	@Test
	public void TestTheCurrencyIsSAR() {
		
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}
	
	@Test
	public void TestContactNumber() {
		
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
		
		
	}
}
