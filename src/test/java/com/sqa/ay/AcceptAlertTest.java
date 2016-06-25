package com.sqa.ay;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class AcceptAlertTest extends BasicTest {
	/**
	 * @param string
	 */
	public AcceptAlertTest() {
		super("http://www.hdfcbank.com");
	}

	@Test
	public void alertTest() {
		if (isAlertPresent()) {
			Alert alert = getDriver().switchTo().alert();
			alert.dismiss();
		} else {
			System.out.println("No alert on page.." + getDriver().getTitle());
		}
		// TODO is element present
		getDriver().findElement(By.cssSelector("#loginsubmit")).submit();
	}
}
