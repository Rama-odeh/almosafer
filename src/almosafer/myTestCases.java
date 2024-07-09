package almosafer;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends TestData {

	MainPageTestCases TC = new MainPageTestCases();
	HotelPageTestCases HTC = new HotelPageTestCases();

	@BeforeTest
	public void mySetup() {

		TheDeafultConfiguration();
	}

	@Test(description = "MainPageTest", priority = 1)
	public void CheckTheLanguage() {

		TC.CheckTheLanguageTest();
	}

	@Test(description = "MainPageTest", priority = 2)
	public void CheckTheCurrencyIsSAR() {

		TC.CheckTheCurrencyIsSARTest();
	}

	@Test(description = "MainPageTest", priority = 3)
	public void CheckContactNumber() {

		TC.CheckContactNumberTest();

	}

	@Test(description = "MainPageTest", priority = 4)
	public void checkQitafLogoIsDisplayed() {

		TC.CheckQitafLogoIsDisplayedTest();

	}

	@Test(description = "MainPageTest", priority = 5)
	public void checkHotelTabIsNotSelected() {

		TC.CheckHotelTabIsNotSelectedTest();

	}

	@Test(description = "MainPageTest", priority = 6)
	public void checkDepatureAndReturnDate() {

		TC.CheckDepatureAndReturnDateTest();
	}

	@Test(description = "MainPageTest", priority = 7)
	public void changeTheLanguageOfTheWebsiteRandomly() {

		TC.ChangeTheLanguageOfTheWebsiteRandomlyTest();
	}

	@Test(description = "HotelPageTest", priority = 8)
	public void HotelSelection() {

		HTC.HotelSelectionTest();

	}

	@Test(description = "HotelPageTest", priority = 9)
	public void selectNumberOfPeople() {

		HTC.HotelNumberSelectOfPeopleCheck();

	}

	@Test(description = "HotelPageTest", priority = 10)
	public void checkThePageISFullyLoaded() {

		HTC.HotelPageISFullyLoadedTest();

	}

	@Test(description = "HotelPageTest", priority = 11)
	public void sortItems() throws InterruptedException {

		HTC.HotelsortItemsTest();
	}

}
