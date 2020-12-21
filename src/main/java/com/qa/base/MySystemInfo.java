package com.qa.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//import com.vimalselvam.testng.SystemInfo; implements SystemInfo

public class MySystemInfo  {

	public Map<String, String> getSystemInfo() {
		// TODO Auto-generated method stub
		return null;
	}
    /*public Map<String, String> getSystemInfo() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("env.properties");
        Properties properties = new Properties();
        Map<String, String> systemInfo = new HashMap<String, String>();
        try {
            properties.load(inputStream);
            systemInfo.put("测试环境", properties.getProperty("Environment"));
            systemInfo.put("测试人员", "qll");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return systemInfo;
    }
  */
}
