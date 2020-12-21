package com.qa.base;


import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Sheet;

import com.qa.util.ExcelReader;
public class PropertiesData {
	public String[] getTestMethodData(Method method){
		Sheet   sheet =	ExcelReader.readExcel("E:\\webgoat\\test.xlsx");
		String[] data=null;
		if(method.getName().equals("addUser")) {
			 data= sheet.getRow(2).getCell(3).getStringCellValue().split("\\|");
		}
		
		return data;
    }
	
}
