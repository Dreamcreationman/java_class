package cn.yank.lesson_test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class severApp {
	private JFrame frmSever;
	private JTextField textField;
	static TextArea textArea;
	static PrintStream ps;
	static Socket sever;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					severApp window = new severApp();
					window.frmSever.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ServerSocket ss;
	     try {
			ss =new ServerSocket(1215);
			sever= ss.accept();//阻塞的accept方法
			//当一个客户端连接上，才会返回Socket对象
			textArea.append("目前有用户连接到服务器上!"+'\n');
			InputStream is =sever.getInputStream();
			InputStreamReader isr =new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			//Thread.currentThread().sleep(500);
			
			while(true)
			{
				String message = br.readLine();
				textArea.append("Client:"+message+'\n');
				//message =br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public severApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSever = new JFrame();
		frmSever.setTitle("Sever");
		frmSever.setBounds(100, 100, 518, 359);
		frmSever.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutputStream os;
				try {
					os = sever.getOutputStream();
					ps =new PrintStream(os);
					String message = textField.getText();
					ps.println(message);
					textField.setText("");//清空
					textArea.append("Sever:"+message+'\n');
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmSever.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		frmSever.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textArea = new TextArea();
		scrollPane.setViewportView(textArea);
	}

}
