@final_project
Feature Final Project

  #Below two scenarios are from website https://darksky.net
  @darksky-1
  Scenario: Verify 24 hours timeline hour values are displayed correctly
    Given I am on home page of darksky
    When I enter "10001" in search bar text field
    And I click on search icon
    Then Hour timeline value should display 24 hours with 2 hours increment

  @darksky-2  
  Scenario: Verify 12 hours timeline hour values are displayed correctly
    Given I am on home page of darksky
    When I enter "10001" in search bar text field
    And I click on search icon in darksky site
    Then I verify forcast days are displayed in correct order starting from current day

     #Below two scenarios are from website https://amazon.com
  @amazon-1
  Scenario: Verify amazon search result
    Given I am on home page of amazon
    When I select "Sports and Outdoors" from department dropdown
    And I enter "spalding basketball" into search text field
    And I click on search icon in amazon site
    Then I verify total search result value should be greater than 2000

  @amazon-2
  Scenario: Verify invalid registration in amazon
    Given I am on home page of amazon
    When I click on sign in button
    And I click on create your amazon account on login screen
    And I enter name as "Khan Mamun" on full name field on create account screen
    And I enter email as "khan_mamun@gmail.com" on email field on create account screen
    And I enter password as "test12345" on password field create account screen
    And I enter re-enter-password as "test12345" on Re-enter password field on create account screen
    And I click on create your amazon account button on create account screen
    Then I verify error message header "Important Message!"
    And I verify error message header "Email address already in use"