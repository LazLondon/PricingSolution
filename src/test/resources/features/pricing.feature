Feature: As a Pricing Solution, I would like to price the contents of the shopping basket.

  Background:
    Given a list of products and their prices
      | beans   | 0.50 |
      | coke    | 0.70 |
      | oranges | 1.99 |

  Scenario Outline: Simple pricing
    When I receive a list of <products>
    Then I want to create a pricing summary containing <price list>, <sub-total>, <savings>, <total savings>, <total to pay>

    Examples:
      | products           | price list    | sub-total | savings | total savings | total to pay |
      |                    |               | 0         | - -> 0  | 0             | 0            |
      | beans              | 0.5           | 0.5       | - -> 0  | 0             | 0.5          |
      | beans, coke, beans | 0.5, 0.7, 0.5 | 1.7       | - -> 0  | 0             | 1.7          |


  Scenario Outline: Pricing products by weight
    When I receive a list of <products>
    Then I want to create a pricing summary containing <price list>, <sub-total>, <savings>, <total savings>, <total to pay>

    Examples:
      | products                     | price list | sub-total | savings | total savings | total to pay |
      | oranges - 0.2                | 0.4        | 0.4       | - -> 0  | 0             | 0.4          |
      | oranges - 0.5, oranges - 0.5 | .99, .99   | 1.98      | - -> 0  | 0             | 1.98         |


  Scenario Outline:  X for Y type of discount
    Given a list of discounts of type X for Y
      | beans | 3 for 2 |
      | coke  | 2 for 1 |

    When I receive a list of <products>
    Then I want to create a pricing summary containing <price list>, <sub-total>, <savings>, <total savings>, <total to pay>

    Examples:
      | products                                 | price list                   | sub-total | savings                                      | total savings | total to pay |
      | beans                                    | 0.5                          | 0.5       | - -> 0                                       | 0             | 0.5          |
      | beans, beans, beans                      | 0.5, 0.5, 0.5                | 1.5       | beans 3 for 2 -> 0.5                         | 0.5           | 1            |
      | beans, beans, beans, beans               | 0.5, 0.5, 0.5, 0.5           | 2         | beans 3 for 2 -> 0.5                         | 0.5           | 1.5          |
      | beans, beans, beans, beans, beans, beans | 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 | 3         | beans 3 for 2 -> 1                           | 1             | 2            |
      | beans, beans, beans, coke, coke          | 0.5, 0.5, 0.5, 0.7, 0.7      | 2.9       | beans 3 for 2 -> 0.5 === coke 2 for 1 -> 0.7 | 1.2           | 1.7          |


  Scenario Outline:  X for Y pounds type of discount
    Given a list of discounts of type X for Y
      | beans | 3 for £1.2 |
      | coke  | 2 for £1   |

    When I receive a list of <products>
    Then I want to create a pricing summary containing <price list>, <sub-total>, <savings>, <total savings>, <total to pay>

    Examples:
      | products                                 | price list                   | sub-total | savings                                          | total savings | total to pay |
      | beans                                    | 0.5                          | 0.5       | - -> 0                                           | 0             | 0.5          |
      | beans, beans, beans                      | 0.5, 0.5, 0.5                | 1.5       | beans 3 for £1.2 -> 0.3                          | 0.3           | 1.2          |
      | beans, beans, beans, beans               | 0.5, 0.5, 0.5, 0.5           | 2         | beans 3 for £1.2 -> 0.3                          | 0.3           | 1.7          |
      | beans, beans, beans, beans, beans, beans | 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 | 3         | beans 3 for £1.2 -> 0.6                          | 0.6           | 2.4          |
      | beans, beans, beans, coke, coke          | 0.5, 0.5, 0.5, 0.7, 0.7      | 2.9       | beans 3 for £1.2 -> 0.3 === coke 2 for £1 -> 0.4 | 0.7           | 2.2          |

