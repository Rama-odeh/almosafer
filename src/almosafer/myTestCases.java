package almosafer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {
	
	WebDriver driver = new ChromeDriver();
	String url = "https://global.almosafer.com/en";
	@BeforeTest
	public void mySetup() {
		
		driver.get(url);
	}
	
	
	@Test
	public void firstTest() {
		
	}
}
