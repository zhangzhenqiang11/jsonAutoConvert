package com.bill99.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.bill99.utils.ConvertUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	
	static Set<String> keySet = new HashSet<String>();
	
	public static void main(String[] args) {
//		test01();
//		test02();
//		test03();
		
//		final String jsoStr = "{\"item\":[{'qq': '','ID_address': '',  'Email': '1838599723@qq.com'}," +
//  		"{'qq': '',  'House_Phone': '',  'ss': '',  'Email': '1404816232@qq.com'}]}";

//		Object[] objects = getStringArray4Json(jsoStr);
//		for (Object object : objects) {
//			addKey(object);
//		}
		
//		addKey(jsoStr);
		
		try {
			FileInputStream fis = new FileInputStream("d:/abc2.txt");// 字节流
			InputStreamReader isr = new InputStreamReader(fis);// 字符流
			BufferedReader br = new BufferedReader(isr);// 缓冲流
			StringBuffer buf = new StringBuffer("");
			String str = null;
			while ((str = br.readLine()) != null) {
				buf.append(str);
			}
			final String jsonStr = "{\"item\":" + buf.toString() + "}";
			keySet = ConvertUtils.addKey(jsonStr);
			System.out.println(keySet);
			Object [] arr =  keySet.toArray();
			for (int i = 0; i < arr.length; i++) {
				System.out.println((String)arr[i]);
				
			}
			System.out.println(arr.length);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void test01(){
		String json = "{'qq': '', 'Company_Phone': '', 'House_Phone': '\u903e\u671f\u5929\u6570\uff1a', 'Borrow_Period': '6\u4e2a\u6708', 'name': '\u5f20\u94f6\u7403', 'Info_url': '', 'Info_Source': '\u62cd\u62cd\u8d37', 'House_address': '', 'cardno': '33032519761109****', 'Money': '60000.00', 'ReturnMoney': '', 'Info_Updated': '', 'phone': '13806800***', 'Company_name': '', 'Time': '2013-10-16', 'NotReturn': '19407.50', 'ID_address': '', 'Company_address': '', 'Email': '1838599723@qq.com'}";
		System.out.println(json);
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			String name = jsonObject.getString("name");
			String address = jsonObject.getString("House_Phone");
			System.out.println("name is:" + name);
			System.out.println("address is:" + address);
			
//			String key = jsonObject.getString(key)
			boolean b = jsonObject.has("qqq");
			System.out.println(b);
			
			Map<String, Object> map = (Map) jsonObject; //转map
			System.out.println(map.get("House_Phone"));
			Set set = map.entrySet();
			Set set2 = map.keySet();
			
			System.out.println(set);
			System.out.println(set2);
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				System.out.println(jsonObject.get(it.next()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//格式：  String json = "{\"item\":[{'qq': '','Email': '1838599723@qq.com'}]}";
	static void addKey(String json){
		
		Object[] objects = getStringArray4Json(json);
		for (Object object : objects) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			Map<String, Object> map = (Map) jsonObject;
			Set<String> set = map.keySet();
			for (String obj : set) {  
				if(!keySet.contains(obj)){
					keySet.add((String)obj);
				}
			}  
		}
		
	}
	
	
	static void test02(){
		
		  final String jsoStr = "{\"item\":[{'qq': '', 'Company_Phone': '', 'House_Phone': '', 'Borrow_Period': '6\u4e2a\u6708', 'name': '\u5f20\u94f6\u7403', 'Info_url': '', 'Info_Source': '\u62cd\u62cd\u8d37', 'House_address': '', 'cardno': '33032519761109****', 'Money': '60000.00', 'ReturnMoney': '', 'Info_Updated': '', 'phone': '13806800***', 'Company_name': '', 'Time': '2013-10-16', 'NotReturn': '19407.50', 'ID_address': '', 'Company_address': '', 'Email': '1838599723@qq.com'}," +
		  		"{'qq': '', 'Company_Phone': '', 'House_Phone': '', 'Borrow_Period': '8\u4e2a\u6708', 'name': '\u66f9\u6210\u5747', 'Info_url': '', 'Info_Source': '\u62cd\u62cd\u8d37', 'House_address': '', 'cardno': '41088119720221****', 'Money': '3300.00', 'ReturnMoney': '1781.64', 'Info_Updated': '', 'phone': '18639192***', 'Company_name': '', 'Time': '2013-06-17', 'NotReturn': '838.18', 'ID_address': '', 'Company_address': '', 'Email': '1404816232@qq.com'}]}";
		// 数组
			Object[] objects = getStringArray4Json(jsoStr);
			for (int i = 0; i < objects.length; i++) {
				System.out.println(" 数组 "+objects[i]);
			}
		 
	}
	
	static void test03(){
		
		try {
			FileInputStream fis = new FileInputStream("d:/abc2.txt");// 字节流
			InputStreamReader isr = new InputStreamReader(fis);// 字符流
			BufferedReader br = new BufferedReader(isr);// 缓冲流
			StringBuffer buf=new StringBuffer("");
			String str = null;
			while ((str = br.readLine()) != null) {
				buf.append(str);
			}
			  final String jsonStr = "{\"item\":"+buf.toString()+"}";
			  System.out.println("jsonStr = "+jsonStr);
			// 数组
				Object[] objects = getStringArray4Json(jsonStr);
				for (int i = 0; i < objects.length; i++) {
					System.out.println(" 数组 "+i+" : "+objects[i]);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	// 从json数组中解析出java字符串数组
	public static Object[] getStringArray4Json(String jsonString) {
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		JSONArray jsonarr = jsonObj.getJSONArray("item");
		return (Object[]) jsonarr.toArray();
	}
}
