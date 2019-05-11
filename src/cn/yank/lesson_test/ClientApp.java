package cn.yank.lesson_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientApp {

	private JFrame frmClient;
	static TextArea textArea;
	static PrintStream ps;
	static Socket client; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApp window = new ClientApp();
					window.frmClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			client = new Socket("127.0.0.1",1215);
			InputStream is =client.getInputStream();
			InputStreamReader isr =new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			Thread.currentThread().sleep(500);
			textArea.append("已成功连接到服务器端!"+'\n');		
			String message = br.readLine();
			while(true)
			{
				textArea.append("Server:"+message+'\n');
				message =br.readLine();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ClientApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.setTitle("Client");
		frmClient.setBounds(100, 100, 608, 398);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutputStream os;
				try {
					os = client.getOutputStream();
					ps =new PrintStream(os);
					String message = textField.getText();
					ps.println(message);
					textField.setText("");//清空
					textArea.append("Client:"+message+'\n');
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmClient.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		frmClient.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textArea = new TextArea();
		scrollPane.setViewportView(textArea);
	}

}
