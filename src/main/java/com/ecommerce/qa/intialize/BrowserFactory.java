package com.ecommerce.qa.intialize;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import org.openqa.selenium.TakesScreenshot;

public class BrowserFactory {
    public WebDriver driver;
    public Properties prop;
    public OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return threadLocal.get();
    }
    private void init_RemoteDriver(String browser){
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        if(browser.equalsIgnoreCase("chrome")){
            System.out.println("running on remote grid"+browser);
            cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
            try {
                threadLocal.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
            } catch (Exception e) {
               e.printStackTrace();
            }
        } else if(browser.equalsIgnoreCase("firefox")){
            System.out.println("running on remote grid"+browser);
            cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
            try {
                threadLocal.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public WebDriver init_Driver(String browser){
        optionsManager = new OptionsManager(prop);
    if(browser.equalsIgnoreCase("chrome")){
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            //init_RemoteDriver(prop.getProperty("browser"));
            init_RemoteDriver(browser);
        }
        threadLocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
    }else if(browser.equalsIgnoreCase("firefox")){
        WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
           // init_RemoteDriver(prop.getProperty("browser"));
            init_RemoteDriver(browser);
        }
        threadLocal.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
    }else if(browser.equalsIgnoreCase("IE")){
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
    }else if(browser.equalsIgnoreCase("safari")){
        driver = new SafariDriver();
    } else {
        System.out.println("Invalid browser");
    }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
    return getDriver();
}
public Properties getProperties() {
 prop = new Properties();
    try {
        FileInputStream fis = new FileInputStream("./src/main/java/com/ecommerce/qa/config/config.properties");
        prop.load(fis);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return  prop;
}
    public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

}
