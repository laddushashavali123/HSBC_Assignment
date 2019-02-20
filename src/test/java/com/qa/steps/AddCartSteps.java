package com.qa.steps;

import com.qa.pages.AddCartPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

public class AddCartSteps {

    AddCartPage addBoxCartPage = new AddCartPage();
    // ExtentTest test = createTest(getExtentReports(), "Add a box to the cart && Boxes display");
    @Given("^I navigate to Amazon homepage$")
    public void i_navigate_to_amazon_homepage() throws Throwable {
        addBoxCartPage.verifyHomePage();
    }

    @When("^I enter (.+)$")
    public void i_enter(String searchitem) throws Throwable {
        addBoxCartPage.enterTextSearch(searchitem);
    }

    @When("^I select product (.+)$")
    public void i_select_product(String productname) throws Throwable {
        addBoxCartPage.clickItemDisplayed(productname);
    }

    @When("^I click Add to cart button$")
    public void i_click_add_to_cart_button() throws Throwable {
        addBoxCartPage.clickAddtoCartButton();
    }

    @Then("^I see the homepage$")
    public void i_see_the_homepage() throws Throwable {
        addBoxCartPage.verifyHomePage();
    }

    @Then("^I see the products associated$")
    public void i_see_the_products_associated() throws Throwable {
        addBoxCartPage.verifySuggestions();
    }

    @Then("^I see the product page with Add to cart button$")
    public void i_see_the_product_page_with_add_to_cart_button() throws Throwable {
        addBoxCartPage.productDetailsPageDisplayed();
    }

    @Then("^I check that correct product (.+) is added$")
    public void i_check_that_correct_product_is_added(String productname) throws Throwable {
        addBoxCartPage.viewCart(productname);
    }

    @And("^I see the Product price per item is displayed$")
    public void i_see_the_product_price_per_item_is_displayed() throws Throwable {
        addBoxCartPage.verifyProductPage();
    }

    @And("^increase the quantity to (.+)$")
    public void increase_the_quantity_to(String quantity) throws Throwable {
        addBoxCartPage.increaseItemNumbersToMorethanFive(quantity);
    }

    @And("^I see the subtotal price sum is correct$")
    public void i_see_the_subtotal_price_sum_is_correct() throws Throwable {
        addBoxCartPage.priceperitemAndQuantity();
    }


}
