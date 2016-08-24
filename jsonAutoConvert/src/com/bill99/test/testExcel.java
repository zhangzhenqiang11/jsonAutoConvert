package com.bill99.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class testExcel {

	public static void main(String[] args) {
		try {
			File file = new File("D:\\\\aa.xlsx");
			OutputStream os = new FileOutputStream(file);
			createExcel(os);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createExcel2(OutputStream os) throws WriteException,IOException{
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
        
//        
//        Label qinghua = new Label(0,1,"\u5f20\u94f6\u7403");
//        sheet.addCell(qinghua);
//        Label jisuanji = new Label(1,1,"计算机专业");
//        sheet.addCell(jisuanji);
//        Label gao = new Label(2,1,"高");
//        sheet.addCell(gao);
//        
//        Label beida = new Label(0,2,"\u66f9\u6210\u5747");
//        sheet.addCell(beida);
//        Label falv = new Label(1,2,"法律专业");
//        sheet.addCell(falv);
//        Label zhong = new Label(2,2,"中");
//        sheet.addCell(zhong);
        
        insertSheet(sheet);
        
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

	
	public static void createExcel(OutputStream os) throws WriteException,IOException{
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
        
//        
//        Label qinghua = new Label(0,1,"\u5f20\u94f6\u7403");
//        sheet.addCell(qinghua);
//        Label jisuanji = new Label(1,1,"计算机专业");
//        sheet.addCell(jisuanji);
//        Label gao = new Label(2,1,"高");
//        sheet.addCell(gao);
//        
//        Label beida = new Label(0,2,"\u66f9\u6210\u5747");
//        sheet.addCell(beida);
//        Label falv = new Label(1,2,"法律专业");
//        sheet.addCell(falv);
//        Label zhong = new Label(2,2,"中");
//        sheet.addCell(zhong);
        
        insertSheet(sheet);
        
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

	private static void insertSheet(WritableSheet sheet) throws WriteException,
			RowsExceededException {
		
		for(int i=1;i<18;i++){
			for(int j=0;j<=18;j++){
				Label ligong = new Label(j,i,"北京理工大学");
		        sheet.addCell(ligong);
			}
		}
	}
}
