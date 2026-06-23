package com.havva.generalstore.pages;

import com.havva.generalstore.utils.Driver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends BasePage {

    private AndroidDriver driver;

    public FormPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------------- LOCATORS ----------------

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryMenu;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleRadio;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleRadio;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopButton;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    private WebElement toastMessage;


    // ---------------- METHODS ----------------

    public void selectCountry(String countryName) {
        test.info("Selecting country: " + countryName);
        countryMenu.click();

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"))"
        ));

        driver.findElement(AppiumBy.xpath(
                "//android.widget.TextView[@text='" + countryName + "']"
        )).click();
    }

    public void enterName(String name) {
        test.info("Entering name: " + name);
        nameField.sendKeys(name);
    }

    public void selectGender(String sex) {
        test.info("Selecting gender: " + sex);

        if (sex.equalsIgnoreCase("female")) {
            femaleRadio.click();
        } else if (sex.equalsIgnoreCase("male")) {
            maleRadio.click();
        } else {
            throw new IllegalArgumentException(
                    "Invalid gender parameter: " + sex + " (Only 'male' or 'female' allowed)"
            );
        }
    }

    public String getToastMessage() {
        test.info("Retrieving toast message");
        return toastMessage.getAttribute("name");
    }

    public void clickLetsShop() {
        test.info("Clicking on 'Let's Shop' button");
        letsShopButton.click();
    }
}
