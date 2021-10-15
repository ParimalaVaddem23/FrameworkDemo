package com.ecommerce.qa.pages;

import com.ecommerce.qa.intialize.BrowserFactory;
import com.ecommerce.qa.util.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginPage extends BrowserFactory {

    private WebDriver driver;
    public BrowserFactory browserFactory;
    private Properties prop;
    private ElementActions elementActions;

    private By email = By.id("input-email");
    private By password = By.id("input-password");
    private  By forgottenPassword = By.linkText("Forgotten Password");
    private By login = By.xpath("//input[@value='Login']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }

    public AccountPage doLogin(String uemail, String upassword) {
        elementActions.sendKeys(email,uemail);
        elementActions.sendKeys(password,upassword);
        elementActions.click_WebElement(login);
        return new AccountPage(driver);
    }

    public boolean verifyForgottenPasswordExists(){
        return elementActions.isElementDisplayed(forgottenPassword);
    }
    /*
    public String pageTitle(){
        return driver.getTitle();
    }
     */

}
