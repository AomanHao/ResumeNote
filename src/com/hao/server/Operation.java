package com.hao.server;

import java.util.Vector;

import com.hao.io.FileIO;
import com.hao.io.FileIOImp;
import com.hao.util.Tool;

public class Operation {
	FileIO io=new FileIOImp();
	Tool tool=new Tool();
	public Operation(String filename){
		io.checkFile(filename);
	}
	
	public Vector select() {
		String data=io.read();
		return tool.StringToVec(data);
	
	}
	public void add(String data){
		io.write(data, true);
	}
	public void delete(String id){
		Vector vecBig=select();
		//System.out.println("ɾ����û�У�");  //����
		for(int i=0;i<vecBig.size();i++){
			Vector vecSmall=(Vector)vecBig.get(i);
			//�������System.out.println("+1");
			if(vecSmall.get(0).toString().equals(id)){
				vecBig.remove(i);
			//	System.out.println("�������Ѿ�ɾ��");  //����
				break;
			}
				
		}
		String data=tool.VecToString(vecBig);
		io.write(data, false);
	}
	public void update(String id,String type,String money,String time,String remark){
		Vector vecBig =select();
		for(int i=0;i<vecBig.size();i++){
			Vector vecSmall=(Vector)vecBig.get(i);
			if(vecSmall.get(0).toString().equals(id)){
				vecSmall.removeAllElements();
				vecSmall.add(id);
				vecSmall.add(type);
				vecSmall.add(money);
				vecSmall.add(time);
				vecSmall.add(remark);
				break;
			}
		}
		String data=tool.VecToString(vecBig);
		io.write(data, false);
	}
	}
