package com.ecommerce.qa.pages;

import com.ecommerce.qa.intialize.BrowserFactory;
import com.ecommerce.qa.util.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BrowserFactory {
    private WebDriver driver;
    private ElementActions elementActions;

    private By searchHeader = By.xpath("//h2[text()='Products meeting the search criteria']");
    private By searchResults = By.xpath("//div[@id='content']/div[3]/div//h4/a");


    public SearchPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }
    public boolean validate_SearchHeader(){
        return elementActions.isElementDisplayed(searchHeader);
    }
    public boolean verifyProductSearchResultsSize() {
        if(elementActions.getWebElements(searchResults).size()>0){
            return true;
        }
        return  false;
    }
    public List<String> verifyProductDetailsInSearchResults(String productName) {
        List<WebElement> results = elementActions.getWebElements(searchResults);
        List<String> prodctDetails = new ArrayList<>();
        for(WebElement result: results) {
            prodctDetails.add(result.getText());
        }
        return prodctDetails;
    }
    public void selectProductFromSearchResult(String productName){
        List<WebElement> productsresults = elementActions.getWebElements(searchResults);
        for(WebElement productResult : productsresults ) {
            if(productResult.getText().equalsIgnoreCase(productName)) {
                productResult.click();
                break;
            }
        }
    }
    public ProductPage selectProductFromSearchResult(String productName,String optional){
        List<WebElement> productsresults = elementActions.getWebElements(searchResults);
        for(WebElement productResult : productsresults ) {
            if(productResult.getText().equalsIgnoreCase(productName)) {
                productResult.click();
                break;
            }
        }
        return new ProductPage(driver);
    }
}
