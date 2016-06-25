package com.sqa.ay;

import java.util.concurrent.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

public class BasicTest {
	private static String baseURL;
	private static WebDriver driver;

	public static String getBaseURL() {
		return baseURL;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeClass
	public static void setupChrome() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(enabled = false)
	public static void setupFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public static void setupTest() {
		driver.get(baseURL);
		driver.manage().deleteAllCookies();

	}

	@AfterClass()
	public static void teardown() {
		driver.quit();
	}
	// @Test(dataProvider = "dp")
	// public void basicTest() {
	// }
	//
	// @DataProvider
	// public Object[][] dp() {
	// return new Object[][] {
	// new Object[] { 1, "a" },
	// new Object[] { 2, "b" },
	// };
	// }

	private boolean acceptNextAlert = true;

	public BasicTest(String baseURL) {
		BasicTest.baseURL = baseURL;
	}

	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = this.driver.switchTo().alert();
			String alertText = alert.getText();
			if (this.acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			this.acceptNextAlert = true;
		}
	}

	protected boolean isAlertPresent() {
		try {
			this.driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
