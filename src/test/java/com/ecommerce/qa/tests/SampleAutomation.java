package com.ecommerce.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleAutomation {
    @Test
    public void verify_Assertion(){

        System.out.println("Executing from Jenkins");
        Assert.assertEquals("Hello", "Hello");
    }
}
