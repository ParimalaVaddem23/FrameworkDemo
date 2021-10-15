package com.ecommerce.qa.tests;

import com.ecommerce.qa.intialize.InitializeTest;
import com.ecommerce.qa.util.Constant;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductTests extends InitializeTest {

    @BeforeClass
    public void searchPageSetUp(){
        accountPage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
        searchPage = accountPage.doSearchForProduct("iMac");
        productPage = searchPage.selectProductFromSearchResult("iMac",null);
    }

    @Test
    public void validate_ProductDetails_And_AddTOCart(){
        Assert.assertEquals(productPage.productName(), Constant.PRODUCT_NAME);
        Assert.assertEquals(productPage.productBrand(), Constant.PRODUCT_BRAND);
        Assert.assertTrue(productPage.productCode().contains(Constant.PRODUCT_CODE));
        Assert.assertTrue(productPage.productAvailability().contains(Constant.PRODUCT_AVAILABILITY));
        Assert.assertTrue(productPage.productPrice().contains(Constant.PRODUCT_PRICE));
        Assert.assertTrue(productPage.productExTax().contains(Constant.PRODUCT_EX_TAX));
        String successMessage = productPage.addProductToCartAndValidateSuccessMessage("iMac");
        System.out.println("***"+successMessage);
    }

}

