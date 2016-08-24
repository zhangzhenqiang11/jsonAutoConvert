package com.bill99.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;

import com.bill99.utils.ConvertUtils;

public class Convert12_蜜蜂数据__失信人曝光 {

	static Object[] objects = null;
	
	public static void main(String[] args) {
		convertAll();
	}
	
	static void convertAll() {

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
			System.out.println("jsonStr = " + jsonStr);
			// 数组
			objects = ConvertUtils.getStringArray4Json(jsonStr);
			
			createExcel();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static String[] convert1(Object obj){
		String json = obj.toString();
		System.out.println("obj.toString() :  "+json);
		String[] arr = new String[6];
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			int i=0;
			arr[i++]=jsonObject.getString("name");
			arr[i++]=jsonObject.getString("逾期天数");
			arr[i++]=jsonObject.getString("cardno");
			arr[i++]=jsonObject.getString("借款金额");
			arr[i++]=jsonObject.getString("借款天数");
			arr[i++]=jsonObject.getString("数据来源");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	public static void createExcel() throws WriteException,IOException{
		File file = new File("D:\\\\12_蜜蜂数据——失信人曝光_result_mifeng1.xlsx");
		OutputStream os = new FileOutputStream(file);
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label name = new Label(0,0,"name");
        sheet.addCell(name);
        Label zone = new Label(1,0,"逾期天数");
        sheet.addCell(zone);
        Label Time = new Label(2,0,"cardno");
        sheet.addCell(Time);
        Label Money2 = new Label(3,0,"借款金额");
        sheet.addCell(Money2);
        Label phone = new Label(4,0,"借款天数");
        sheet.addCell(phone);
        Label Money1 = new Label(5,0,"数据来源");
        sheet.addCell(Money1);
        
        for (int i = 0; i < objects.length; i++) {
			System.out.println(" 数组 " + i + " : " + objects[i]);
			String[] arr = convert1(objects[i]);
			insertSheet(sheet, arr, i+1);
		}
        
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

	static void insertSheet(WritableSheet sheet,String[] arr,int i) throws WriteException,
			RowsExceededException {
		
			for(int j=0;j<=5;j++){
				Label cell = new Label(j,i,arr[j]);
		        sheet.addCell(cell);
			}
	}
}
