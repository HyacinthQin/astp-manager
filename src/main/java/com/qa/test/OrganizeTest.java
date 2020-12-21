package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.ExcelReader;
import com.qa.util.JsonUtil;

public class OrganizeTest extends TestBase{
	CloseableHttpResponse response;
	RestClient restClient;
    static String token;
    OrganizeTest organize;
	final static Logger log=Logger.getLogger(PostApiTest.class);
 	 Sheet   sheet =	ExcelReader.readExcel("E:\\webgoat\\test.xlsx");
 	 String host =sheet.getRow(1).getCell(0).getStringCellValue() ;
 	
	@BeforeClass
	public void setUp() {
		restClient = new RestClient();
		HashMap<String,String> headers =new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=utf-8");
		try {
			response = restClient.post(host+sheet.getRow(1).getCell(2).getStringCellValue(), sheet.getRow(1).getCell(3).getStringCellValue(), headers);
			String responseString=EntityUtils.toString(response.getEntity());
			JSONObject responseJson=JSONObject.parseObject(responseString);
		    token=JsonUtil.getValueByJPath(responseJson, "TOKEN");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	@Test(priority =1,description="添加用户",dataProvider="data",dataProviderClass=com.qa.base.TestBase.class)
	public void addUser(String data) throws ClientProtocolException, IOException {
		restClient =new RestClient();
		HashMap<String, String> headmap=new HashMap<String, String>();
		headmap.put("Content-Type", "application/json;charset=utf-8");
		headmap.put("TOKEN", token);
		response =restClient.post(host+sheet.getRow(2).getCell(2).getStringCellValue(), data,headmap);
		String responseString = EntityUtils.toString(response.getEntity());
		System.out.println(responseString);
		 
	}
	
	@Test(priority =2,description="查询用户")
	public int  getUser() throws ClientProtocolException, IOException{
		restClient =new RestClient();
		HashMap<String, String> headmap=new HashMap<String, String>();
		headmap.put("TOKEN", token);
		response = restClient.get(host+sheet.getRow(3).getCell(2).getStringCellValue(), headmap);
		String responseString = EntityUtils.toString(response.getEntity());
		JSONObject obj = JSONObject.parseObject(responseString);
		JSONArray array = obj.getJSONArray("list");
		int user_id= (Integer) array.getJSONObject(0).get("id");
		//Assert.assertEquals(user_id, 5);
		return user_id;		
	}
	
	@Test(priority = 3,description="编辑用户")
	public void editUser() throws ClientProtocolException, IOException {
		organize =new OrganizeTest();
		restClient =new RestClient();
		HashMap<String, String> headmap=new HashMap<String, String>();
		headmap.put("Content-Type", "application/json;charset=utf-8");
		headmap.put("TOKEN", token);
		response =restClient.put(host+sheet.getRow(4).getCell(2).getStringCellValue()+organize.getUser()+"/other", sheet.getRow(4).getCell(3).getStringCellValue(), headmap);
		//String responseString = EntityUtils.toString(response.getEntity());
		
		
	}
	
	@Test(priority= 4,description="删除用户" )
	public void delteUser() throws ClientProtocolException, IOException {
		organize =new OrganizeTest();
		restClient =new RestClient();
		HashMap<String, String> headmap=new HashMap<String, String>();
		headmap.put("TOKEN", token);
		response = restClient.delete(host + sheet.getRow(5).getCell(2).getStringCellValue()+ organize.getUser(),headmap);
		//String responseString = EntityUtils.toString(response.getEntity());
		
	}
	@Test(priority=5,description="添加小组")
	public void addGroup() throws ClientProtocolException, IOException {
		organize =new OrganizeTest();
		restClient =new RestClient();
		HashMap<String, String> headmap=new HashMap<String, String>();
		headmap.put("Content-Type", "application/json;charset=utf-8");
		headmap.put("TOKEN", token);
		response =restClient.put(host+sheet.getRow(6).getCell(2).getStringCellValue(), sheet.getRow(6).getCell(3).getStringCellValue(), headmap);
		/*String responseString = EntityUtils.toString(response.getEntity());
		System.out.println(responseString);*/
	}

}
