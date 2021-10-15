package com.ecommerce.qa.pages;

import com.ecommerce.qa.intialize.BrowserFactory;
import com.ecommerce.qa.util.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BrowserFactory {
    private WebDriver driver;
    private ElementActions elementActions;

    private By productName = By.xpath("//div[@class='col-sm-4']//h1[text()='iMac']");
    private By brand = By.xpath("//div[@class='col-sm-4']//li/a");
    private By productCode = By.xpath("//div[@class='col-sm-4']//li[contains(text(),'Product Code')]");
    private By productAvailability = By.xpath("//div[@class='col-sm-4']//li[contains(text(),'Availability')]");
    private By productPrice = By.xpath("//div[@class='col-sm-4']//li/h2");
    private By productExTax = By.xpath("//div[@class='col-sm-4']//li[contains(text(),'Ex Tax')]");
    private By addToCart = By.id("button-cart");
    private By addTOCartSuccessMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");

    public ProductPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }
    public String productName(){
        return elementActions.getElementText(productName);
    }
    public String productBrand(){
        return elementActions.getElementText(brand);
    }
    public String productCode(){
        return elementActions.getElementText(productCode);
    }
    public String productAvailability(){
        return elementActions.getElementText(productAvailability);
    }
    public String productPrice(){
        return elementActions.getElementText(productPrice);
    }
    public String productExTax(){
        return elementActions.getElementText(productExTax);
    }
    public String addProductToCartAndValidateSuccessMessage(String productName){
        elementActions.click_WebElement(addToCart);
        WebElement waitForSuccessMessage = elementActions.waitForPresenceOfWebElementLocated(addTOCartSuccessMessage,5000);
        return elementActions.getElementText(addTOCartSuccessMessage);
    }
}
