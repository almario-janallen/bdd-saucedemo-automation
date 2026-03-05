Feature: Product Catalog
  As a logged-in user
  I want to manage products in my cart
  So that I can prepare my order before checkout

  Background:
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I am on the products page

  @smoke @regression
  Scenario: User can add a product to the cart
    When I add product "Sauce Labs Backpack" to the cart
    Then the cart count should be "1"

  @regression
  Scenario: User can remove a product from the cart
    Given I have added product "Sauce Labs Backpack" to the cart
    When I remove product "Sauce Labs Backpack" from the cart
    Then the cart count should be "0"
