package framework;

import org.openqa.selenium.By;

public class AmazonHomePage extends BasePage {
    private By departmentDropDownBox = By.xpath(".//*[@id='searchDropdownBox']");
    private By searchBox = By.xpath(".//*[@id='twotabsearchtextbox']");
    private By searchIcon = By.xpath(".//*[@id='nav-search']/form/div[2]/div/input");
    private By searchResultBox = By.id("s-result-count");
    private By signInButton = By.xpath(".//*[@id='nav-link-accountList']");
    private By createAccountButton = By.xpath(".//*[@id='createAccountSubmit']");
    private By yourNameTextField = By.xpath(".//*[@id='ap_customer_name']");
    private By emailTextField = By.xpath(".//*[@id='ap_email']");
    private By passwordField = By.xpath(".//*[@id='ap_password']");
    private By reenterPasswordField = By.xpath(".//*[@id='ap_password_check']");
    private By createYourAmazonAccountButton = By.xpath(".//*[@id='continue']");
    private By firstErrorMessage = By.xpath(".//*[@id='auth-warning-message-box']/div/h4");
    private By secondErrorMessage = By.xpath(".//*[@id='a-page']/div[1]/div[3]/div/div[1]/div/div/h4");


    public void selectFromDepartmentDropDownBox(String anyText){
        selectFromDropDown(departmentDropDownBox,anyText);
    }
    public void enterInSearchBox(String anyText){
        sendText(searchBox,anyText);
    }
    public void clickSearchIcon(){
        clickOn(searchIcon);
    }
    public String searchResult(){
        return getTextFromElement(searchResultBox);
    }
    public void clickOnSignIn(){
        clickOn(signInButton);
    }
    public void clickOnCreatAccountButton(){
        clickOn(createAccountButton);
    }
    public void enterNameInTextField(String anyText){
        sendText(yourNameTextField, anyText);
    }
    public void enterEmail(String anyText){
        sendText(emailTextField,anyText);
    }
    public void enterPassword(String anyText){
        sendText(passwordField,anyText);
    }
    public void reenterPassword(String anyText){
        sendText(reenterPasswordField,anyText);

    }
    public void clickOnAmazonAccount(){
        clickOn(createYourAmazonAccountButton);
    }
    public String getFirstErrorMessage(){
        return getTextFromElement(firstErrorMessage);

    }
    public String getSecondErrorMessage(){
        return getTextFromElement(secondErrorMessage);

    }

}
