package com.bill99.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConvertUtils {

	/**
	 * 从json数组中解析出java字符串数组 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getStringArray4Json(String jsonString) {
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		JSONArray jsonarr = jsonObj.getJSONArray("item");
		return (Object[]) jsonarr.toArray();
	}

	/**
	 * 将json转换成数组
	 * 
	 * @param filePath
	 *            文件存储路径
	 * @param fromFile
	 *            json数据源
	 */
	public static String convertAll(String filePath, String fromFile) {
		String jsonStr = "";
		try {
			FileInputStream fis = new FileInputStream(fromFile);// 字节流
			InputStreamReader isr = new InputStreamReader(fis);// 字符流
			BufferedReader br = new BufferedReader(isr);// 缓冲流
			StringBuffer buf = new StringBuffer("");
			String str = null;
			while ((str = br.readLine()) != null) {
				buf.append(str);
			}
			jsonStr = "{\"item\":" + buf.toString() + "}";
			System.out.println("jsonStr = " + jsonStr);

			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	static Set<String> keySet = new HashSet<String>();

	/**
	 * 获取json字符串所有的key，并去重
	 * 
	 * @param json
	 *        格式： "{\"item\":[{'qq': '','Email': '1838599723@qq.com'}]}"
	 */
	public static Set<String> addKey(String json) {

		Object[] objects = getStringArray4Json(json);
		for (Object object : objects) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			Map<String, Object> map = (Map) jsonObject;
			Set<String> set = map.keySet();
			for (String obj : set) {
				if (!keySet.contains(obj)) {
					keySet.add((String) obj);
				}
			}
		}
		return keySet;
	}

}
