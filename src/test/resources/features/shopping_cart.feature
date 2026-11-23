Feature: Shopping cart
  As a customer of Advantage Online Shopping
  I want to manage the products in my cart
  So that I can review my purchase before paying

  @CP06 @cart @positive
  Scenario Outline: CP06 - Add a product to the cart
    Given the user opens the product catalog
    When the user adds the following products to the cart
      | category   | productId   | quantity   |
      | <category> | <productId> | <quantity> |
    And the user opens the cart
    Then the following products appear in the cart
      | productName   |
      | <productName> |

    Examples:
      | category | productId | quantity | productName                          |
      | speakers | 20        | 1        | BOSE SOUNDLINK BLUETOOTH SPEAKER III |

  @CP07 @cart @positive
  Scenario Outline: CP07 - Add products from different categories
    Given the user opens the product catalog
    When the user adds the following products to the cart
      | category    | productId    | quantity    |
      | <category1> | <productId1> | <quantity1> |
      | <category2> | <productId2> | <quantity2> |
    And the user opens the cart
    Then the following products appear in the cart
      | productName    |
      | <productName1> |
      | <productName2> |

    Examples:
      | category1 | productId1 | quantity1 | productName1                         | category2 | productId2 | quantity2 | productName2               |
      | tablets   | 16         | 1         | HP ELITEPAD 1000 G2 TABLET           | speakers  | 20         | 1         | BOSE SOUNDLINK BLUETOOTH SPEAKER III |

  @CP08 @cart @positive
  Scenario Outline: CP08 - Modify the quantity of a product
    Given the user opens the product catalog
    When the user adds the following products to the cart
      | category   | productId   | quantity   |
      | <category> | <productId> | <quantity> |
    And the user updates the product quantity to the following value
      | quantity      |
      | <newQuantity> |
    Then the cart reflects the following quantity and amount
      | quantity      | unitPrice   |
      | <newQuantity> | <unitPrice> |

    Examples:
      | category | productId | quantity | newQuantity | unitPrice |
      | speakers | 20        | 1        | 3           | 269.99    |

  @CP09 @cart @validation
  Scenario Outline: CP09 - Validate the subtotal and the total
    Given the user opens the product catalog
    When the user adds the following products to the cart
      | category   | productId   | quantity   |
      | <category> | <productId> | <quantity> |
    And the user opens the cart
    Then the subtotal and the total are the following
      | amount   |
      | <amount> |

    Examples:
      | category | productId | quantity | amount  |
      | speakers | 20        | 2        | $539.98 |

  @CP10 @cart @alternative
  Scenario Outline: CP10 - Remove a product from the cart
    Given the user opens the product catalog
    When the user adds the following products to the cart
      | category   | productId   | quantity   |
      | <category> | <productId> | <quantity> |
    And the user removes the first product from the cart
    Then the cart is empty

    Examples:
      | category | productId | quantity |
      | speakers | 20        | 1        |