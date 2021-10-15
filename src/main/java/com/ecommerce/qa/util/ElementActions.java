package com.ecommerce.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ElementActions {

    public WebDriver driver;
    Actions action;
    WebDriverWait wait;
    public ElementActions(WebDriver driver){
        this.driver = driver;
    }
    public WebElement getElement(By locator){
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("Exception while creating a Webelement"+locator);
        }
        return element;
    }
    public List<WebElement> getWebElements(By locator){
        return driver.findElements(locator);
    }
    //findElements
    public void click_WebElement(By locator){
        getElement(locator).click();
    }
    public void sendKeys(By locator, String input){
        getElement(locator).sendKeys(input);
    }
    public boolean isElementDisplayed(By locator){
        return getElement(locator).isDisplayed();
    }
    public String getElementText(By locator){
        return getElement(locator).getText();
    }
    public  void getAttributeList(By locator, String attribute){
        List<WebElement> webElementList = getWebElements(locator);
        for(int i=0; i< webElementList.size();i++) {
            String attributeValue = webElementList.get(i).getAttribute(attribute);
            System.out.println(attributeValue);
        }
    }
    // select dropdowns
    public void selectValueByIndex(By locator, int index){
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }
    public void selectByValue(By locator, String value){
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }
    public void selectValueByVisibleText(By locator, String visibleText){
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(visibleText);
    }
    public List<String> getDropDownOptions(By locator){
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        List<String> optionsText = new ArrayList<>();
        for(WebElement option : optionsList){
            optionsText.add(option.getText());
        }
        return optionsText;
    }
    //print dropdown options
    //select values from drop down options
    //Actions util
    public void action_SendKeys(By locator, String value){
        action = new Actions(driver);
        action.sendKeys(getElement(locator), value).perform();
    }
    public void action_Click(By locator){
        action = new Actions(driver);
        action.click(getElement(locator)).perform();
    }
    public void twoLevelMenu(By parentLocator, By childLocator){
        action = new Actions(driver);
        action.moveToElement(getElement(parentLocator)).perform();
        action_Click(childLocator);
    }
    //three level menu
    //right click
    //drag and drop
    //waits
    public List<WebElement> waitForVisibilityOfElements(By locator, int timeout){
        wait = new WebDriverWait(driver,timeout);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public WebElement waitForPresenceOfWebElementLocated(By locator, int timeout){
        wait = new WebDriverWait(driver,timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public WebElement waitForElementToBeClickable(By locator, int timeout){
        wait = new WebDriverWait(driver,timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    //visbilityof Alert
    //visbilityof element
    // url contains / tobe
    //wait for frame
    //fluent waits
}
