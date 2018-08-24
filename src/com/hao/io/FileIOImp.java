package com.hao.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOImp extends FileIO{

	private final String FILEPATH=".\\txt\\";
	private static String filename;
	File file = new File(FILEPATH+filename);
	
	//生成文件夹和信息文件
	public void checkFile(String filename) {
		this.filename = filename;
		File fileDir = new File(FILEPATH);
		 file=new File(FILEPATH+filename);
		 if(!fileDir.exists()) {
			 fileDir.mkdirs();
		 }
		 if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		 
	}

	//写入数据文本
	public void write(String data, boolean mode) {
		try {
			File file = new File(FILEPATH+filename);
			FileWriter fw = new FileWriter(file);
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//读取数据文本
	public String read() {
		String fileString = "";
		String lineString = "";
		
		try {
		File file = new File(FILEPATH+filename);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while((lineString=br.readLine())!=null) {
				fileString+=lineString+"\n";
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileString;
	}

}
