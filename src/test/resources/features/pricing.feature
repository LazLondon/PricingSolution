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
      | products           | price list       | sub-total | savings | total savings | total to pay |
      |                    |                  | 0         | 0       | 0             | 0            |
      | beans              | 0.50             | 0.50      | 0       | 0             | 0.50         |
      | beans, coke, beans | 0.50, 0.70, 0.50 | 1.70      | 0       | 0             | 1.70         |