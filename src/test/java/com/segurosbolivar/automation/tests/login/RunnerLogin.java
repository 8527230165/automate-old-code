package com.segurosbolivar.automation.tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.TestType;

import com.segurosbolivar.automation.commons.Data;
import com.segurosbolivar.automation.commons.Elements;
import com.segurosbolivar.automation.commons.Hooks;
import com.segurosbolivar.automation.commons.dataprovider.DataProviderSource;
import com.segurosbolivar.automation.commons.helpers.driver.DriverConstants;
import com.segurosbolivar.automation.commons.helpers.driver.web.DriverWebBase;

import lombok.extern.log4j.Log4j2;



@Log4j2
public class RunnerLogin extends Hooks {
    StepsLogin steps;
   // private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(RunnerLogin.class);

    @Test(
            id = 113,
            testType = TestType.WEB,
            dataProviderClass = DataProviderSource.class,
            dataProvider = "test-data",
            description = "Validate login"
    )
    public void loginSuccessfully(Data data){
        try {
        	DriverWebBase.getDriver().get(DriverConstants.WEB_URL_ENVIRONMENT);
        	log.info("Application Launching");
            this.steps = new StepsLogin(Elements.getWebElements());
            /*steps.clickEntry()
            .changeWindow()
            .clickOkAlert()
            .chooseCompanyList(data.getDataField("company"))
            .clickBtnContinue()
            .clickSiniestroTest()
            .clickAvisoDeSiniestroTest()
            .clickPreAvisoIndemnizaciones();*/

            //DriverWebBase.quitDriver();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

}