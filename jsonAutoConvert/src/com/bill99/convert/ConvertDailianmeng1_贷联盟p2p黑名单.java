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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConvertDailianmeng1_贷联盟p2p黑名单 {
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
			objects = getStringArray4Json(jsonStr);
			
			createExcel();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static String[] convert1(Object obj){
		String json = obj.toString();
		System.out.println("obj.toString() :  "+json);
		String[] arr = new String[19];
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			String name = jsonObject.getString("name");
			String address = jsonObject.getString("House_Phone");
			System.out.println("name is:" + name);
			System.out.println("address is:" + address);
			int i=0;
			arr[i++]=jsonObject.getString("qq");
			arr[i++]=jsonObject.getString("Company_Phone");
			arr[i++]=jsonObject.getString("House_Phone");
			arr[i++]=jsonObject.getString("Borrow_Period");
			arr[i++]=jsonObject.getString("name");
			arr[i++]=jsonObject.getString("Info_url");
			arr[i++]=jsonObject.getString("Info_Source");
			arr[i++]=jsonObject.getString("House_address");
			arr[i++]=jsonObject.getString("cardno");
			arr[i++]=jsonObject.getString("Money");
			arr[i++]=jsonObject.getString("ReturnMoney");
			arr[i++]=jsonObject.getString("Info_Updated");
			arr[i++]=jsonObject.getString("phone");
			arr[i++]=jsonObject.getString("Company_name");
			arr[i++]=jsonObject.getString("Time");
			arr[i++]=jsonObject.getString("NotReturn");
			arr[i++]=jsonObject.getString("ID_address");
			arr[i++]=jsonObject.getString("Company_address");
			arr[i++]=jsonObject.getString("Email");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	public static void createExcel() throws WriteException,IOException{
		File file = new File("D:\\\\3_网贷信用黑名单.xlsx");
		OutputStream os = new FileOutputStream(file);
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label qq = new Label(0,0,"qq");
        sheet.addCell(qq);
        Label Company_Phone = new Label(1,0,"Company_Phone");
        sheet.addCell(Company_Phone);
        Label House_Phone = new Label(2,0,"House_Phone");
        sheet.addCell(House_Phone);
        Label Borrow_Period = new Label(3,0,"Borrow_Period");
        sheet.addCell(Borrow_Period);
        Label name = new Label(4,0,"name");
        sheet.addCell(name);
        Label Info_url = new Label(5,0,"Info_url");
        sheet.addCell(Info_url);
        Label Info_Source = new Label(6,0,"Info_Source");
        sheet.addCell(Info_Source);
        Label House_address = new Label(7,0,"House_address");
        sheet.addCell(House_address);
        Label cardno = new Label(8,0,"cardno");
        sheet.addCell(cardno);
        Label Money = new Label(9,0,"Money");
        sheet.addCell(Money);
        Label ReturnMoney = new Label(10,0,"ReturnMoney");
        sheet.addCell(ReturnMoney);
        Label Info_Updated = new Label(11,0,"Info_Updated");
        sheet.addCell(Info_Updated);
        Label phone = new Label(12,0,"phone");
        sheet.addCell(phone);
        Label Company_name = new Label(13,0,"Company_name");
        sheet.addCell(Company_name);
        Label Time = new Label(14,0,"Time");
        sheet.addCell(Time);
        Label NotReturn = new Label(15,0,"NotReturn");
        sheet.addCell(NotReturn);
        Label ID_address = new Label(16,0,"ID_address");
        sheet.addCell(ID_address);
        Label Company_address = new Label(17,0,"Company_address");
        sheet.addCell(Company_address);
        Label Email = new Label(18,0,"Email");
        sheet.addCell(Email);
        
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
		
			for(int j=0;j<=17;j++){
				Label cell = new Label(j,i,arr[j]);
		        sheet.addCell(cell);
			}
	}
	// 从json数组中解析出java字符串数组
	public static Object[] getStringArray4Json(String jsonString) {
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		JSONArray jsonarr = jsonObj.getJSONArray("item");
		return (Object[]) jsonarr.toArray();
	}
}
