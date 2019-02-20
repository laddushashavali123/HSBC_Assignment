@addItem
Feature: As a Customer I want to find a desired camera and add it to the cart and validate sub total price

                     Acceptance Criteria
                     When searching for "Best sellers in Digital Camera" in search field (top of the page), suggestions are displayed as the customer types
                     After selecting the 5th Product
                     Check the correct product was added
                     Check the subtotal price sum is correct

Scenario Outline: Product page subtotal price sum is correct

    Given I navigate to Amazon homepage
    Then I see the homepage
    When I enter <searchItem>
    Then I see the products associated
    When I select product <productName>
    Then I see the product page with Add to cart button
    And I see the Product price per item is displayed
    And increase the quantity to <quantity>
    When I click Add to cart button
    Then I check that correct product <productName> is added
    And I see the subtotal price sum is correct

Examples:
 |productName|searchItem|quantity|
 |Amcrest ProHD 1080P WiFi Camera 2MP (1920TVL) Indoor Pan/Tilt Security Wireless IP Camera IP2M-841B (Black)|Best Sellers in Digital Cameras|8|

