package com.bill99.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JsonUtil {
	// {"product":"pencil","price":12,"item":[{"id":1,"name":"chen"}]}
	private static final String jsoStr = "{\"product\":\"pencil\",\"price\":12,\"item\":[{\"id\":1,\"name\":\"chen\"},{\"id\":1,\"name\":\"chen\"}]}";

	public static void main(String[] args) throws IOException {
		// String
		System.out.println(string4json("key", "20"));

		// obj
//		Product p = (Product) getObject4JsonString(jsoStr, Product.class);
//		System.out.println(p.getProduct() + "," + p.getPrice());

		// map
		Map map = getMap4Json(jsoStr);
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			System.out.println(entry.getKey() + "," + entry.getValue());
		}

		// 数组
		Object[] objects = getStringArray4Json(jsoStr);
		for (int i = 0; i < objects.length; i++) {
			System.out.println(" 数组 "+objects[i]);
		}
	}

	// 将String转换成JSON
	public static String string4json(String key, String value)
			throws JSONException {
		JSONObject object = new JSONObject();
		object.put(key, value);
		return object.toString();
	}

	// 从一个JSON 对象字符格式中得到一个java对象
	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return pojo;
	}

	// 从json 表达式中获取一个map
	public static Map getMap4Json(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator keyIter = jsonObject.keys();
		String key;
		Object value;
		Map valueMap = new HashMap();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}

		return valueMap;
	}

	// 从json对象集合表达式中得到一个java对象列表
	public static List getList4Json(String jsonString, Class pojoClass) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		Object pojoValue;
		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, pojoClass);
			list.add(pojoValue);

		}
		return list;

	}

	// 从json数组中解析出java字符串数组
	public static Object[] getStringArray4Json(String jsonString) {
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		JSONArray jsonarr = jsonObj.getJSONArray("item");
		return (Object[]) jsonarr.toArray();
	}
}
