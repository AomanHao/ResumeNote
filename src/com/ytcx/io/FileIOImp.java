package com.ytcx.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileIOImp extends FileIO{
	private final String FILEPATH=".\\record\\";
	
//	private final String FILENAME="personalRecord.txt";
	private static String filename;
	File  file=new File(FILEPATH+filename);
	public void checkFile(String filename) {
		this.filename = filename;
		File fileDir= new File(FILEPATH);
		 file=new File(FILEPATH+filename);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String read() {
		String fileString="";
		String lineString="";
		try {
			//测试语句System.out.println("文件名"+filename);
			File  file=new File(FILEPATH+filename);
			FileReader fr =new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			while((lineString=br.readLine())!=null){
				fileString+=lineString+"\n";
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileString;
	}
	public void write(String data, boolean mode) {
		try {
			//System.out.println("是否进行了覆盖写？");//测试
			File  file=new File(FILEPATH+filename);
			FileWriter fw=new FileWriter(file,mode);
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
