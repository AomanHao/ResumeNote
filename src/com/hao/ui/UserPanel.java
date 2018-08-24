package com.hao.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.SelectableChannel;
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
import javax.swing.table.DefaultTableModel;

import com.hao.server.Operation;
import com.hao.util.Tool;

public class UserPanel extends JFrame{
	private final String str = null;
	private String filestr = null;
	//�Զ������
	Operation op = null;
	
	private JTable table = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	Vector colName = new Vector();
	private JComboBox typeCom = new JComboBox();
	private JTextField moneyTxt=new JTextField();
	private JTextField remarkTxt=new JTextField();
	private String id;
	private String time;
	
	//��½�ɹ��������û����ʹ洢��Ϣ��txt�ļ�
	public UserPanel(String str, String fileStr) {
		//������̧ͷ
		this.filestr = fileStr;
		Operation oper = new Operation(fileStr);
		setTitle(str+"�û�����ӭʹ��");
		this.setIconImage(new ImageIcon("note.png").getImage());
		setBounds(50,50,900,600);
		init();
		setVisible(true);
	}
	//��ʼ������
	void init() {
		JScrollPane sp = new JScrollPane();
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,sp,setInfo());
		jsp.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				jsp.setDividerLocation(0.5);
			}
		});
		this.add(jsp);
	}
	
	
	public JTable setTable() {
		colName.add("���");
		colName.add("��˾����");
		colName.add("ӦƸ��λ");
		colName.add("��������");
		colName.add("��Ƹ��ַ");
		colName.add("��ע");
		Vector data=op.select();
		dtm.setDataVector(data, colName);
		table.setModel(dtm);
	

		table.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				id=dtm.getValueAt(row, 0).toString();
				//�������System.out.println(id);
				String type=dtm.getValueAt(row, 1).toString();
				String money=dtm.getValueAt(row, 2).toString();
				String remark=dtm.getValueAt(row, 4).toString();
				typeCom.setSelectedItem(type);
				moneyTxt.setText(money);
				remarkTxt.setText(remark);
				time=dtm.getValueAt(row, 3).toString();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	});
		return table;
}
	public JPanel setInfo(){
		JPanel jp=new JPanel();
		jp.setLayout(null);
		
//		JLabel label1=new JLabel("��˾����");
//		label1.setBounds(20, 50, 50, 30);
//		jp.add(label1);
//		typeCom.addItem("����");
//		typeCom.addItem("֧��");
//		typeCom.setBounds(80,50,60,30);
		JLabel label1=new JLabel("��˾����");
		label1.setBounds(200,50,50,30);
		jp.add(label1);
		moneyTxt.setBounds(250,50,100,30);
		
		JLabel label2=new JLabel("ӦƸ��λ");
		label2.setBounds(200,50,50,30);
		jp.add(label2);
		moneyTxt.setBounds(250,50,100,30);
		
		JLabel label3=new JLabel("��ע");
		label3.setBounds(380,50,50,30);
		jp.add(label3);
		remarkTxt.setBounds(420,50,100,30);
		
		JButton button1=new JButton("�޸�");
		button1.setBounds(50, 100, 100, 50);
		button1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String type=typeCom.getSelectedItem().toString();
				String money=moneyTxt.getText();
				String remark=remarkTxt.getText();
				Tool tool=new Tool();
				String time=tool.getTime();
				op.update(id, type, money, time, remark);
				/*
				 * ���ˢ��
				 */
				Vector dataSel=op.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		JButton button2=new JButton("����");
		button2.setBounds(150, 100, 100, 50);
		button2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String type=typeCom.getSelectedItem().toString();
				String money=moneyTxt.getText();
				String remark=remarkTxt.getText();
				Tool tool=new Tool();
				int id=tool.getNewId();
				String time=tool.getTime();
				String data=id+" "+type+" "+money+" "+time+" "+remark+"\n";
				op.add(data);
				/*
				 * ���ˢ��
				 */
				Vector dataSel=op.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JButton button3=new JButton("ɾ��");
		button3.setBounds(250, 100, 100, 50);
		button3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				op.delete(id);
				/*
				 * ���ˢ��
				 */
				
				Vector dataSel=op.select();
				dtm.setDataVector(dataSel, colName);
				table.setModel(dtm);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		jp.add(remarkTxt);
		jp.add(moneyTxt);
		jp.add(typeCom);
		jp.add(button1);
		jp.add(button2);
		jp.add(button3);
		return jp;
	}
}
