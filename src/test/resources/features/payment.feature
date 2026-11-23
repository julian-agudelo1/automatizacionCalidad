Feature: Order payment
  As a new customer of Advantage Online Shopping
  I want to register, add products to my cart and pay
  So that I can confirm my purchase

  @CP11 @checkout @e2e @positive
  Scenario Outline: CP11 - Complete the purchase of several products with MasterCredit
    Given a new user is registered and signed in
    When the user adds the following products to the cart
      | category    | productId    | quantity    |
      | <category1> | <productId1> | <quantity1> |
      | <category2> | <productId2> | <quantity2> |
    And the user pays with the following test card
      | cardNumber   | cvv   | month   | year   | cardholder   |
      | <cardNumber> | <cvv> | <month> | <year> | <cardholder> |
    Then the purchase confirmation is displayed

    Examples:
      | category1 | productId1 | quantity1 | category2 | productId2 | quantity2 | cardNumber       | cvv | month | year | cardholder    |
      | speakers  | 20         | 2         | tablets   | 16         | 1         | 1234567891234567 | 1234 | 12    | 2027 | AUTOMATION QA |

  @CP12 @checkout @negative
  Scenario Outline: CP12 - Prevent the payment when required data is missing
    Given a new user is registered and signed in
    And the user adds the following products to the cart
      | category   | productId   | quantity   |
      | <category> | <productId> | <quantity> |
    When the user tries to pay without completing the required fields
    Then the payment is blocked

    Examples:
      | category | productId | quantity |
      | speakers | 20        | 1        |