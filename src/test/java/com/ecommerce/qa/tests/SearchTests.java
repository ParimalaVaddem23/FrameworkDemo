package com.ecommerce.qa.tests;

import com.ecommerce.qa.intialize.InitializeTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests extends InitializeTest {
    @BeforeClass
    public void accountPageSetUp(){
        accountPage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
        searchPage = accountPage.doSearchForProduct("Mac");
    }
    @Test(priority=0)
    public void verify_Header() {
        Assert.assertTrue(searchPage.validate_SearchHeader());
    }
    @Test(priority=0)
    public void verify_Product_SearchResultsSize() {
        Assert.assertTrue(searchPage.verifyProductSearchResultsSize());
    }
    @Test(priority=1)
    public void verify_Products_SearchResults() {
        List<String> productSearchResults = searchPage.verifyProductDetailsInSearchResults("Mac");
        for(String productSearchResult : productSearchResults){
            Assert.assertTrue(productSearchResult.contains("Mac"));
        }
    }
    @Test(priority=2)
    public void Select_Product_FromSearchResult() {
        searchPage.selectProductFromSearchResult("iMac");
    }

}
