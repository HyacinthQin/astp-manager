package com.qa.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestJson {
	
	
	public static void main(String args[]) {
		String jsonString =  "{\"username\":\"testql\",\"password\":\"12345678a\",\"code\":\"0000\"}"; 
		JSONObject jsobject = JSON.parseObject(jsonString);
		System.out.println(jsonString);
		System.out.println(jsobject);
	} 
}
