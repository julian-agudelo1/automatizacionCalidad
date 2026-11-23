Feature: User registration
  As a visitor of Advantage Online Shopping
  I want to create an account
  So that I can access the purchase features

  @CP01 @registration @positive
  Scenario Outline: CP01 - Register a user with valid data
    Given the user opens the registration form
    When the user fills the registration form with the following data
      | email   | firstName   | lastName   | phone   | country   | city   | address   | state   | postalCode   |
      | <email> | <firstName> | <lastName> | <phone> | <country> | <city> | <address> | <state> | <postalCode> |
    And the user submits the registration form
    Then the account is registered successfully

    Examples:
      | email     | firstName | lastName | phone      | country  | city     | address         | state     | postalCode |
      | GENERATED | Test      | User     | 3001234567 | Colombia | Medellin | Street 10 20-30 | Antioquia | 050001     |
      | GENERATED | Maria     | Gomez    | 3109876543 | Colombia | Bogota   | Avenue 5 10-20  | Bogota    | 110111     |

  @CP02 @registration @negative
  Scenario Outline: CP02 - Reject an invalid email
    Given the user opens the registration form
    When the user fills the registration form with the following data
      | email   | firstName   | lastName   | phone   | country   | city   | address   | state   | postalCode   |
      | <email> | <firstName> | <lastName> | <phone> | <country> | <city> | <address> | <state> | <postalCode> |
    Then the registration is blocked by the form validation

    Examples:
      | email         | firstName | lastName | phone      | country  | city     | address         | state     | postalCode |
      | invalid-email | Test      | User     | 3001234567 | Colombia | Medellin | Street 10 20-30 | Antioquia | 050001     |
      | user@         | Test      | User     | 3001234567 | Colombia | Medellin | Street 10 20-30 | Antioquia | 050001     |

  @CP03 @registration @negative
  Scenario Outline: CP03 - Prevent the registration without required fields
    Given the user opens the registration form
    When the user tries to register without completing the required fields
    Then the user remains on the following page
      | page   |
      | <page> |

    Examples:
      | page       |
      | #/register |