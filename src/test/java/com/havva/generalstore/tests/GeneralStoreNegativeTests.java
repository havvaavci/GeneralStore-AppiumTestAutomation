package com.havva.generalstore.tests;

import com.havva.generalstore.pages.FormPage;

import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.havva.generalstore.listeners.TestListener.class)
public class GeneralStoreNegativeTests {



    @Test
    public void tc01_validateToastWhenNameIsEmpty() {
       FormPage formPage = new FormPage();
        formPage.clickLetsShop();

        String actualToast = formPage.getToastMessage();
        Assert.assertEquals(actualToast, "Please enter your name",
                "Toast message mismatch when name is empty!");
    }
}
