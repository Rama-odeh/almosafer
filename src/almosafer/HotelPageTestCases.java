package almosafer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HotelPageTestCases extends TestData {

	public void HotelSelectionTest() {

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

	public void HotelNumberSelectOfPeopleCheck() {

		WebElement myElement = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select mySelect = new Select(myElement);

		int randIndex = rand.nextInt(2);

		mySelect.selectByIndex(randIndex);

		driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
	}

	public void HotelPageISFullyLoadedTest() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		WebElement resultTab = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));

		Assert.assertEquals(resultTab.getText().contains("found") || resultTab.getText().contains("وجدنا"), true);
	}

	public void HotelsortItemsTest() throws InterruptedException {

		WebElement lowestPriceButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		lowestPriceButton.click();

		Thread.sleep(2000);

		WebElement pricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> prices = pricesContainer.findElements(By.className("Price__Value"));

		String firstPrice = prices.get(0).getText();
		String lastPrice = prices.get(prices.size() - 1).getText();

		int firstPriceAsInt = Integer.parseInt(firstPrice);
		int lastPriceAsInt = Integer.parseInt(lastPrice);

		Assert.assertEquals(firstPriceAsInt < lastPriceAsInt, true);

	}

}
