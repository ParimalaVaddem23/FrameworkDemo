package com.ecommerce.qa.tests;
import com.ecommerce.qa.intialize.InitializeTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class AccountTests extends InitializeTest {

    @BeforeClass
    public void accountPageSetUp(){
        accountPage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
    }
    @Test(priority=0)
    public void verify_MyAccountHeaderHeader() {
        Assert.assertTrue(accountPage.verifyMyAccountHeader());
    }
    @Test(priority=1)
    public void verify_MyOrdersHeader() {
        Assert.assertTrue(accountPage.verifyMyOrdersHeader());
    }
    @Test(priority=2)
    public void verify_MyAffiliateAccountHeader() {
        Assert.assertTrue(accountPage.verifyMyAffiliateAccountHeader());
    }
    @Test(priority=3)
    public void verify_SearchForProduct(){
        accountPage.doSearch("Mac");
    }
}
