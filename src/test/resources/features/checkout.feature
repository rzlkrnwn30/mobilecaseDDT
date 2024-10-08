Feature: SwagLabs End-to-End Testing
  Scenario: Successful login and checkout
    Given I launch the SwagLabs apk
    When I enters a valid username and valid password
    And I tap the Login button
    Then I should see the products page

    When I add a product to the cart
    And I navigate to the cart page
    And I tap the Checkout button
    And I enter valide  first name, last name, and postal code
    And I tap the Continue button
    Then I should see the checkout overview page

    When I tap the Finish button
    Then I should see checkout complete page
