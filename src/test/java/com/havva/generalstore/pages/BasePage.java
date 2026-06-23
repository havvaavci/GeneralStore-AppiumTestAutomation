package com.havva.generalstore.pages;

import com.aventstack.extentreports.ExtentTest;
import com.havva.generalstore.utils.ExtentReportManager;
import com.havva.generalstore.utils.ReusableMethods;

public class BasePage extends ReusableMethods {
    protected ExtentTest test;
    public BasePage() {
        this.test = ExtentReportManager.getTest();
    }
}
