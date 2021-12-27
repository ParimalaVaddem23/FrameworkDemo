package com.ecommerce.qa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.URL;

import static org.openqa.selenium.Platform.*;

public class SampleAutomation {
    @Test
    public void verify_Assertion() {
        WebDriver driver;
        System.out.println("Executing from Jenkins");
        System.out.println("Added Web Hook");
        Assert.assertEquals("Hello", "Hello");
//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("headless");
//        chromeOptions.addArguments("disable-gpu");
//        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        //WebDriverManager.chromedriver().setup();
//        cap.setCapability("version","94.0.4606.61");
//        //cap.setPlatform(Platform.LINUX);
//        try {
//            driver = new RemoteWebDriver(new URL("http://65.2.79.227:4444/wd/hub"), cap);
//            driver.get("https://www.google.com");
//            System.out.println("Title of the page is: " + driver.getTitle());
//            Assert.assertTrue(driver.getTitle().equals("Google"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
