Feature: Shopping on Your account


  Scenario: Do shopping on Your account after login
    Given User is logged in MyStore shop
    When User puts Product name in Search our catalog
    And User chooses search icon
    And User chooses product from the list
    And User selects size of the product
    And User selects quantity of the product
    And User puts product to the cart
    And User proceeds to checkout
    And User selects delivery address
    And User selects shipping method
    And User selects payment method
    And User selects Order with an obligation to pay
    Then User makes screenshot of order items