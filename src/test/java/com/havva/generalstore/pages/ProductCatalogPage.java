package com.havva.generalstore.pages;

import com.havva.generalstore.utils.Driver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductCatalogPage extends BasePage {

    private AndroidDriver driver;

    public ProductCatalogPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------------- LOCATORS ----------------

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    private List<WebElement> addToCartButtons;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;


    // ---------------- METHODS ----------------

    public void addProductToCart(String productName) {

        test.info("Adding product to cart: " + productName);

        // Scroll to the target product
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + productName + "\"))"));

        duration(1);

        // Get all product name elements
        List<WebElement> productNames = driver.findElements(
                AppiumBy.id("com.androidsample.generalstore:id/productName"));

        // Get all ADD TO CART buttons
        List<WebElement> addButtons = driver.findElements(
                AppiumBy.id("com.androidsample.generalstore:id/productAddCart"));

        // Click the ADD TO CART button matching the product index
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equals(productName)) {
                addButtons.get(i).click();
                test.pass("Product added to cart: " + productName);
                break;
            }
        }
    }

    public void goToCart() {
        test.info("Navigating to Cart page");
        cartButton.click();
    }

    public boolean isCartPageDisplayed() {
        test.info("Checking if Cart page is displayed");

        By titleLocator = By.id("com.androidsample.generalstore:id/toolbar_title");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));

        boolean isDisplayed = title.getText().equals("Cart");

        if (isDisplayed) {
            test.pass("Cart page displayed successfully");
        } else {
            test.fail("Cart page NOT displayed");
        }

        return isDisplayed;
    }

}
