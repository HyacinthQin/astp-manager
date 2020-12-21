package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.JsonUtil;

public class PostApiTest extends TestBase {
	
	TestBase testBase;
	static String host;
	String url1;
	static String url2;
	CloseableHttpResponse response;
	RestClient restClient;
    static String token;
    PostApiTest p;
	final static Logger log=Logger.getLogger(PostApiTest.class);
	//Map<String,User>  result=ExcelReader.readExcel("D:\\test.xlsx", 0);
	
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
		host = pro.getProperty("host");
		url1 = host + "/api/logon";
		//url2 = host + "/api/agent";
		restClient = new RestClient();
		//添加请求头信息
		HashMap<String,String> headers =new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=utf-8");
		//对象转换成json字符串
		//User data=new User(result.get("login1").getUsername(),result.get("login1").getPassword(),result.get("login1").getCode());
		//String userJsonString=JSON.toJSONString(data);
		try {
			response = restClient.post(url1, "{\"username\":\"testql\",\"password\":\"12345678a\",\"code\":\"0000\"}", headers);
			String responseString=EntityUtils.toString(response.getEntity());
			JSONObject responseJson=JSONObject.parseObject(responseString);
		    token=JsonUtil.getValueByJPath(responseJson, "TOKEN");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	/*
		//断言响应json内容中name和job是不是期待结果
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
		JSONObject responseJson = JSON.parseObject(responseString);
		//System.out.println(responseString);
		String name = TestUtil.getValueByJPath(responseJson, "name");
		String job = TestUtil.getValueByJPath(responseJson, "job");
		Assert.assertEquals(name, "Anthony","name is not same");
		Assert.assertEquals(job, "tester","job is not same");
		
	}*/
	

	@Test(priority=0,description="查询服务器")
	public Integer queryAgentTest() throws Exception {
		p =new PostApiTest();
		log.info("开始执行用例2...");
		restClient = new RestClient();
		HashMap<String, String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=utf-8");
		headers.put("TOKEN",token);
		response = restClient.get(host+"/api/agent", headers);
		String responseString=EntityUtils.toString(response.getEntity());
		//System.out.println(responseString);
		JSONObject responseJson=JSONObject.parseObject(responseString);
		JSONArray list=responseJson.getJSONArray("list");
		//System.out.println(list);
	    int agent_id=(Integer) list.getJSONObject(0).get("id");
		return agent_id;
	}
	

	
	@Test(priority=1,description="查看某一服务器详情信息")
	public void getServerTest() throws Exception {
		log.info("开始执行用例3---");
		p =new PostApiTest();
		System.out.println(p.queryAgentTest());
		restClient =new RestClient();
		HashMap<String, String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=utf-8");
		headers.put("TOKEN",token);
		response = restClient.get(host+"/api/agent?secPointIds"+ p.queryAgentTest(),headers);
		String responseString=EntityUtils.toString(response.getEntity());
	   // System.out.println(responseString);
		
	}
	@Test(priority=2,description="查看服务器列表")
	public void getApp() throws ClientProtocolException, IOException {
		log.info("开始执行用例4---");
		p =new PostApiTest();
		restClient =new RestClient();
		HashMap<String, String> headmap=new HashMap<String, String>();
		headmap.put("TOKEN", token);
		response = restClient.get(host +"/api/application?",headmap);
		String responseString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseString);
		JSONObject responseJson = JSON.parseObject(responseString);
		JSONObject obj1= (JSONObject) responseJson.get("pagination");
		
		
	}
	
	
}
