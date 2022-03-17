package com.segurosbolivar.automation.commonslocal;

import org.json.simple.JSONObject;

import com.segurosbolivar.automation.commons.methods.web.CreateCases;
import com.segurosbolivar.automation.commons.methods.web.WebGlobalMethods;
import com.segurosbolivar.automation.commonslocal.layout.Header;

public class BaseComponent  {

   protected Header header;
   protected WebGlobalMethods methods;
   protected CreateCases cases;

    public  BaseComponent(JSONObject json){
        this.methods = new WebGlobalMethods(json);
        this.header = new Header(this.methods);
        this.cases = new CreateCases();
    }
}
