package com.segurosbolivar.automation.commons;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.TestType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.automation.commons.helpers.driver.DriverConstants;
import com.segurosbolivar.automation.commons.helpers.driver.mobile.DriverMobileBase;
import com.segurosbolivar.automation.commons.helpers.driver.web.DriverWebBase;
import com.segurosbolivar.automation.commons.models.CaseExecution;
import com.segurosbolivar.automation.commons.models.Execution;
import com.segurosbolivar.automation.commons.utils.Constants;
import com.segurosbolivar.automation.commons.utils.Utils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestListener implements ITestListener {
	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TestListener.class);

    @Attachment(value = "Test Evidence Mobile", type = "image/png")
    public byte[] takeMobileScreenshot() {
        AppiumDriver<?> driver = DriverMobileBase.getCurrentDriver();
        TakesScreenshot scrShot = null;
        try {
            Allure.addAttachment("failure", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            scrShot = ((TakesScreenshot) driver);

        } catch (Exception ex) {
            log.error("Mobile Driver: " + driver);
        }
        return scrShot.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Test Evidence Web", type = "image/png")
    public byte[] takeWebScreenshot() {
        WebDriver driver = DriverWebBase.getCurrentDriver();
        TakesScreenshot scrShot = null;
        try {
            Allure.addAttachment("failure", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            scrShot = ((TakesScreenshot) driver);
        } catch (Exception ex) {
            log.error("Web Driver: " + driver);
        }
        return scrShot.getScreenshotAs(OutputType.BYTES);
    }


    @Override
    public void onTestStart(ITestResult iTestResult) {
        Test testAnnotation = Utils.getTestAnnotation(iTestResult);
        boolean isLocalDriver = Boolean.parseBoolean(DriverConstants.DRIVER_LOCAL);
        if(!isLocalDriver){
            DriverWebBase.getDriverRemote(testAnnotation.description());
        }
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Test testAnnotation = Utils.getTestAnnotation(iTestResult);

        TestType testType = testAnnotation.testType();

        boolean isLocalDriver = Boolean.parseBoolean(DriverConstants.DRIVER_LOCAL);



        if (testType == TestType.WEB) {
            takeWebScreenshot();
            if (!isLocalDriver) {
                WebDriver driver = DriverWebBase.getCurrentDriver();
                ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            }
            DriverWebBase.quitDriver();
        } else if (testType == TestType.MOBILE) {
            takeMobileScreenshot();
            DriverMobileBase.quitDriver();
        } else if (testType == TestType.HYBRID) {
            takeWebScreenshot();
            takeMobileScreenshot();
            DriverMobileBase.quitDriver();
            DriverWebBase.quitDriver();
        }



        try {
			sendTestMethodStatus(iTestResult, Constants.TEST_FAIL);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        boolean isLocalDriver = Boolean.parseBoolean(DriverConstants.DRIVER_LOCAL);

        Object[] params = iTestResult.getParameters();
        if (params.length > 0) {
            Data data = (Data) params[0];
            System.out.println(data.getData().toJSONString());

        }
        /*
        if (!isLocalDriver) {
            WebDriver driver = DriverWebBase.getCurrentDriver();
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        }

         */
        sendTestMethodStatus(iTestResult, Constants.TEST_SUCCESS);
    }

    private void sendTestMethodStatus(ITestResult iTestResult, Integer idStateExecution) throws JsonProcessingException {
        Test testAnnotation = Utils.getTestAnnotation(iTestResult);
        Integer idExecution = Execution.id;
        CaseExecution caseExecution = CaseExecution.builder()
                .idExecution(idExecution)
                .idCase(testAnnotation.id())
                .idStateExecution(idStateExecution)
                .startDate(new SimpleDateFormat(Constants.DATE_TIME_FORMAT).format(iTestResult.getStartMillis()))
                .endDate(new SimpleDateFormat(Constants.DATE_TIME_FORMAT).format(iTestResult.getEndMillis()))
                .build();
        //MetricsService.setCaseExecution(caseExecution);
    }

}
