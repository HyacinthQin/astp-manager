package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.JsonUtil;


public class GetApiTest extends TestBase{
	TestBase testBase;
	String host;
	String url;
	CloseableHttpResponse response;
	RestClient restClient;
	final static Logger log=Logger.getLogger(GetApiTest.class);
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
		//Log.info("测试服务器地址为："+ host.toString());
		host = pro.getProperty("host");
		//Log.info("当前测试接口的完整地址为："+url.toString());
		url = host + "/api/logon";
	
	}
	
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		log.info("开始执行用例...");
		restClient = new RestClient();
		response = restClient.get(url);
		
		//断言状态码是不是200
		log.info("测试响应状态码是否是200");
		int statusCode = restClient.getStatusCode(response);
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "response status code is not 200");
		
        JSONObject responseJson = restClient.getResponseJson(response);
        //System.out.println("respon json from API-->" + responseJson); 
        
        //json内容解析
        String s = JsonUtil.getValueByJPath(responseJson,"data[0]/first_name");
        log.info("执行JSON解析，解析的内容是 " + s);
        //System.out.println(s);
        log.info("接口内容响应断言");
        Assert.assertEquals(s, "Eve","first name is not Eve");
        log.info("用例执行结束...");
	}
	
	
}



