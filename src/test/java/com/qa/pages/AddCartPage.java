package com.qa.pages;

import com.qa.driver.DriverManager;
import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.qa.utility.CommonUtility.*;
import static com.qa.utility.ReportUtility.setLogStatus;

public class AddCartPage extends DriverManager {
    private String priceBefore = "";
    private String quantityAdded = "";
    Logger logger = Logger.getLogger(AddCartPage.class);
    // ExtentTest test = createTest(getExtentReports(), "Adding or viewing box in the cart");

    public AddCartPage() {
        PageFactory.initElements(driver, this);
    }

    // magnifier icon, when clicked opens search text box
    @FindBy(xpath = "//*[@name='field-keywords']")
    WebElement searchField;

    // search text box
    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    WebElement searchTextInput;

    // search text box
    @FindBy(xpath = "//*[@value='Go']")
    WebElement searchIcon;

    // search text box
    @FindBy(xpath = "(//*[@class='a-link-normal a-text-normal'])")
    WebElement searchResultItem;

    @FindBy(xpath = "//*[@id='quantity']")
    WebElement quantityProductDetails;

    @FindBy(xpath = "//*[@id='add-to-cart-button']")
    WebElement addTocartButton;

    @FindBy(xpath = "//*[@id='buy-now-button']")
    WebElement buyNowButton;

    @FindBy(xpath = "//*[@id='price_inside_buybox']")
    WebElement priceOfItem;

    @FindBy(xpath = "//*[@id='priceblock_ourprice']")
    WebElement ourpriceOfItem;

    @FindBy(xpath = "//*[@class='a-color-price hlb-price a-inline-block a-text-bold']")
    WebElement subTotalPrice;

    @FindBy(xpath = "//*[@class='a-size-medium a-align-center huc-subtotal']")
    WebElement subTotalText;

    @FindBy(xpath = "//*[@id='imgTagWrapperId']/img")
    WebElement imgIconProductDeatilsPage;

    @FindBy(xpath = "//*[@id='hlb-view-cart-announce']")
    WebElement viewCartPage;

    @FindBy(xpath = "//*[@class='a-size-medium sc-product-title a-text-bold']")
    WebElement producTitleCartPage;

    @FindBy(xpath = "//*[@class='a-size-medium a-color-price sc-price sc-white-space-nowrap sc-product-price sc-price-sign a-text-bold']")
    WebElement priceInCart;

    @FindBy(xpath = "//*[@class='a-dropdown-prompt']")
    WebElement quantityInCart;

    @FindBy(xpath = "//*[@class='a-size-medium a-color-price sc-price sc-white-space-nowrap  sc-price-sign']")
    WebElement subTotalCart;


    public void enterTextSearch(String partialString) {
        //Enter partial string
        searchField.clear();
        searchField.sendKeys(partialString);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchIcon));
        //Click on search
        searchIcon.click();
        wait.until(ExpectedConditions.visibilityOf(searchResultItem));
        boolean flag = searchResultItem.getText() != null;
        if (flag)
            setLogStatus(test, "Pass", "Search word is entered to display the search results successfully");
        else
            setLogStatus(test, "Fail", "Search word is not entered to display the search results successfully");
    }

    public void verifySuggestions() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // ="Romano D'ezzelino"
        wait.until(ExpectedConditions.visibilityOf(searchResultItem));
        boolean flag = false;
        List<WebElement> listImages = driver.findElements(By.xpath("(//*[@class='a-link-normal a-text-normal'])"));
        if (resultsDisplayed(listImages))
            setLogStatus(test, "Pass", "Product Search results are displayed successfully");
        else
            setLogStatus(test, "Fail", "Product Search results are not displayed successfully");

    }

    public boolean resultsDisplayed(List<WebElement> listImages) {
        boolean flag = false;
        for (int i = 0; i <= listImages.size() - 1; i++) {
            WebElement elemnt = driver.findElement(By.xpath("(//*[@class='a-link-normal a-text-normal']//*[@class='a-size-medium a-color-base a-text-normal'])[" + (i + 1) + "]"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(elemnt));
            //Highlight and Scroll into View
            highlight(driver, elemnt);
            scrollToElement(driver, elemnt);
            WebElement elementProductname = elemnt;//.findElement(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal']"));
            if (elementProductname.getText() != null
                    && !elementProductname.getText().equalsIgnoreCase("")) {
                logger.info("Product Name : " + elementProductname.getText());
                setLogStatus(test, "Info", "Product ID under check is : " + elementProductname.getText());
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public void clickItemDisplayed(String item) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchResultItem));
        boolean flag = false;
        List<WebElement> listImages = driver.findElements(By.xpath("(//*[@class='a-link-normal a-text-normal'])"));
        clickResultDisplayed(listImages, item);
        if (addTocartButton.isEnabled()
                && addTocartButton.isDisplayed())
            setLogStatus(test, "Pass", "Product details page is displayed successfully");
        else
            setLogStatus(test, "Fail", "Product details page is not displayed successfully");

    }

    //Click on specific result
    public void clickResultDisplayed(List<WebElement> listImages,
                                     String item) {
        for (int i = 0; i <= listImages.size() - 1; i++) {
            WebElement elemnt = driver.findElement(By.xpath("(//*[@class='a-link-normal a-text-normal']//*[@class='a-size-medium a-color-base a-text-normal'])[" + (i + 1) + "]"));
            //Highlight and Scroll into View
            highlight(driver, elemnt);
            scrollToElement(driver, elemnt);
            WebElement elementProductname = elemnt;//.findElement(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal']"));
            if (elementProductname.getText() != null
                    && !elementProductname.getText().equalsIgnoreCase("")
                    && elementProductname.getText().trim().toUpperCase().equalsIgnoreCase(item.trim().toUpperCase())) {
                logger.info("Product Name : " + elementProductname.getText());
                setLogStatus(test, "Info", "Product ID under check is : " + elementProductname.getText());
                elementProductname.click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            }
        }
    }

    public void productDetailsPageDisplayed() {
        //Add to cart button
        if (addTocartButton.isEnabled()
                && addTocartButton.isDisplayed())
            setLogStatus(test, "Pass", "Add to Cart button is displayed");
        else
            setLogStatus(test, "Fail", "Add to Cart button is not displayed");
        //Buy now button
        if (buyNowButton.isEnabled()
                && buyNowButton.isDisplayed())
            setLogStatus(test, "Pass", "Buy now button is displayed");
        else
            setLogStatus(test, "Fail", "Buy now Cart button is not displayed");
        // Price of item in cart box
        //Price of Item button
        if (priceOfItem.isDisplayed()
                && priceOfItem.getText() != null)
            setLogStatus(test, "Pass", "Price of the Item : " + priceOfItem.getText() + " is displayed");
        else
            setLogStatus(test, "Fail", "Price of the Item : " + priceOfItem.getText() + " is not displayed");

        //Price of Item by Amazon button
        if (ourpriceOfItem.isDisplayed()
                && ourpriceOfItem.getText() != null)
            setLogStatus(test, "Pass", "Price of the Item : " + ourpriceOfItem.getText() + " is displayed");
        else
            setLogStatus(test, "Fail", "Price of the Item : " + ourpriceOfItem.getText() + " is not displayed");

    }

    //Click on Add to cart page
    public void clickAddtoCartButton() {
        addTocartButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (subTotalPrice.isDisplayed()
                && subTotalText.isDisplayed())
            setLogStatus(test, "Pass", "Add to cart is clicked which will navigate to home page with details of cart items is dipslayed");
        else
            setLogStatus(test, "Fail", "Add to cart is clicked which will not navigate to home page with details of cart items is dipslayed");

    }

    //Verify the correct product is added to the cart
    public void viewCart(String producttitle) {
        viewCartPage.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (producTitleCartPage.isDisplayed()
                && producTitleCartPage.getText().toUpperCase().trim().contains(producttitle.trim().toUpperCase()))
            setLogStatus(test, "Pass", "Cart is displayed with correct product : "+producttitle);
        else
            setLogStatus(test, "Fail", "Cart is not displayed with correct product : "+producttitle);

    }

    public void verifyProductPage() {
        boolean flag = (addTocartButton.isDisplayed()
                && addTocartButton.isEnabled()
                && isClickable(driver, addTocartButton)
                && isElementPresent(priceOfItem)
                && ourpriceOfItem.isDisplayed()
                && buyNowButton.isDisplayed()
                && buyNowButton.isEnabled());
        if (flag) {
            priceBefore = priceOfItem.getText();
            priceBefore = priceBefore.substring(1, priceBefore.length());
            setLogStatus(test, "Pass", "Product details page is displayed with Add to cart button ,buy now button ,price of item and Amazon Price");
        } else
            setLogStatus(test, "Fail", "Product details page is not displayed with Add to cart button ,buy now button ,price of item and Amazon Price");
    }

    public void verifyHomePage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        boolean flag = isElementPresent(searchField) &&
                isElementPresent(searchIcon)
                && isElementPresent(searchTextInput);
        if (flag)
            setLogStatus(test, "Pass", "Home page is displayed with Search Text field and Search Icon");
        else
            setLogStatus(test, "Fail", "Home page is not displayed with Search Text field and Search Icon");
    }

    public void increaseItemNumbersToMorethanFive(String number) {
        selectFilter(quantityProductDetails, number);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        setLogStatus(test, "Info", "Quantity is updated/changed to : " + number);
        quantityAdded = number;
    }

    public void priceperitemAndQuantity() {
        if (priceInCart.isDisplayed()
                && quantityInCart.isDisplayed()
                && priceInCart.getText() != null
                && quantityInCart.getText() != null)
            setLogStatus(test, "Pass", "Price of each item "+priceInCart.getText()+" and quantity: "+quantityInCart.getText()+" are displayed in the Cart");
        else
            setLogStatus(test, "Fail", "Price of each item "+priceInCart.getText()+" and quantity: "+quantityInCart.getText()+" are not displayed in the Cart");
        boolean flag = priceInCart.getText().trim().contains(priceBefore.trim())
                && quantityInCart.getText().trim().equals(quantityAdded.trim());
        logger.info("priceInCart.getText(): " + priceInCart.getText());
        logger.info("quantityInCart.getText(): " + quantityInCart.getText());
        if (flag)
            setLogStatus(test, "Pass", "Correct product price: "+priceInCart.getText()+" and quantity : "+quantityInCart.getText()+" are added to the cart");
        else
            setLogStatus(test, "Fail", "Correct product price: "+priceInCart.getText()+" and quantity :"+quantityInCart.getText()+" are not added to the cart");
        //Calculate subtotal
        float subtotPrice = Float.valueOf(priceBefore.trim()).floatValue();
        logger.info("subtotPrice: " + subtotPrice);
        int len = subTotalCart.getText().length();
        String sbTotal = subTotalCart.getText().substring(1, len);
        float stPrice = Float.valueOf(sbTotal.trim()).floatValue();
        logger.info("stPrice : " + stPrice);
        boolean subTotalPrice = (subtotPrice * 8) == stPrice;
        Asserts.check(subTotalPrice,"SubTotalPrice : " + subTotalPrice + " value of floats: " + subtotPrice * 8 +" are not correct");
        logger.info("SubTotalPrice : " + subTotalPrice + " value of floats: " + subtotPrice * 8);
        if (subTotalPrice)
            setLogStatus(test, "Pass", "SubTotal price sum is : "+stPrice+" correct in the cart");
        else
            setLogStatus(test, "Fail", "SubTotal price sum is : "+stPrice+" are not correct in the cart");
    }

}
