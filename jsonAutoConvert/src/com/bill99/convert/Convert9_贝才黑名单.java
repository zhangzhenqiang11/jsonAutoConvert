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

public class Convert9_贝才黑名单 {

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
		String[] arr = new String[10];
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			
			if(jsonObject.has("性别：")){
				arr[0]=jsonObject.getString("性别：");
			}
			if(jsonObject.has("name")){
				arr[1]=jsonObject.getString("name");
			}
			if(jsonObject.has("cardno")){
				arr[2]=jsonObject.getString("cardno");
			}
			if(jsonObject.has("家庭住址：")){
				arr[3]=jsonObject.getString("家庭住址：");
			}
			if(jsonObject.has("微信：")){
				arr[4]=jsonObject.getString("微信：");
			}
			if(jsonObject.has("学校名称：")){
				arr[5]=jsonObject.getString("学校名称：");
			}
			if(jsonObject.has("支付宝账号：")){
				arr[6]=jsonObject.getString("支付宝账号：");
			}
			if(jsonObject.has("身份证地址：")){
				arr[7]=jsonObject.getString("身份证地址：");
			}
			if(jsonObject.has("单位名称：")){
				arr[8]=jsonObject.getString("单位名称：");
			}
			if(jsonObject.has("QQ：")){
				arr[9]=jsonObject.getString("QQ：");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	public static void createExcel() throws WriteException,IOException{
		File file = new File("D:\\\\9_贝才黑名单.xlsx");
		OutputStream os = new FileOutputStream(file);
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label 性别 = new Label(0,0,"性别");
        sheet.addCell(性别);
        Label name = new Label(1,0,"name");
        sheet.addCell(name);
        Label cardno = new Label(2,0,"cardno");
        sheet.addCell(cardno);
        Label 家庭住址 = new Label(3,0,"家庭住址");
        sheet.addCell(家庭住址);
        Label 微信 = new Label(4,0,"微信");
        sheet.addCell(微信);
        Label 学校名称 = new Label(5,0,"学校名称");
        sheet.addCell(学校名称);
        Label 支付宝账号 = new Label(6,0,"支付宝账号");
        sheet.addCell(支付宝账号);
        Label 身份证地址 = new Label(7,0,"身份证地址");
        sheet.addCell(身份证地址);
        Label 单位名称 = new Label(8,0,"单位名称");
        sheet.addCell(单位名称);
        Label QQ = new Label(9,0,"QQ");
        sheet.addCell(QQ);
        
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
		
			for(int j=0;j<=9;j++){
				Label cell = new Label(j,i,arr[j]);
		        sheet.addCell(cell);
			}
	}
}
