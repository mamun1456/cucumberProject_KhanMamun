package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepdefinition.SharedSD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DarkSkyForecastPage extends BasePage{
    private By searchBarBox = By.xpath(".//*[@id='searchForm']/input");
    private By searchIcon = By.xpath(".//*[@id='searchForm']/a[2]/img");
    private By totalTimeBar = By.xpath(".//*[@id='timeline']/div/div[2]");
    private By initialTimeBar  = By.xpath(".//*[@id='timeline']/div/div[3]/span[1]/span");
    private By todayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(0);']");
    private By secondDayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(1);']");
    private By thirdDayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(2);']");
    private By fourthDayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(3);']");
    private By fifthDayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(4);']");
    private By sixthDayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(5);']");
    private By sevenDayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(6);']");
    private By eighthDayForecast = By.xpath("//a[@class='day' and @href='javascript:toggleDayDetails(7);']");


    public void clickOnSearchBarBox() {
        clickOn(searchBarBox);

    }
    public void clearSearchBarBox(){
        clearText(searchBarBox);
    }

    public void enterZipCode(String zipCode) {
        sendText(searchBarBox,zipCode);
    }
    public void clickSearchIcon() {
        clickOn(searchIcon);

    }
    public void verifyTimeLine(int time){
        verifyHourTime(totalTimeBar, time);
    }

    public void currentForecastDay(){
        verifyForecastDay(todayForecast);
    }
    public void secondForecastDay(){
        verifyForecastDay(secondDayForecast);
    }
    public void thirdForecastDay(){
        verifyForecastDay(thirdDayForecast);
    }
    public void fourthForecastDay(){
        verifyForecastDay(fourthDayForecast);
    }
    public void fifthForecastDay(){
        verifyForecastDay(fifthDayForecast);
    }
    public void sixthForecastDay(){
        verifyForecastDay(sixthDayForecast);
    }
    public void seventhForecastDay(){
        verifyForecastDay(sevenDayForecast);
    }
    public void eightForecastDay(){
        verifyForecastDay(eighthDayForecast);
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

}
