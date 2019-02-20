package com.qa.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CommonUtility {
    private static Logger logger = Logger.getLogger(CommonUtility.class);
    public static final int DEFAULT_WAIT_TIME = 5;
    public static void delay(int timeInMilli){
        try {
            Thread.sleep(timeInMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void moveToSubMenuItem(WebDriver driver, WebElement menuItem,WebElement subMenuItem){
        Actions actions = new Actions(driver);
        actions.moveToElement(menuItem);
        actions.moveToElement(subMenuItem);
        actions.click();
        actions.perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void waitForVisibilityOfElement(WebDriver driver,WebElement element){
        FluentWait<WebDriver> wait = fluentWait(driver);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitForStalenessVisibilityOfElement(WebDriver driver,WebElement element){
        FluentWait<WebDriver> wait = fluentWait(driver);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
    }

    public static FluentWait<WebDriver> fluentWait(WebDriver driver){
        return fluentWait(driver,DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
    }

    public static FluentWait<WebDriver> fluentWait(WebDriver driver,int duration, TimeUnit timeUnit){
        return new FluentWait<WebDriver>(driver)
                .withTimeout(duration, timeUnit)
                .pollingEvery(200, TimeUnit.MILLISECONDS)
                .ignoreAll(new ArrayList<Class<? extends Throwable>>(){
                    {
                        add(StaleElementReferenceException.class);
                        add(NoSuchElementException.class);
                    }
                }).withMessage("Selenium Timeout Exception");
    }

    public static void waitForPageTitle(WebDriver driver,String title){
        FluentWait<WebDriver> wait = fluentWait(driver);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void waitForElementAttributeContains(WebDriver driver,WebElement element, String attributeName, String attributeValue){
        FluentWait<WebDriver> wait = fluentWait(driver );
        wait.until(ExpectedConditions.attributeContains(element,attributeName,attributeValue));
    }
    public static boolean isClickable(WebDriver driver,WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void highlight(WebDriver driver,WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid blue");
            delay(200);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delay(200);
        }
    }

    public static void jsClick(WebDriver driver,WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var el=arguments[0]; setTimeout(function() {e1.click();}, 100);", element);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void actionClick(WebDriver driver,WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public static void  scrollToElement(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //Method to Hover over an element
    public static void hoverAction(WebDriver driver,WebElement element){
        highlight(driver,element);
        delay(2000);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    //Mouse Hover element with Locatable and Mouse class
    public static void hoverLocatable(WebDriver driver,WebElement element){
        highlight(driver,element);
        Locatable hoverItem = (Locatable) element;
        Mouse mouse = ((HasInputDevices) driver).getMouse();
        mouse.mouseMove(hoverItem.getCoordinates());
    }

    //Method to hover over an element using JavaScript Executor
    public static void mouseHoverJScript(WebDriver driver,WebElement HoverElement) {
       highlight(driver,HoverElement);
        try {
            if (isElementPresent(HoverElement)) {
                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                ((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
            } else {
                System.out.println("Element was not visible to hover " + "\n");
            }

        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + HoverElement
                    + "is not attached to the page document"
                    + e.getStackTrace());
        }
    }

    public static boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if (element.isDisplayed()
                    || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException |StaleElementReferenceException e) {
            flag = false;
        }
        return flag;
    }

    public static void enterTextSearch(WebDriver driver,WebElement searchField,String partialString,String valueToBeSelected){

        //Enter partial string
        searchField.sendKeys(partialString);
        WebDriverWait wait=new WebDriverWait(driver, 10);
        // ="Romano D'ezzelino"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-label='"+valueToBeSelected+"']")));
        WebElement element=driver.findElement(By.xpath("//*[@data-label='"+valueToBeSelected+"']"));
        element.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void selectItemFromSearchSuggestion(WebDriver driver,WebElement searchField,String partialString,String valueToBeSelected){

        //Enter partial string
        searchField.sendKeys(partialString);
        WebDriverWait wait=new WebDriverWait(driver, 10);
        // ="Romano D'ezzelino"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-label='"+valueToBeSelected+"']")));
        WebElement element=driver.findElement(By.xpath("//*[@data-label='"+valueToBeSelected+"']"));
        element.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void selectFilter(WebElement filter,String text){
        //Customers' opinion
        Select fruits = new Select(filter);
        fruits.selectByValue(text);

    }

    public static void
    allowCookies(){
        Robot robot = null;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(600);
        } catch (AWTException e) {
            logger.info(e);
        }
    }
    public static void handleAcceptCookies(WebDriver driver){
        WebElement closePopup=driver.findElement(By.xpath("//*[contains(text(),'CONTINUE')]"));
        /*WebElement acceptCookies=driver.findElement(By.linkText("Accepter les cookies"));*/
        waitForVisibilityOfElement(driver,closePopup);
       /* highlight(driver,acceptCookies);
        actionClick(driver,acceptCookies);*/
        highlight(driver,closePopup);
        actionClick(driver,closePopup);
    }
}
