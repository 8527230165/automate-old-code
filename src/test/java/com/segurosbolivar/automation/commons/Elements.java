package com.segurosbolivar.automation.commons;

import org.json.simple.JSONObject;

import com.segurosbolivar.automation.commons.services.DataService;
import com.segurosbolivar.automation.commons.services.utils.ServiceConstants;
import com.segurosbolivar.automation.commons.utils.JsonParser;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Elements {
    private static JSONObject response;
    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Elements.class);

    public static void initElements() {
        if (response == null) {
            log.info("Call Elements ....");
            response = DataService.getElements(ServiceConstants.PLATFORM_ID, ServiceConstants.ENVIRONMENT_ID);
        }
    }

    public static JSONObject getWebElements() {
        initElements();
        return JsonParser.toJSONObject(response.get("webElements"));
    }

    public static JSONObject getMobileElements() {
        initElements();
        return JsonParser.toJSONObject(response.get("mobileElements"));
    }
}
