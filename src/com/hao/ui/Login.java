package com.hao.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends JFrame {

	private String username = null;
	private String fileNames = null;
	private AdaptivePanel panel;
	// �˺�
	private JLabel user_account = new JLabel("�˺ţ�");
	private JTextField acc_text = new JTextField();
	// ����
	private JLabel user_password = new JLabel("���룺");
	private JPasswordField pw_text = new JPasswordField();
	// ��ť
	private JButton but_register = new JButton("ע��");
	private JButton but_login = new JButton("��½");

	public static  int pd = 0;
	private String acc_str, pw_str;
	Font font_acc = new Font("����", 1, 30);
	Font font_pw = new Font("����", 1, 30);
	Font font_but = new Font("����", 2, 30);
//	private Container contentPane = this.getContentPane();
	public String getUserName(){
    	return username;
    }
	
	public Login() {
		this.setSize(400, 267);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setIconImage(new ImageIcon("note.png").getImage());// ͼ��ͼ��
		this.setTitle("�û���½");
		this.setResizable(false);// �̶������С
		// ע��
		but_register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				pd = 1;
				acc_str = acc_text.getText();
				pw_str = pw_text.getText();
				String filename = "user.dat";
				try {
					// д���û���Ϣ�ļ� IO������
					FileWriter fwrite = new FileWriter(filename, true);
					BufferedWriter bw = new BufferedWriter(fwrite);
					bw.write("ID:" + acc_str + "&&" + "password" + pw_str);
					bw.newLine();
					bw.close();
					fwrite.close();

				} catch (Exception e2) {
				}
				acc_text.setText("");
				pw_text.setText("");
				JOptionPane.showMessageDialog(null, "�˻�" + acc_str + "ע��ɹ�! ");
			}
		});
		// ��½
		but_login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(pd==1||pd==0) {
				File filename = new File("user.dat");
				try {
					FileReader fread = new FileReader(filename);
					BufferedReader br = new BufferedReader(fread);
					String s = null;
					boolean judge = false;
					// �ж��û��������Ƿ����
					while ((s = br.readLine()) != null) {
						StringBuilder sb = new StringBuilder();
						sb.append("ID:").append(acc_text.getText()).append("&&").append("password:")
								.append(pw_text.getText());
						String log_str = sb.toString();
						// ����
						if (s.equals(log_str)) {
							username = acc_text.getText();
							fileNames = acc_text.getText() + pw_text.getText() + ".txt";
							JOptionPane.showMessageDialog(null, "�˻�" + username + "��½�ɹ���");
							acc_text.setText("");
							pw_text.setText("");
							judge = true;

							break;
						}
					}
					fread.close();
					br.close();
					// �ж�ʱ���½�ɹ�
					if (judge == false) {
						JOptionPane.showMessageDialog(null, "��½ʧ�ܣ�����˺������ע����½��", null, JOptionPane.ERROR_MESSAGE,
								null);
						acc_text.setText("");
						pw_text.setText("");
					}
					// ��½�ɹ������ݴ�����ҳ��
					if (judge == true) {
						new UserPanel(username, fileNames);
						dispose();
						return;
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ϵͳ����" + e2, null, JOptionPane.ERROR_MESSAGE, null);
				}
			}
		}});
		init();

		panel = new AdaptivePanel();
		// �����е������ӵ�panel�����
		panel.add(user_account);
		panel.add(user_password);
		panel.add(acc_text);
		panel.add(pw_text);
		panel.add(but_register);
		panel.add(but_login);
		panel.setLayout(null);
	}

	private void init() {
		user_account.setSize(200, 100);
		user_account.setLocation(50, 20);
		user_account.setFont(font_acc);
		user_password.setSize(200, 100);
		user_password.setLocation(50, 80);
		user_password.setFont(font_pw);
		acc_text.setSize(190, 30);
		acc_text.setLocation(160, 55);
		pw_text.setSize(190, 30);
		pw_text.setLocation(160, 115);
		but_register.setSize(120, 40);
		but_register.setLocation(60, 180);
		but_login.setSize(120, 40);
		but_login.setLocation(230, 180);
		but_register.setFont(font_but);
		but_login.setFont(font_but);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		new Login().setVisible(true);
	}

}
