package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.DarkSkyForecastPage;
import org.testng.Assert;

public class ForecastSD {
    private DarkSkyForecastPage forecastPage = new DarkSkyForecastPage();


    @Given("^I am on home page of darksky$")
    public void iAmOnHomePageOfDarkSky() {
        Assert.assertEquals(SharedSD.getDriver().getTitle(), "Dark Sky - 260 Broadway, New York City, NY", "I am on invalid home page");
    }

    @When("^I click on search bar text field$")
    public void clickOnSearchBarTextField() {
        forecastPage.clickOnSearchBarBox();
    }
    @When("^I clear search bar text field$")
    public void clearSearchBox(){
        forecastPage.clearSearchBarBox();
    }

    @When("^I enter (.+) in search bar text field$")
    public void enterZipCodeInSearchBarTextField(String anyText) {
        forecastPage.enterZipCode(anyText);
    }

    @When("^I click on search icon$")
    public void clickOnSearchIcon(){
        forecastPage.clickSearchIcon();
    }

    @Then("^Hour timeline value should display 24 hours with ([0-9]) hours increment$")
    public void hourTimelineValueShouldDisplayHoursWithHoursIncrement(int time) {
        forecastPage.verifyTimeLine(time);

    }

    @Then("^I verify forcast days (.+) are displayed in correct order starting from current day$")
    public void verifyForecastDayDisplayedInCorrectOrder(String dayFields){

            switch (dayFields) {
                case "Today":
                    forecastPage.currentForecastDay();
                    break;
                case "second day":
                    forecastPage.secondForecastDay();
                    break;
                case "third day":
                    forecastPage.thirdForecastDay();
                    break;
                case "fourth day":
                    forecastPage.fourthForecastDay();
                    break;
                case "fifth day":
                    forecastPage.fifthForecastDay();
                    break;
                case "sixth day":
                    forecastPage.sixthForecastDay();
                    break;
                case "seventh day":
                    forecastPage.seventhForecastDay();
                    break;
                case "eighth day":
                    forecastPage.eightForecastDay();
                    break;
            }
        }

    }
