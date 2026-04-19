Feature: Purchase the order from Ecommerce Website
  
    Scenario: verify login is successfull
    Given Logged in with username "hemaayworld@gmail.com" and password "Practice@123"
    Then verify that the "HOME" is displayed on the page.

    Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username "<name>" and password "<password>"
    When I add product "<productName>" to Cart
    And Checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                  | password     | productName |
      | hemajayworld@gmail.com | Practice@123  | ZARA COAT 3 |
