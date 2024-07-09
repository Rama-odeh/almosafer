package almosafer;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MainPageTestCases extends TestData {

	public void CheckTheLanguageTest() {

		WebElement HtmlTag = driver.findElement(By.tagName("html"));
		String ActualLanguage = HtmlTag.getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedEnLanguage);

	}

	public void CheckTheCurrencyIsSARTest() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	public void CheckContactNumberTest() {

		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	public void CheckQitafLogoIsDisplayedTest() {

		WebElement FooterTag = driver.findElement(By.tagName("footer"));
		boolean ActualQitafLogo = FooterTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.tagName("svg")).isDisplayed();
		Assert.assertEquals(ActualQitafLogo, ExpectedQitafLogo);
	}

	public void CheckHotelTabIsNotSelectedTest() {
		
		String ActualHotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		Assert.assertEquals(ActualHotelTab, ExpectedHotelTab);
		
	}
	
	public void CheckDepatureAndReturnDateTest() {
		
		LocalDate today = LocalDate.now();

		int ExpectedEepatureDate = today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDate = today.plusDays(2).getDayOfMonth();

		String ActualEepatureDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText();
		String ActualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText();

		int ActualEepatureDateAsInt = Integer.parseInt(ActualEepatureDate);
		int ActualReturnDateAsInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualEepatureDateAsInt, ExpectedEepatureDate);
		Assert.assertEquals(ActualReturnDateAsInt, ExpectedReturnDate);
		
	}

	public void ChangeTheLanguageOfTheWebsiteRandomlyTest() {
		
		String[] websites = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };
		int randomIndex = rand.nextInt(websites.length);
		driver.get(websites[randomIndex]);

		if (driver.getCurrentUrl().contains("en")) {

			WebElement HtmlTag = driver.findElement(By.tagName("html"));
			String ActualLanguage = HtmlTag.getAttribute("lang");
			Assert.assertEquals(ActualLanguage, ExpectedEnLanguage);

		}

		if (driver.getCurrentUrl().contains("ar")) {

			WebElement HtmlTag = driver.findElement(By.tagName("html"));
			String ActualLanguage = HtmlTag.getAttribute("lang");
			Assert.assertEquals(ActualLanguage, ExpectedArLanguage);

		}
		
	}




}
