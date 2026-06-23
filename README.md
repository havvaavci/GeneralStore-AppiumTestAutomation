# 📱 Appium Mobile Test Automation – GeneralStore

A mobile test automation framework built with Appium, Java, and TestNG for the GeneralStore Android application.

---

## 🛠️ Tech Stack

| Tool               | Version |
|--------------------|---------|
| Java               | 21      |
| Appium             | 2.x     |
| Appium Java Client | 8.6.0   |
| Selenium           | 4.15.0  |
| TestNG             | 7.11.0  |
| ExtentReports      | 4.1.7   |

---

## 📁 Project Structure

```
src/
├── main/java/com/havva/generalstore/
│   └── pages/
│       ├── BasePage
│       ├── FormPage
│       ├── ProductCatalogPage
│       ├── ShoppingCartPage
│       └── OrderCompletePage
└── test/java/com/havva/generalstore/
    ├── listeners/
    │   └── TestListener
    ├── tests/
    │   ├── GeneralStorePositiveTests
    │   └── GeneralStoreNegativeTests
    └── utils/
        ├── Driver
        ├── ConfigReader
        ├── ExtentReportManager
        └── ReusableMethods
reports/
└── GeneralStoreReport.html
config.properties
Jenkinsfile
```

---

## ✅ Test Scenarios

- Form filling with dynamic user input
- Product selection and cart addition
- Cart validation (product count + total price)
- WebView transition and Google search interaction
- Return to native app and toolbar validation
- End-to-end HTML report with step-by-step logging via ExtentReports

---

## ⚙️ Configuration

All settings are managed in `src/test/resources/config.properties`:

```properties
appium.autoStart=true
appium.ip=127.0.0.1
appium.port=4723
appPath=src/test/resources/GeneralStore.apk
implicitWait=10
```

Any value can be overridden from Jenkins or command line:

```bash
mvn test -DappPath=/custom/path/app.apk -Dappium.port=4724
```

---

## 📱 Device Support

The `Driver` class automatically detects the environment at runtime — no manual changes needed:

- **Real device connected** → used automatically (detected via `adb devices`)
- **No real device** → falls back to `emulator-5554`
- **Appium server** → started automatically by the framework when `appium.autoStart=true`, or connects to an already-running instance

---

## 🏗️ Architecture

- **BasePage** – all page classes extend this; holds shared driver access and common actions
- **Driver** – singleton, handles device detection and Appium server lifecycle
- **ConfigReader** – reads `config.properties`, supports Jenkins `-D` parameter override
- **TestListener** – logs each test step to the report in real time via TestNG hooks
- **ExtentReportManager** – generates the HTML test report as a singleton

---

## 🔧 Jenkins Integration

A `Jenkinsfile` is included. Tests can be run with custom parameters:

```groovy
mvn test -DappPath=${APP_PATH} -Dappium.ip=${APPIUM_IP}
```

> ⚠️ The Jenkins agent must have ADB access and a connected device or running emulator.

---

## ▶️ Demo

📹 Real test run recorded via Vysor on a physical Android device.

---

## 🚀 How to Run

```bash
git clone https://github.com/havvaavci/GeneralStore-AppiumTestAutomation.git
mvn test
```

> ⚠️ Requires a connected Android device or a running emulator.
