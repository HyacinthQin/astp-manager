package com.qa.webgoat;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.JsonUtil;


public class WebGoatApiTest extends TestBase {
	
	TestBase testBase;
	static String webgoat_host;
	String url;
	CloseableHttpResponse response;
	RestClient restClient;
    static String token;
    WebGoatApiTest webgoat;
	final static Logger log=Logger.getLogger(WebGoatApiTest.class);
	
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
		webgoat_host = pro.getProperty("webgoat_host");
		url = webgoat_host + "/WebGoat/j_spring_security_check";
		restClient = new RestClient();
		//添加请求头信息
		HashMap<String,String> headers =new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		//创建CookieStore对象用来获取cookie
		CookieStore cookieStore = new BasicCookieStore();
		try {
			response = restClient.post(url, "username=webgoat&password=webgoat", headers);
			System.out.println(response.getStatusLine());
			List<Cookie> cookies = cookieStore.getCookies();
			
			for (Cookie cookie : cookies) {
				
				System.out.println(cookie.toString());
				System.out.println(cookie.getName()+"--"+cookie.getValue());
			}

		
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@Test(priority=0,description="LAB: Cross Site Scripting")
	public void queryAgentTest() throws Exception {
		webgoat =new WebGoatApiTest();
		log.info("开始执行用例1...");
		restClient = new RestClient();
		HashMap<String, String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("TOKEN",token);
		response = restClient.post(webgoat_host+"/WebGoat/attack?Screen=611366032&menu=900", "employee_id=101&password=test&action=Login", headers);
		String responseString=EntityUtils.toString(response.getEntity());
		System.out.println(responseString);
		/*JSONObject responseJson=JSONObject.parseObject(responseString);
		JSONArray list=responseJson.getJSONArray("list");
		System.out.println(list);
	    int agent_id=(Integer) list.getJSONObject(0).get("id");
		return agent_id;*/
	}

}
