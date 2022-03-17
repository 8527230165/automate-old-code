package com.segurosbolivar.automation.commons.methods.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.automation.commons.services.MetricsService;
import com.segurosbolivar.automation.commons.utils.DataUtil;
import com.segurosbolivar.automation.commons.utils.ExcelReader;

public class CreateCases {
	//caseExcel.getRowCount(DataUtil.casesSheetName)
	public static Logger log=Logger.getLogger(CreateCases.class);
	/*public List<Integer> caseCreation() throws JsonProcessingException {
		List<Integer> caseIds=new ArrayList<Integer>();
		ExcelReader caseExcel=DataUtil.casesexcel;
		for(int i=2;i<caseExcel.getRowCount(DataUtil.casesSheetName)+1;i++) {
			String caseName=caseExcel.getCellData(DataUtil.casesSheetName,"Case_Name",i);
			String caseDescription=caseExcel.getCellData(DataUtil.casesSheetName,"Case_Description",i);
			String caseJiraId=caseExcel.getCellData(DataUtil.casesSheetName,"Case_IdJira",i);
			caseIds.add(MetricsService.createCase(caseName,caseDescription,caseJiraId));
		}
		return caseIds;
	}*/

}
