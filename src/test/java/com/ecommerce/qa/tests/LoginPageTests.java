package com.ecommerce.qa.tests;

import com.ecommerce.qa.intialize.InitializeTest;
import com.ecommerce.qa.util.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginPageTests extends InitializeTest {

    @Test
    public  void signUpTODemoOpenCart(){
        //loginPage.signUp();
        //Assert.assertEquals(loginPage.pageTitle(), Constant.LOGIN_PAGE_TITLE);
    }

    @Test
    public void verifyForgotPasswordLink(){
        Assert.assertTrue(loginPage.verifyForgottenPasswordExists());
    }
}
