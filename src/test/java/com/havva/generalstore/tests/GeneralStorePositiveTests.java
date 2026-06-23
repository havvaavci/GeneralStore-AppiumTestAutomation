package com.havva.generalstore.tests;

import com.havva.generalstore.pages.FormPage;
import com.havva.generalstore.pages.OrderCompletePage;
import com.havva.generalstore.pages.ProductCatalogPage;
import com.havva.generalstore.pages.ShoppingCartPage;

import org.testng.Assert;

import org.testng.annotations.Test;

public class GeneralStorePositiveTests {



    @Test
    public void tc01_validateGeneralStorePositiveFlow() {
        FormPage formPage = new FormPage();
        ProductCatalogPage productCatalogPage = new ProductCatalogPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        OrderCompletePage orderPage = new OrderCompletePage();

        // FORM PAGE
        formPage.selectCountry("Brazil");
        formPage.enterName("Havva");
        formPage.selectGender("female");
        formPage.clickLetsShop();

        // PRODUCT CATALOG PAGE
        productCatalogPage.addProductToCart("Converse All Star");
        productCatalogPage.addProductToCart("Air Jordan 9 Retro");
        productCatalogPage.goToCart();
        Assert.assertTrue(productCatalogPage.isCartPageDisplayed(), "Cart page is not displayed!");

        // SHOPPING CART PAGE
        Assert.assertEquals(shoppingCartPage.getProductCount(), 2, "Product count mismatch!");

        double expectedTotal = shoppingCartPage.calculateCartTotal();
        double displayedTotal = shoppingCartPage.getDisplayedTotal();
        Assert.assertEquals(expectedTotal, displayedTotal, "Total price mismatch!");

        shoppingCartPage.acceptTerms();
        shoppingCartPage.clickProceed();

        // ORDER COMPLETE PAGE (WEBVIEW)
        orderPage.clickRefuseCookies();
        orderPage.searchInGoogle("Appium");

        // RETURN TO NATIVE
        orderPage.returnToNative();
        Assert.assertEquals(orderPage.getToolbarTitle(), "General Store", "Toolbar title mismatch!");
    }
}
