package com.havva.generalstore.tests;

import com.havva.generalstore.pages.FormPage;
import com.havva.generalstore.pages.ProductCatalogPage;
import com.havva.generalstore.pages.ShoppingCartPage;
import com.havva.generalstore.utils.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
