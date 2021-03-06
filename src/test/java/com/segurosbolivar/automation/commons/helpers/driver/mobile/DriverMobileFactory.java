package com.segurosbolivar.automation.commons.helpers.driver.mobile;

import com.segurosbolivar.automation.commons.helpers.driver.mobile.platforms.AndroidDriver;
import com.segurosbolivar.automation.commons.helpers.driver.mobile.platforms.IOSDriver;
import com.segurosbolivar.automation.commons.utils.PropertyManager;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DriverMobileFactory {
    private AppiumDriver<?> mobileDriver;
    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(DriverMobileFactory.class);

    AppiumDriver<?> getDriver() throws Exception {

        if (null == mobileDriver) {
            String option = PropertyManager.getConfigValueByKey("mobileDriver").toLowerCase();
            switch (option) {
                case "android":
                    return mobileDriver = AndroidDriver.getDriver();
                case "ios":
                    return mobileDriver = IOSDriver.getDriver();
                default:
                    throw new Exception("Driver" + option + "Not Found");
            }
        }
        return mobileDriver;
    }

    AppiumDriver<?> getCurrentDriver() {
        return mobileDriver;
    }

    void quitDriver() {
        if (null != mobileDriver) {
            try {
                mobileDriver.quit();
                mobileDriver = null;

            } catch (Exception ex) {
                log.warn(ex.getMessage());
            }
        }
    }
}