package com.bill99.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;


public class AutoConvert {

	static Object[] objects = null;
	static Set<String> keySet = new HashSet<String>();

	public static void main(String[] args) {
		String fromFile = "d:/abc2.txt";
		String filePath = "D:\\\\数据埋点.xlsx";
		String jsonStr = ConvertUtils.convertAll(filePath, fromFile);
		// 数组
		objects = ConvertUtils.getStringArray4Json(jsonStr);

		try {
			createExcel(jsonStr, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 创建Excel
	 * 
	 * @param jsonStr
	 * @param filePath
	 * @throws WriteException
	 * @throws IOException
	 */
	 static void createExcel(String jsonStr, String filePath)
			throws WriteException, IOException {

		keySet = ConvertUtils.addKey(jsonStr);
		File file = new File(filePath);
		OutputStream os = new FileOutputStream(file);
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容

		Iterator<String> it = keySet.iterator();
		int cellCount = 0;
		while (it.hasNext()) {
			Label keycell = new Label(cellCount++, 0, it.next());
			sheet.addCell(keycell);
		}

		generateSheetRow(sheet);

		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}

	/**
	 * 针对数组中的单个对象，转换成数组
	 * 
	 * @param obj
	 * @return
	 */
	static String[] convert1(Object obj) {
		String json = obj.toString();
		// System.out.println("obj.toString() :  " + json);
		String[] arr = new String[keySet.size()];
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			Object[] arrObj = keySet.toArray();
			for (int i = 0; i < keySet.size(); i++) {
				if (jsonObject.has((String) arrObj[i])) {
					arr[i] = jsonObject.getString((String) arrObj[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	/**
	 * 将json字符数组填充至Excel
	 * @param sheet
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	 static void generateSheetRow(WritableSheet sheet)
			throws WriteException, RowsExceededException {
		for (int i = 0; i < objects.length; i++) {
			System.out.println(" 数组 " + i + " : " + objects[i]);
			String[] arr = convert1(objects[i]);
			insertSheet(sheet, arr, i + 1);
		}
	}

	/**
	 * Excel插入行
	 * 
	 * @param sheet
	 * @param arr
	 * @param i
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	static void insertSheet(WritableSheet sheet, String[] arr, int i)
			throws WriteException, RowsExceededException {
		for (int j = 0; j < keySet.size(); j++) {
			Label cell = new Label(j, i, arr[j]);
			sheet.addCell(cell);
		}
	}
}
