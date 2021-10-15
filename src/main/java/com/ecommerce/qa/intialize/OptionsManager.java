package com.ecommerce.qa.intialize;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    public ChromeOptions chromeOptions;
    public FirefoxOptions firefoxOptions;
    public Properties prop;

    public OptionsManager(Properties prop){
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions(){
        chromeOptions = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            chromeOptions.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            chromeOptions.addArguments("--incognito");
        }
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions(){
        firefoxOptions = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;
    }
}
