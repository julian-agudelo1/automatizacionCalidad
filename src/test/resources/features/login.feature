Feature: User login
  As a registered user
  I want to sign in
  So that I can access my account

  @CP04 @authentication @positive
  Scenario Outline: CP04 - Sign in with valid credentials
    Given a new user is registered with the following password
      | password   |
      | <password> |
    When the user signs in with the following password
      | password        |
      | <loginPassword> |
    Then the user is authenticated with the registered account

    Examples:
      | password | loginPassword |
      | Test2026 | Test2026      |

  @CP05 @authentication @negative
  Scenario Outline: CP05 - Reject an incorrect password
    Given a new user is registered with the following password
      | password   |
      | <password> |
    When the user signs in with the following password
      | password        |
      | <loginPassword> |
    Then the sign in is rejected

    Examples:
      | password | loginPassword     |
      | Test2026 | WrongPassword2026 |