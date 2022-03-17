package com.segurosbolivar.automation.tests.login;
import org.json.simple.JSONObject;
import org.junit.Assert;

import com.segurosbolivar.automation.commons.utils.DataUtil;
import com.segurosbolivar.automation.commonslocal.BaseComponent;

import io.qameta.allure.Step;

public class StepsLogin  extends BaseComponent {

    public StepsLogin(JSONObject json){
        super(json);
    }
    @Step("The user clicks on the login Link in the Header")
    public StepsLogin clickEntry() {
        methods.waitingForElement("login", 5);
        methods.sendKeysText("user_id",DataUtil.userName);
        methods.clickElement("login");
        return this;
    }

    @Step("Change window")
    public StepsLogin changeWindow() throws InterruptedException {
        methods.changeWindow();
        return this;
    }

    @Step("Click on ok btn alert popup")
    public StepsLogin clickOkAlert() {
        boolean isVisible =  methods.visibleElement("ok",10);
        if(isVisible){
        methods.clickElement("ok");
        }else{
            Assert.fail();
        }
        return this;
    }

    @Step("Choose company list option")
    public StepsLogin chooseCompanyList(String company) {
        methods.selectElementDropDown("companyDropdown",company);
        return this;
    }

    @Step("click on continue button")
    public StepsLogin clickBtnContinue() {
        methods.waitForPageLoad();
        boolean isVisible = methods.visibleElement("btnContinue",10);
        if(isVisible){
            methods.clickSubmitElement("btnContinue");
        }else{
            Assert.fail();
        }
        return this;
    }
    
    @Step("click to sinietsro test menu")
    public StepsLogin clickSiniestroTest() {
        methods.waitForPageLoad();
        boolean isVisible = methods.visibleElement("siniestros_test",5);
        if(isVisible){
            methods.clickElementAction("siniestros_test");
        }else{
            Assert.fail();
        }
        return this;
    }
    
    @Step("click to aviso de siniestro test menu")
    public StepsLogin clickAvisoDeSiniestroTest() {
        methods.waitForPageLoad();
        boolean isVisible = methods.visibleElement("aviso_de_siniestro_test",5);
        if(isVisible){
            methods.clickElementAction("aviso_de_siniestro_test");
        }else{
            Assert.fail();
        }
        return this;
    }
    
    @Step("click to pre aviso indemnizaciones menu")
    public StepsLogin clickPreAvisoIndemnizaciones() {
        methods.waitForPageLoad();
        boolean isVisible = methods.visibleElement("pre_aviso_indemniaciones",5);
        if(isVisible){
            methods.clickElementAction("pre_aviso_indemniaciones");
        }else{
            Assert.fail();
        }
        return this;
    }
}