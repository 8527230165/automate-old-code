package com.segurosbolivar.automation.commons.dataprovider;

import com.segurosbolivar.automation.commons.Data;
import com.segurosbolivar.automation.commons.services.DataHelper;
import com.segurosbolivar.automation.commons.services.utils.ServiceConstants;
import com.segurosbolivar.automation.commons.utils.DataUtil;
import com.segurosbolivar.automation.commons.utils.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Hashtable;


public class DataProviderSource {
    @DataProvider(name = "test-data")
    public static Object[][] getTestTypeData(Method method) throws Exception {
        Test testAnnotation = method.getAnnotation(Test.class);
        DataHelper dataHelper = new DataHelper(ServiceConstants.PLATFORM_ID, testAnnotation.id());
        JSONArray data = dataHelper.getArrData();
        JSONObject asserts = dataHelper.getAsserts();

        int size = data.size();
        Object[][] object = new Object[size][1];

        for (int i = 0; i < size; i++) {
            object[i][0] = new Data(JsonParser.toJSONObject(data.get(i)), asserts);
        }
        return object;
    }
   /* @DataProvider(name = "test-data")
    public static Object[][] getTestData() throws Exception {
    	int rows = DataUtil.casesexcel.getRowCount(DataUtil.seccionProductSheetName);
		int cols = DataUtil.casesexcel.getColumnCount(DataUtil.seccionProductSheetName);
		Object[][] data = new Object[rows-1][1];
		Hashtable<String,String> table=null;
		for(int rowNum=2; rowNum<=rows; rowNum++){
				table=new Hashtable<String,String>();
				for(int colNum=0; colNum<cols; colNum++){
					table.put(DataUtil.casesexcel.getCellData(DataUtil.seccionProductSheetName, colNum, 1), DataUtil.casesexcel.getCellData(DataUtil.seccionProductSheetName, colNum, rowNum));
					data[rowNum-2][0]=table;
				}
		}
		return data;
    }*/
}