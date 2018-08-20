package com.ytcx.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.ytcx.io.FileIO;
import com.ytcx.io.FileIOImp;


public class Tool {
	public Vector StringToVec(String data){
		Vector vecBig=new Vector();
		if(data!=""){
			String[] strArray=data.split("\n");
			for(int i=0;i<strArray.length;i++){
				String [] s=strArray[i].split(" ");
				Vector vecSmall=new Vector();
				for(int j=0;j<s.length;j++){
					vecSmall.add(s[j]);
				}
				vecBig.add(vecSmall);
			}
		}
		return vecBig;
	}
	//ÈÕÆÚ
	public String getTime(){
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now);
		
	}

	public int getNewId(){
		int id=1;
		FileIO io=new FileIOImp();
		String data=io.read();
		if(data!=""){
			Vector vecBig=this.StringToVec(data);
			Vector vecSmall=(Vector)vecBig.get(vecBig.size()-1);
			int idLast=Integer.parseInt(vecSmall.get(0).toString());
			id=idLast+1;
		}
		return id;
	}
	
	public String VecToString(Vector vecBig){
		String s="";
		for(int i=0;i<vecBig.size();i++){
			Vector vecSmall=(Vector)vecBig.get(i);
			String line=vecSmall.get(0)+" "+vecSmall.get(1)+" "+vecSmall.get(2)+" "+vecSmall.get(3)+" "+vecSmall.get(4);
			s+=line+"\n";
			
		}
		return s;
	}
	
	

}
