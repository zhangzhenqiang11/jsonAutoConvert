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

public class Convert6_拍拍贷黑名单 {

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
		String[] arr = new String[11];
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			int i=0;
			arr[i++]=jsonObject.getString("Borrow_Time");
			arr[i++]=jsonObject.getString("name");
			arr[i++]=jsonObject.getString("NO");
			arr[i++]=jsonObject.getString("cardno");
			arr[i++]=jsonObject.getString("Borrow_TimePeriod");
			arr[i++]=jsonObject.getString("Detail");
			arr[i++]=jsonObject.getString("delay");
			arr[i++]=jsonObject.getString("phone");
			arr[i++]=jsonObject.getString("NotReutrn");
			arr[i++]=jsonObject.getString("Money");
			arr[i++]=jsonObject.getString("Nickname");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	public static void createExcel() throws WriteException,IOException{
		File file = new File("D:\\\\6_拍拍贷黑名单_2016.xlsx");
		OutputStream os = new FileOutputStream(file);
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label Borrow_Time = new Label(0,0,"Borrow_Time");
        sheet.addCell(Borrow_Time);
        Label name = new Label(1,0,"name");
        sheet.addCell(name);
        Label NO = new Label(2,0,"NO");
        sheet.addCell(NO);
        Label cardno = new Label(3,0,"cardno");
        sheet.addCell(cardno);
        Label Borrow_TimePeriod = new Label(4,0,"Borrow_TimePeriod");
        sheet.addCell(Borrow_TimePeriod);
        Label Detail = new Label(5,0,"Detail");
        sheet.addCell(Detail);
        Label delay = new Label(6,0,"delay");
        sheet.addCell(delay);
        Label phone = new Label(7,0,"phone");
        sheet.addCell(phone);
        Label NotReutrn = new Label(8,0,"NotReutrn");
        sheet.addCell(NotReutrn);
        Label Money = new Label(9,0,"Money");
        sheet.addCell(Money);
        Label Nickname = new Label(10,0,"Nickname");
        sheet.addCell(Nickname);
        
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
		
			for(int j=0;j<=10;j++){
				Label cell = new Label(j,i,arr[j]);
		        sheet.addCell(cell);
			}
	}
}
