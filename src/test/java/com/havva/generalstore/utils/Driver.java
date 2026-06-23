package com.havva.generalstore.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static AndroidDriver driver;
    private static AppiumDriverLocalService service;

    public static AndroidDriver getDriver() {

        if (driver == null) {

            boolean autoStart = Boolean.parseBoolean(ConfigReader.get("appium.autoStart"));
            String ip = ConfigReader.get("appium.ip");
            int port = Integer.parseInt(ConfigReader.get("appium.port"));

            if (autoStart) {
                startAppiumServer(ip, port);
            }

            // 🔥 1) Gerçek cihaz var mı kontrol et
            String deviceName = getAvailableDevice();

            if (deviceName == null) {
                // 🔥 2) Gerçek cihaz yok → emulator fallback
                deviceName = "emulator-5554";
                System.out.println("No real device detected. Using emulator: " + deviceName);
            } else {
                System.out.println("Real device detected: " + deviceName);
            }

            // 🔥 3) Driver capabilities
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName(deviceName)
                    .setApp(new File(ConfigReader.get("appPath")).getAbsolutePath())
                    .setAutomationName("UiAutomator2");

            try {
                URL serverUrl = autoStart ? service.getUrl() : new URL("http://" + ip + ":" + port);
                driver = new AndroidDriver(serverUrl, options);
            } catch (Exception e) {
                throw new RuntimeException("Driver could not be created: " + e.getMessage());
            }

            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicitWait")))
            );
        }

        return driver;
    }

    // 🔥 Gerçek cihaz var mı kontrol eden method
    private static String getAvailableDevice() {
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device") && !line.startsWith("List")) {
                    return line.split("\t")[0];  // cihaz ID'si
                }
            }
        } catch (Exception e) {
            System.out.println("Error checking devices: " + e.getMessage());
        }

        return null; // cihaz yok
    }

    private static void startAppiumServer(String ip, int port) {

        if (service == null) {
            service = new AppiumServiceBuilder()
                    .withIPAddress(ip)
                    .usingPort(port)
                    .withLogFile(new File("appium-server.log"))
                    .build();

            service.start();
            System.out.println("🔥 Appium Server Started at: " + service.getUrl());
        }
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }

        if (service != null) {
            service.stop();
            System.out.println("🛑 Appium Server Stopped");
            service = null;
        }
    }
}
