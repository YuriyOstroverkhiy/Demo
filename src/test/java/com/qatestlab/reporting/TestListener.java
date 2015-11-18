package com.qatestlab.reporting;

import com.qatestlab.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;


public class TestListener extends TestListenerAdapter {


    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        byte[] screenshotAs = null;
        try {
             screenshotAs = ((TakesScreenshot) BaseTest.driver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e){
            fail(e);
        }

        return screenshotAs;
    }

    @Attachment(value = "Unable to save screenshot")
    private String fail(Exception e){
        String a = e.getMessage() + "\n" + e.getStackTrace().toString();
        return a;
    }
}
