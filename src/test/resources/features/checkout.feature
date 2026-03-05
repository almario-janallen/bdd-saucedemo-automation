Feature: Checkout
  As a logged-in user
  I want to complete the checkout process
  So that I can successfully place my order

  @smoke @regression
  Scenario: User can complete a full end-to-end purchase
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I am on the products page
    When I add product "Sauce Labs Backpack" to the cart
    And I navigate to the cart
    And I proceed to checkout
    And I fill in shipping details with first name "John" last name "Doe" and postal code "12345"
    And I click continue
    And I click finish
    Then I should see the order confirmation message "Thank you for your order!"
