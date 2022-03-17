package com.segurosbolivar.automation.commonslocal.layout;
import com.segurosbolivar.automation.commons.methods.web.WebGlobalMethods;
import com.segurosbolivar.automation.commons.methods.web.factory.WebMethodsFactory;
import org.json.simple.JSONObject;

public class Header {

    private WebGlobalMethods methods;

    public Header(WebGlobalMethods methods) {
        this.methods = methods;
    }

    public void clickOnEntry(){
        methods.waitForPageLoad();
        methods.waitingForElement("headPerson", 5);
        methods.clickElement("headPerson");
    }

}
