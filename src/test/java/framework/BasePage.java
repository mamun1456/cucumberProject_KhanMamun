package framework;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BasePage {



	public static WebElement webAction(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SharedSD.getDriver())
				.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(java.util.NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotFoundException.class)
				.withMessage(
						"Webdriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown");
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	public void clickOn(By locator) {
		try {
			SharedSD.getDriver().findElement(locator).click();
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
	}
	public void sendText(By locator, String text) {
		try {
			SharedSD.getDriver().findElement(locator).sendKeys(text);
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
	}
	public String getTextFromElement(By locator) {
		String text = null;
		try {
			text = SharedSD.getDriver().findElement(locator).getText();
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
		return text;
	}
	public void clearText(By locator){
		try {

			SharedSD.getDriver().findElement(locator).sendKeys(Keys.chord(Keys.CONTROL,"a"));
			SharedSD.getDriver().findElement(locator).sendKeys(Keys.BACK_SPACE);
		}
		catch(Exception e){
			Assert.fail("Eelement can not be selected : " + locator.toString());
			e.printStackTrace();

		}
	}
	public void selectAutoComplete(By autoCompleteTextField, String partialText, By suggestedList, String valueToBeSelected) {
		try {
			SharedSD.getDriver().findElement(autoCompleteTextField).sendKeys(partialText);
		} catch (NoSuchElementException e){
			Assert.fail("Elelment is not found with this locator : " + autoCompleteTextField.toString());
		}
		List<WebElement> list = SharedSD.getDriver().findElements(suggestedList);
		for (WebElement ele : list){
			if (ele.getText().contains(valueToBeSelected)){
				ele.click();
				break;
			}
		}
	}
	public void selectCurrentDateFromCalender(String datePattern, By calenderField, By listOfDays){
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		Date date =new Date();
		String todayDate = sdf.format(date);
		webAction(calenderField).click();
		List<WebElement> days = SharedSD.getDriver().findElements(listOfDays);
		for (WebElement day : days){
			String expectedDay = day.getText();
			if (expectedDay.equals(todayDate)){
				day.click();
				break;
			}
		}
	}
	public void selectReurnDateFromCalender(String datePattern, By calenderField, int amountToAdd, By listOfDays){
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,amountToAdd);
		date = cal.getTime();
		String endDate = sdf.format(date);
		webAction(calenderField).click();
		//Fluent wait is not implemented here
		List<WebElement> days = SharedSD.getDriver().findElements(listOfDays);
		for (WebElement day : days) {
			String expectedDay = day.getText();
			if (expectedDay.contains(endDate)) {
				day.click();
				break;
			}
		}
	}

	public void verifyHourTime(By locator, int time) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, Calendar.HOUR);

		SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
		for (int i = 0; i < 12; i++) {
			System.out.println(hourFormat.format(c.getTime()));
			int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
			if (hourOfDay == Calendar.HOUR) {
				c.add(Calendar.HOUR, 2);
			} else if (hourOfDay == Calendar.HOUR_OF_DAY) {
				c.add(Calendar.HOUR, 2);
			} else {
				c.add(Calendar.HOUR, 2);
			}
		}
	}

		//Gets expected result using calendar
		public List<String> getExpectedResult() {

			ArrayList<String> expectedTime = new ArrayList<>();
			expectedTime.add("Now");
			Calendar now = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("ha");

			for (int i = 0; i < 11; i++) {
				now.add(Calendar.HOUR_OF_DAY, 2);
				expectedTime.add(dateFormat.format(now.getTime()).toLowerCase());
			}

			return expectedTime;
		}

		// Gets actual result from the website
		public List<String> getActualResult() throws InterruptedException {

			Thread.sleep(2000);
			List<WebElement> list = SharedSD.getDriver().findElements(By.xpath(".//*[@id='timeline']/div/div[3]"));
			Thread.sleep(2000);
			String actualHour = webAction(By.xpath(".//*[@id='timeline']/div/div[3]")).getText();
			System.out.println(actualHour);

			String[] actualHourArray = actualHour.split("\n", 12);

			List<String> wordList = Arrays.asList(actualHourArray);

			return wordList;
		}

	public void verifyForecastDay(By locator){
		Date day = new Date();
		if(day.equals("Today")){
			System.out.println("Today is current day");

		}else if(day.equals("Mon")){
			System.out.println("Monday");
		}else if(day.equals("Tue")){
			System.out.println("Tuesday");
		}else if(day.equals("Wed")){
			System.out.println("Wednesday");
		}else if(day.equals("Thu")){
			System.out.println("Thursday");
		}else if(day.equals("Fri")){
			System.out.println("Friday");
		}else if(day.equals("Sat")){
			System.out.println("Tuesday");
		}else if(day.equals("Sun")){
			System.out.println("Sunday");
		}else{
			System.out.println("Days are not in order");
		}

	}

	public void selectFromDropDown(By dropdownField, String visibleText) {
		// Select value from drop
		Select dropdown = new Select(webAction(dropdownField));
		// Select element by visible text
		dropdown.selectByVisibleText(visibleText);
	}
	public void selectFromDropDown(By dropdownField, int index) {
		// Select value from drop
		Select dropdown = new Select(webAction(dropdownField));
		// Select element by index
		dropdown.selectByIndex(index);
	}
	public void mouseOver(By hoverOverElement) {
		WebElement element = webAction(hoverOverElement);
		Actions action = new Actions(SharedSD.getDriver());
		action.moveToElement(element).build().perform();
	}
	// Switches to window based of index
	public static void switchToWidnow(int index) {
		List<String> listOfWindows = new ArrayList<String>(SharedSD.getDriver().getWindowHandles());
		SharedSD.getDriver().switchTo().window(listOfWindows.get(index));
	}
	// Closes current window and switches back to root window
	public static void switchToRootWindowAndCloseCurrentBrowser() {
		List<String> listOfWindows = new ArrayList<String>(SharedSD.getDriver().getWindowHandles());
		for(int i = 1; i < listOfWindows.size(); i++){
			SharedSD.getDriver().switchTo().window(listOfWindows.get(i));
			SharedSD.getDriver().close();
		}
		SharedSD.getDriver().switchTo().window(listOfWindows.get(0));
	}
	public void clickOnBrowserBackArrow() {
		SharedSD.getDriver().navigate().back();
	}
	public void clickOnBrowserForwardArrow() {
		SharedSD.getDriver().navigate().forward();
	}
	public void refreshBrowser() {
		SharedSD.getDriver().navigate().refresh();
	}
}
