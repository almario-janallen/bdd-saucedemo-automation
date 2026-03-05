Feature: Negative Login Scenarios
  As a security-conscious application
  I want to restrict access to unauthorized users
  So that only valid users can access the product catalog

  Background:
    Given I am on the login page

  @regression
  Scenario: Locked out user cannot log in
    When I enter username "locked_out_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see the error message "Epic sadface: Sorry, this user has been locked out."

  @regression
  Scenario: User cannot access the products page without logging in
    When I navigate directly to the products page URL
    Then I should be redirected back to the login page
