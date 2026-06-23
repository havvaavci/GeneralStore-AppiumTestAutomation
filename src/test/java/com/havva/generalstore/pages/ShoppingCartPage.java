package com.havva.generalstore.pages;

import com.havva.generalstore.utils.Driver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

public class ShoppingCartPage extends BasePage {

    private AndroidDriver driver;

    public ShoppingCartPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------------- LOCATORS ----------------

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrices;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmountLabel;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement termsCheckBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedButton;


    // ---------------- METHODS ----------------

    public int getProductCount() {
        test.info("Retrieving product count from cart");
        int count = productPrices.size();
        test.pass("Product count: " + count);
        return count;
    }

    public double calculateCartTotal() {
        test.info("Calculating expected total price from product list");

        double total = 0;

        for (WebElement priceElement : productPrices) {
            String priceText = priceElement.getText(); // e.g. "$160.97"
            double price = Double.parseDouble(priceText.substring(1));
            total += price;
        }

        test.pass("Calculated expected total: $" + total);
        return total;
    }

    public double getDisplayedTotal() {
        test.info("Retrieving displayed total amount from UI");

        String totalText = totalAmountLabel.getText(); // e.g. "$160.97"
        double displayed = Double.parseDouble(totalText.substring(1));

        test.pass("Displayed total: $" + displayed);
        return displayed;
    }

    public void acceptTerms() {
        test.info("Accepting Terms & Conditions");
        termsCheckBox.click();
        test.pass("Terms & Conditions accepted");
    }

    public void clickProceed() {
        test.info("Clicking on Proceed button");
        proceedButton.click();

        duration(2);

        test.info("Waiting for WebView context to appear");

        // Wait until WebView context appears
        Set<String> contexts = driver.getContextHandles();
        int retry = 0;

        while (!contexts.toString().contains("WEBVIEW") && retry < 10) {
            duration(1);
            contexts = driver.getContextHandles();
            retry++;
        }

        // Switch to WebView
        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                test.pass("WebView context detected: " + context);
                driver.context(context);
                return;
            }
        }

        test.fail("Failed to switch to WebView context");
    }
}
