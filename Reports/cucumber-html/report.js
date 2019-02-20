$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("AmazonCart.feature");
formatter.feature({
  "line": 2,
  "name": "As a Customer I want to find a desired camera and add it to the cart and validate sub total price",
  "description": "\r\n                   Acceptance Criteria\r\n                   When searching for \"Best sellers in Digital Camera\" in search field (top of the page), suggestions are displayed as the customer types\r\n                   After selecting the 5th Product\r\n                   Check the correct product was added\r\n                   Check the subtotal price sum is correct",
  "id": "as-a-customer-i-want-to-find-a-desired-camera-and-add-it-to-the-cart-and-validate-sub-total-price",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@addItem"
    }
  ]
});
formatter.scenarioOutline({
  "line": 10,
  "name": "Product page subtotal price sum is correct",
  "description": "",
  "id": "as-a-customer-i-want-to-find-a-desired-camera-and-add-it-to-the-cart-and-validate-sub-total-price;product-page-subtotal-price-sum-is-correct",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 12,
  "name": "I navigate to Amazon homepage",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I see the homepage",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I enter \u003csearchItem\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "I see the products associated",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "I select product \u003cproductName\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "I see the product page with Add to cart button",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "I see the Product price per item is displayed",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "increase the quantity to \u003cquantity\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "I click Add to cart button",
  "keyword": "When "
});
formatter.step({
  "line": 21,
  "name": "I check that correct product \u003cproductName\u003e is added",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "I see the subtotal price sum is correct",
  "keyword": "And "
});
formatter.examples({
  "line": 24,
  "name": "",
  "description": "",
  "id": "as-a-customer-i-want-to-find-a-desired-camera-and-add-it-to-the-cart-and-validate-sub-total-price;product-page-subtotal-price-sum-is-correct;",
  "rows": [
    {
      "cells": [
        "productName",
        "searchItem",
        "quantity"
      ],
      "line": 25,
      "id": "as-a-customer-i-want-to-find-a-desired-camera-and-add-it-to-the-cart-and-validate-sub-total-price;product-page-subtotal-price-sum-is-correct;;1"
    },
    {
      "cells": [
        "Amcrest ProHD 1080P WiFi Camera 2MP (1920TVL) Indoor Pan/Tilt Security Wireless IP Camera IP2M-841B (Black)",
        "Best Sellers in Digital Cameras",
        "8"
      ],
      "line": 26,
      "id": "as-a-customer-i-want-to-find-a-desired-camera-and-add-it-to-the-cart-and-validate-sub-total-price;product-page-subtotal-price-sum-is-correct;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 27637859800,
  "status": "passed"
});
formatter.scenario({
  "line": 26,
  "name": "Product page subtotal price sum is correct",
  "description": "",
  "id": "as-a-customer-i-want-to-find-a-desired-camera-and-add-it-to-the-cart-and-validate-sub-total-price;product-page-subtotal-price-sum-is-correct;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@addItem"
    }
  ]
});
formatter.step({
  "line": 12,
  "name": "I navigate to Amazon homepage",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I see the homepage",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I enter Best Sellers in Digital Cameras",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "I see the products associated",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "I select product Amcrest ProHD 1080P WiFi Camera 2MP (1920TVL) Indoor Pan/Tilt Security Wireless IP Camera IP2M-841B (Black)",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "I see the product page with Add to cart button",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "I see the Product price per item is displayed",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "increase the quantity to 8",
  "matchedColumns": [
    2
  ],
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "I click Add to cart button",
  "keyword": "When "
});
formatter.step({
  "line": 21,
  "name": "I check that correct product Amcrest ProHD 1080P WiFi Camera 2MP (1920TVL) Indoor Pan/Tilt Security Wireless IP Camera IP2M-841B (Black) is added",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "I see the subtotal price sum is correct",
  "keyword": "And "
});
formatter.match({
  "location": "AddCartSteps.i_navigate_to_amazon_homepage()"
});
formatter.result({
  "duration": 964141100,
  "status": "passed"
});
formatter.match({
  "location": "AddCartSteps.i_see_the_homepage()"
});
formatter.result({
  "duration": 131104300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Best Sellers in Digital Cameras",
      "offset": 8
    }
  ],
  "location": "AddCartSteps.i_enter(String)"
});
formatter.result({
  "duration": 9469773900,
  "status": "passed"
});
formatter.match({
  "location": "AddCartSteps.i_see_the_products_associated()"
});
formatter.result({
  "duration": 28609003400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Amcrest ProHD 1080P WiFi Camera 2MP (1920TVL) Indoor Pan/Tilt Security Wireless IP Camera IP2M-841B (Black)",
      "offset": 17
    }
  ],
  "location": "AddCartSteps.i_select_product(String)"
});
formatter.result({
  "duration": 12185527500,
  "status": "passed"
});
formatter.match({
  "location": "AddCartSteps.i_see_the_product_page_with_add_to_cart_button()"
});
formatter.result({
  "duration": 1179025500,
  "status": "passed"
});
formatter.match({
  "location": "AddCartSteps.i_see_the_product_price_per_item_is_displayed()"
});
formatter.result({
  "duration": 391808900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "8",
      "offset": 25
    }
  ],
  "location": "AddCartSteps.increase_the_quantity_to(String)"
});
formatter.result({
  "duration": 235657200,
  "status": "passed"
});
formatter.match({
  "location": "AddCartSteps.i_click_add_to_cart_button()"
});
formatter.result({
  "duration": 2421027600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Amcrest ProHD 1080P WiFi Camera 2MP (1920TVL) Indoor Pan/Tilt Security Wireless IP Camera IP2M-841B (Black)",
      "offset": 29
    }
  ],
  "location": "AddCartSteps.i_check_that_correct_product_is_added(String)"
});
formatter.result({
  "duration": 2723636400,
  "status": "passed"
});
formatter.match({
  "location": "AddCartSteps.i_see_the_subtotal_price_sum_is_correct()"
});
formatter.result({
  "duration": 666936300,
  "status": "passed"
});
formatter.after({
  "duration": 1194540900,
  "status": "passed"
});
});