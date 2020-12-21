package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.DataProvider;


public class TestBase {
	
	public final static int RESPNSE_STATUS_CODE_200 = 200;
	public final static int RESPNSE_STATUS_CODE_400 = 400;
	public final static int RESPNSE_STATUS_CODE_500 = 500;
	public final static int RESPNSE_STATUS_CODE_401 = 401;
	
	
	public  Properties pro;
	public TestBase() {
		pro =new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			pro.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	  @DataProvider(name="data")
	    public Object[][] dataProvider(Method method){
	  
	    	PropertiesData testData = new PropertiesData();
	        String[] listData = testData.getTestMethodData(method);
	        Object[][] object = new Object[listData.length][];
	        for (int i = 0; i < listData.length; i++) {
	            object[i] = new Object[]{listData[i]};
	        }
	        return object;
	    }
	    
	 
}
