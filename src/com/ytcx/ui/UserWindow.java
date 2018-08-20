package com.ytcx.ui;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFrame.*;
import javax.swing.table.DefaultTableModel;

import com.ytcx.server.Operation;
import com.ytcx.util.Tool;
public class UserWindow extends JFrame{
	private final String str=null;
	private  String fileStr=null;
	Operation oper=null;
	

	private JTable table=new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	Vector colName=new Vector();
	private JComboBox typeCom=new JComboBox();
	private JTextField companyTxt=new JTextField();
	private JTextField jobTxt=new JTextField();
	private JTextField remarkTxt=new JTextField();
	private String id;
	private String time;
	public UserWindow(String str,String fileStr){
		
		this.fileStr=fileStr;
		oper=new Operation(fileStr);
		setTitle("欢迎用户"+str+"使用记事本");
		this.setIconImage(new ImageIcon("note.png").getImage());
		setBounds(50,50,900,600);
		init();
		setVisible(true);
	}
	void init(){
		JScrollPane sp=new JScrollPane(setTable());
		final JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,sp,setInfo());
		jsp.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				jsp.setDividerLocation(0.5);
			}
		});
		this.add(jsp);
		//showUserImage=new ImageIcon(this.getClass().getResource("notes.png"));
		
		
	}

	public JTable setTable(){
		
		colName.add("编号");
		colName.add("招聘类型");
		colName.add("公司名称");
		colName.add("应聘岗位");
		colName.add("日期");
		colName.add("备注");
		Vector data=oper.select();
		dtm.setDataVector(data, colName);
		table.setModel(dtm);
		
		table.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				id=dtm.getValueAt(row, 0).toString();
				//测试语句System.out.println(id);
				String type=dtm.getValueAt(row, 1).toString();
				String company=dtm.getValueAt(row, 2).toString();
				String job=dtm.getValueAt(row, 3).toString();
				String remark=dtm.getValueAt(row, 5).toString();
				typeCom.setSelectedItem(type);
				companyTxt.setText(company);
				jobTxt.setText(job);
				remarkTxt.setText(remark);
				time=dtm.getValueAt(row, 4).toString();
			}
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		return table;
	}
	/*public static void main(String[] args) {
		new UserWindow();
	}*/
	public JPanel setInfo(){
		JPanel jp=new JPanel();
		jp.setLayout(null);
		
		JLabel label1=new JLabel("招聘类型");
		label1.setBounds(20, 50, 50, 30);
		jp.add(label1);
		typeCom.addItem("提前批");
		typeCom.addItem("校招");
		typeCom.addItem("社招");
		typeCom.setBounds(80,50,60,30);
		
		JLabel label2=new JLabel("公司名称");
		label2.setBounds(200,50,50,30);
		jp.add(label2);
		companyTxt.setBounds(250,50,100,30);
		
		JLabel label3=new JLabel("应聘岗位");
		label3.setBounds(360,50,50,30);
		jp.add(label3);
		jobTxt.setBounds(420,50,100,30);
		
		JLabel label4=new JLabel("备注");
		label4.setBounds(530,50,50,30);
		jp.add(label4);
		remarkTxt.setBounds(580,50,100,30);
		
		JButton button1=new JButton("修改");
		button1.setBounds(150, 100, 100, 50);
		button1.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				String type=typeCom.getSelectedItem().toString();
				String company=companyTxt.getText();
				String job=jobTxt.getText();
				String remark=remarkTxt.getText();
				Tool tool=new Tool();
				String time=tool.getTime();
				oper.update(id, type, company, job, time, remark);
				/*
				 * 表格刷新
				 */
				Vector dataSel=oper.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		JButton button2=new JButton("增加");
		button2.setBounds(350, 100, 100, 50);
		button2.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				String type=typeCom.getSelectedItem().toString();
				String company=companyTxt.getText();
				String job =jobTxt.getText();
				String remark=remarkTxt.getText();
				Tool tool=new Tool();
				int id=tool.getNewId();
				String time=tool.getTime();
				String data=id+" "+type+" "+company+" "+job+" "+time+" "+remark+"\n";
				oper.add(data);
				/*
				 * 表格刷新
				 */
				Vector dataSel=oper.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JButton button3=new JButton("删除");
		button3.setBounds(550, 100, 100, 50);
		button3.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				oper.delete(id);
				Vector dataSel=oper.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		jp.add(remarkTxt);
		jp.add(jobTxt);
		jp.add(companyTxt);
		jp.add(typeCom);
		jp.add(button1);
		jp.add(button2);
		jp.add(button3);
		return jp;
	}
}













