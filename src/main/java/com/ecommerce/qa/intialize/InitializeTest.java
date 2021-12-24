package com.ecommerce.qa.intialize;

import com.ecommerce.qa.pages.AccountPage;
import com.ecommerce.qa.pages.LoginPage;
import com.ecommerce.qa.pages.ProductPage;
import com.ecommerce.qa.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class InitializeTest {

    public BrowserFactory browserFactory;
    public Properties prop;
    public WebDriver driver;

    public LoginPage loginPage;
    public AccountPage accountPage;
    public SearchPage searchPage;
    public ProductPage productPage;

    //@BeforeTest
    @Parameters("browser")
    public void setup(String browserName){
        browserFactory= new BrowserFactory();
        prop = browserFactory.getProperties();
        String browser = prop.getProperty("browser");
        if(browserName!=null) {
            browser = browserName;
        }
            driver = browserFactory.init_Driver(browser);
            loginPage = new LoginPage(driver);
            driver.get(prop.getProperty("url"));
    }
    //@AfterTest
    public void tearDown(){
        driver.quit();
    }
}
