package com.ecommerce.qa.pages;

import com.ecommerce.qa.intialize.BrowserFactory;
import com.ecommerce.qa.util.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountPage extends BrowserFactory {
    private WebDriver driver;
    ElementActions elementActions;

    private  By homePageHeader = By.linkText("Your Store");
    private  By myAccountHeader = By.xpath("//h2[text()='My Account']");
    private  By myOrdersHeader = By.xpath("//h2[text()='My Orders']");
    private  By myAffiliateAccountHeader = By.xpath("//h2[text()='My Affiliate Account']");
    private By search = By.name("search");
    private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");

    public AccountPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }
    public boolean verifyMyAccountHeader(){
        return elementActions.isElementDisplayed(myAccountHeader);
    }
    public boolean verifyMyOrdersHeader(){
        return elementActions.isElementDisplayed(myOrdersHeader);
    }
    public boolean verifyMyAffiliateAccountHeader(){
        return elementActions.isElementDisplayed(myAffiliateAccountHeader);
    }
    public void  doSearch(String productName){
        elementActions.sendKeys(search, productName);
        elementActions.click_WebElement(searchButton);
    }
    public SearchPage doSearchForProduct(String productName){
        elementActions.sendKeys(search, productName);
        elementActions.click_WebElement(searchButton);
        return new SearchPage(driver);
    }

}
