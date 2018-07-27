package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.AmazonHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AmazonSD {

    private AmazonHomePage amazonHome = new AmazonHomePage();

    @Given("^I am on home page of amazon$")
    public void iAmOnHomePageOfDarkSky() {
        Assert.assertEquals(SharedSD.getDriver().getTitle(), "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", "I am on invalid home page");
    }

    @When("^I select (.+) from department dropdown$")
    public void iSelectSportsAndOutDoorsFromDepartment(String anyText){
        amazonHome.selectFromDepartmentDropDownBox(anyText);

    }

    @When("^I enter (.+) into search text field$")
    public void iEnterSpaldingBasketballIntoSearchTextField(String anyText){
        amazonHome.enterInSearchBox(anyText);
    }

    @When("^I click on search icon in amazon site$")
    public void iClickOnSearchIcon(){
        amazonHome.clickSearchIcon();
    }

    @Then("^I verify total search result value should be greater than 2000$")
    public void iVerifyTotalSearchResultValueShouldBeGreaterThan() {
        Assert.assertEquals(amazonHome.searchResult(),"value should be greater than 2000","Invalid Search Result");
        String value = SharedSD.getDriver().findElement(By.id("s-result-count")).getText();
        if (value.contains("2000")){
            System.out.println("Result value is expected");
        }else
        {
            System.out.println("Result is not valid as expected");
        }

    }

    @When("^I click on sign in button$")
    public void iClickOnSignInButton(){
        amazonHome.clickOnSignIn();
    }

    @When("^I click on create your amazon account on login screen$")
    public void iClickOnCreateYourAmazonAccount(){
        amazonHome.clickOnCreatAccountButton();
    }

    @When("^I enter name as (.+) on full name field on create account screen$")
    public void enterNameInTextField(String anyText){
        amazonHome.enterNameInTextField(anyText);
    }

    @When("^I enter email as (.+) on email field on create account screen$")
    public void enterEmailInEmailTextField(String anyText){
        amazonHome.enterEmail(anyText);
    }

    @When("^I enter password as (.+) on password field create account screen$")
    public void enterPasswordInPasswordField(String anyText){
        amazonHome.enterPassword(anyText);
    }

    @When("^I enter re-enter-password as (.+) on Re-enter password field on create account screen$")
    public void reenterPasswordInReenterPassWordField(String anyText){
        amazonHome.reenterPassword(anyText);
    }
    @When("^I click on create your amazon account button on create account screen$")
    public void clickOnCreateYourAmazonAccount(){
        amazonHome.clickOnAmazonAccount();
    }
    @When("^I verify error message header Important Message!$")
    public void verifyErrorMessage(){
        Assert.assertEquals(amazonHome.getFirstErrorMessage(),"Important Message!","Not Valid Message");
    }
    @Then("^I verify error message header Email address already in use$")
    public void verifyEmailErrorMessage(){
       Assert.assertEquals(amazonHome.getSecondErrorMessage(),"Email address already in use","Not Valid Message");
    }

}
