Feature: User Login
  As a registered user
  I want to log into the SauceDemo application
  So that I can access the product catalog

  Background:
    Given I am on the login page

  @smoke @regression
  Scenario: Valid user can log in successfully
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the products page

  @regression
  Scenario Outline: Invalid credentials display an error message
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should see the error message "<error_message>"

    Examples:
      | username         | password      | error_message                                                             |
      | invalid_user     | secret_sauce  | Epic sadface: Username and password do not match any user in this service |
      | standard_user    | wrong_pass    | Epic sadface: Username and password do not match any user in this service |
      | (empty)          | (empty)       | Epic sadface: Username is required                                        |
