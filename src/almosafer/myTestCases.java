package almosafer;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	String url = "https://global.almosafer.com/en";
	String ExpectedLanguage = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	boolean ExpectedQitafLogo = true;
	String ExpectedHotelTab = "false";
	String ExpectedEnLanguage = "en";
	String ExpectedArLanguage = "ar";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	}

	@Test(priority = 1)
	public void TestTheLanguage() {
		WebElement HtmlTag = driver.findElement(By.tagName("html"));
		String ActualLanguage = HtmlTag.getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);

	}

	@Test(priority = 2)
	public void TestTheCurrencyIsSAR() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void TestContactNumber() {

		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);

	}

	@Test(priority = 4)
	public void checkQitafLogoIsDisplayed() {

		WebElement FooterTag = driver.findElement(By.tagName("footer"));
		boolean ActualQitafLogo = FooterTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.tagName("svg")).isDisplayed();
		Assert.assertEquals(ActualQitafLogo, ExpectedQitafLogo);

	}

	@Test(priority = 5)
	public void checkHotelTabIsNotSelected() {

		String ActualHotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		Assert.assertEquals(ActualHotelTab, ExpectedHotelTab);

	}

	@Test(priority = 6)
	public void checkDepatureAndReturnDate() {

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

	@Test(priority = 7)
	public void changeTheLanguageOfTheWebsiteRandomly() {

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

	@Test(priority = 8)
	public void HotelSelection() {

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		WebElement searchForInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().contains("en")) {

			String[] EnCities = { "Dubai", "Jeddah", "Riyadh" };
			int randSearch = rand.nextInt(EnCities.length);
			searchForInput.sendKeys(EnCities[randSearch]);
		}

		else if (driver.getCurrentUrl().contains("ar")) {

			String[] ArCities = { "دبي", "جدة" };
			int randSearch = rand.nextInt(ArCities.length);
			searchForInput.sendKeys(ArCities[randSearch]);

		}

	}

	@Test(priority = 9)
	public void selectNumberOfPeople() {

		WebElement myElement = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select mySelect = new Select(myElement);

		int randIndex = rand.nextInt(2);

		mySelect.selectByIndex(randIndex);
		
		 driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
		
	}
	
	@Test(priority=10)
	public void checkThePageISFullyLoaded() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		WebElement resultTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));
		
		Assert.assertEquals(resultTab.getText().contains("found") || resultTab.getText().contains("وجدنا"), true);
		
	}
	
	@Test(priority =11)
	public void sortItems() throws InterruptedException {
		
		WebElement lowestPriceButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		lowestPriceButton.click();
		
		Thread.sleep(2000);
		
		WebElement pricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List <WebElement> prices = pricesContainer.findElements(By.className("Price__Value"));
		
		String firstPrice = prices.get(0).getText();
		String lastPrice = prices.get(prices.size()-1).getText();
		
		int firstPriceAsInt = Integer.parseInt(firstPrice);
		int lastPriceAsInt = Integer.parseInt(lastPrice);
		
		Assert.assertEquals(firstPriceAsInt < lastPriceAsInt , true);
		
		
	
		
		
		
		
		
		
	}

}
