package com.bill99.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestIO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("d:/abc.txt");// 字节流
			InputStreamReader isr = new InputStreamReader(fis);// 字符流
			BufferedReader br = new BufferedReader(isr);// 缓冲流
			StringBuffer buf=new StringBuffer("");
			String str = null;
			while ((str = br.readLine()) != null) {
				buf.append(str);
			}
			System.out.println(buf.toString());
			br.close();
			isr.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
