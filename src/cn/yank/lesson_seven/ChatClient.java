
/**   
 * @Title: ChatClient.java 
 * @Package: cn.yank.lesson_seven 
 * @Description: TODO
 * @author Yank  
 * @date Apr 29, 2019 1:18:37 PM 
 * @version 0.1 
 */


package cn.yank.lesson_seven;

import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

/** 
 * @Description 
 * @author Yank
 * @date Apr 29, 2019 1:18:37 PM 
 * @version V0.1
 */

public class ChatClient extends JFrame {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -3786331689239344254L;
	private JPanel contentPane;
	private JTextField serverip;
	private JButton connect;
	private Socket socket;
	private JTextArea contentArea;
	private DataOutputStream dataOutputStream;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient frame = new ChatClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatClient() {
		setTitle("ChatClient");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServerIp = new JLabel("Server IP : ");
		lblServerIp.setBounds(10, 13, 72, 15);
		contentPane.add(lblServerIp);
		
		serverip = new JTextField();
		serverip.setHorizontalAlignment(SwingConstants.CENTER);
		serverip.setText("127.0.0.1");
		serverip.setBounds(83, 10, 97, 21);
		contentPane.add(serverip);
		serverip.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 503, 622);
		contentPane.add(scrollPane);
		
		contentArea = new JTextArea();
		scrollPane.setViewportView(contentArea);
		
		JLabel lblServerPort = new JLabel("Server Port : ");
		lblServerPort.setBounds(219, 13, 97, 15);
		contentPane.add(lblServerPort);
		
		JTextField port = new JTextField();
		port.setText("8888");
		port.setHorizontalAlignment(SwingConstants.CENTER);
		port.setColumns(10);
		port.setBounds(302, 10, 52, 21);
		contentPane.add(port);
		
		connect = new JButton("Connect");
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (serverip.getText().toString().trim().length() !=0 && port.getText().toString().trim().length() != 0) {
					try {
						socket = new Socket(serverip.getText().toString().trim(), Integer.parseInt(port.getText().toString().trim()));
						if (socket.isConnected()) {
							contentArea.setText("Client is connected to server!\n");
							ClinetReaderThread clinetReaderThread = new ClinetReaderThread(socket);
							clinetReaderThread.start();
						}
					} catch (NumberFormatException | IOException e1) {
						JOptionPane.showInternalMessageDialog(getContentPane(), "Please specify a correct ip or port!");
					}
				}
			}
		});
		connect.setBounds(420, 9, 93, 23);
		contentPane.add(connect);
		
		JTextArea messageBox = new JTextArea();
		messageBox.setBounds(10, 673, 400, 38);
		contentPane.add(messageBox);
		
		JButton send = new JButton("SEND");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (messageBox.getText().toString().length() != 0) {
					if (socket != null && socket.isConnected()) {
						try {
							dataOutputStream = new DataOutputStream(socket.getOutputStream());
							dataOutputStream.writeUTF(messageBox.getText().toString());
							dataOutputStream.flush();
							contentArea.setText(contentArea.getText().toString()+"I:  	"+messageBox.getText().toString()+"\n");
							messageBox.setText(null);
						} catch (IOException e1) {
							contentArea.setText(contentArea.getText().toString()+"An Error occurs! \n");
						}
						
					}else {
						JOptionPane.showInternalMessageDialog(getContentPane(), "Socket disconnected! Please reconnnect!");
					}
				}else {
					JOptionPane.showInternalMessageDialog(getContentPane(), "Please specify some data in message box!");
				}
			}
		});
		send.setBounds(420, 673, 93, 38);
		contentPane.add(send);
	}
	
	class ClinetReaderThread extends Thread{
		
		private Socket socket;
		
		/** 
		 * @Description 
		 * @author Yank
		 * @date Apr 29, 2019 1:56:55 PM   
		 */

		public ClinetReaderThread(Socket socket) {
			this.socket = socket;
		}
		
		/**
		 * Description  
		 * @see java.lang.Thread#run() 
		 */ 
			
		@Override
		public void run() {
			try {
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				while (true) {
					String message = dataInputStream.readUTF().toString();
					contentArea.append(message +"\n");
				}
			} catch (IOException e) {
				contentArea.setText("Server error! Please reconnect!\n");
			}
		}
	}
	
}
