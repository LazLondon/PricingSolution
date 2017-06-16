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
      |                    |               | 0         | - 0     | 0             | 0            |
      | beans              | 0.5           | 0.5       | - 0     | 0             | 0.5          |
      | beans, coke, beans | 0.5, 0.7, 0.5 | 1.7       | - 0     | 0             | 1.7          |


  Scenario Outline: Pricing products by weight
    When I receive a list of <products>
    Then I want to create a pricing summary containing <price list>, <sub-total>, <savings>, <total savings>, <total to pay>

    Examples:
      | products                     | price list | sub-total | savings | total savings | total to pay |
      | oranges - 0.2                | 0.4        | 0.4       | - 0     | 0             | 0.4          |
      | beans, oranges - 0.4         | 0.5, 0.8   | 1.3       | - 0     | 0             | 1.3          |
      | oranges - 0.5, oranges - 0.5 | .99, .99   | 1.98      | - 0     | 0             | 1.98         |