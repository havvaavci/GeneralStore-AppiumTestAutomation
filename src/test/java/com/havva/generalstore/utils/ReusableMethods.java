package com.havva.generalstore.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.io.IOException;

public class ReusableMethods {

    public static void  duration(int second){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Bekleme yapilamadi" );
            throw new RuntimeException(e);
        }
    }

    public static String takeScreenshot(String testName) {

        // Screenshot alma
        File srcFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);

        // Kaydedilecek yol
        String screenshotPath = System.getProperty("user.dir")
                + "/reports/" + testName + ".png";

        // Dosyayı reports klasörüne kopyalama
        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

    // 1. CLICK GESTURES
    public void clickGesture(AndroidDriver driver, WebElement element) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    public void clickGestureCoordinate(AndroidDriver driver, int x, int y) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "x", x,
                "y", y
        ));
    }

    // 2. DOUBLE CLICK GESTURES (Hatalı script ismi düzeltildi)
    public void doubleClickGesture(AndroidDriver driver, WebElement element) {
        driver.executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    public void doubleClickGestureCoordinate(AndroidDriver driver, int x, int y) {
        driver.executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "x", x,
                "y", y
        ));
    }

    // 3. LONG CLICK GESTURES (Sabit 1000 ms değeri dinamik yapıldı)
    public void longClickGestureElement(AndroidDriver driver, WebElement element, int milisecond) {
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", milisecond
        ));
    }

    public void longClickGestureElementCoordinate(AndroidDriver driver, int x, int y, int milisecond) {
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "x", x,
                "y", y,
                "duration", milisecond
        ));
    }

    // 4. DRAG GESTURES (Sabit koordinatlar gelen parametrelere bağlandı)
    public void dragGesture(AndroidDriver driver, WebElement element, int endX, int endY) {
        driver.executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", endX,
                "endY", endY
        ));
    }

    public void dragGestureCoordinate(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        driver.executeScript("mobile: dragGesture", ImmutableMap.of(
                "startX", startX,
                "startY", startY,
                "endX", endX,
                "endY", endY
        ));
    }

    // 5. SCROLL GESTURE
    public void scrollGestureElement(AndroidDriver driver, WebElement element, String direction, double percent,int speed) {
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", percent,
                "speed",speed
        ));
    }

    // 6. SWIPE GESTURES ( Element ve Koordinat Tabanlı Swipe Metotları)
    public void swipeGestureElement(AndroidDriver driver, WebElement element, String direction, double percent, int speed) {
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction, // "right", "left", "up", "down"
                "percent", percent,     // 0.0 ile 1.0 arası
                "speed", speed          // Kaydırma hızı (Örn: 1000)
        ));
    }

    public void swipeGestureCoordinate(AndroidDriver driver, int left, int top, int width, int height, String direction, double percent, int speed) {
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", left,
                "top", top,
                "width", width,
                "height", height,
                "direction", direction,
                "percent", percent,
                "speed", speed
        ));
    }

    // 7. PINCH GESTURES (OPEN/CLOSE)
    public void pinchOpenGestureElement(AndroidDriver driver, WebElement element, double percent) {
        driver.executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "percent", percent
        ));
    }

    public void pinchOpenGestureCoordinate(AndroidDriver driver, int left, int top, int width, int height, double percent, int speed) {
        driver.executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "left", left,
                "top", top,
                "width", width,
                "height", height,
                "percent", percent,
                "speed", speed
        ));
    }

    public void pinchCloseGestureElement(AndroidDriver driver, WebElement element, double percent) {
        driver.executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "percent", percent
        ));
    }

    public void pinchCloseGestureCoordinate(AndroidDriver driver, int left, int top, int width, int height, double percent, int speed) {
        driver.executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
                "left", left,
                "top", top,
                "width", width,
                "height", height,
                "percent", percent,
                "speed", speed
        ));
    }
}