package com.havva.generalstore.pages;

import com.havva.generalstore.utils.Driver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletePage extends BasePage {

    private AndroidDriver driver;

    public OrderCompletePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Google search box (HTML element)
    @FindBy(name = "q")
    private WebElement searchBox;

    // Cookie refuse button
    @FindBy(xpath = "//*[text()='Tout refuser']")
    private WebElement refuseCookiesButton;


    // ---------------- METHODS ----------------

    public void clickRefuseCookies() {
        test.info("Attempting to refuse cookies");

        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});",
                    refuseCookiesButton
            );

            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    refuseCookiesButton
            );

            test.pass("Cookies refused successfully");

        } catch (Exception e) {
            test.fail("Failed to click cookie refuse button: " + e.getMessage());
        }
    }

    public void searchInGoogle(String text) {
        test.info("Performing Google search: " + text);
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void returnToNative() {
        test.info("Returning to native context");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }

    public String getToolbarTitle() {
        test.info("Retrieving toolbar title");
        return driver.findElement(AppiumBy.id(
                "com.androidsample.generalstore:id/toolbar_title"
        )).getText();
    }
}
