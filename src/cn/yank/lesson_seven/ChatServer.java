
/**   
 * @Title: ChatServer.java 
 * @Package: cn.yank.lesson_seven 
 * @Description: TODO
 * @author Yank  
 * @date Apr 29, 2019 12:37:28 PM 
 * @version 0.1 
 */


package cn.yank.lesson_seven;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** 
 * @Description 
 * @author Yank
 * @date Apr 29, 2019 12:37:28 PM 
 * @version V0.1
 */

public class ChatServer extends JFrame {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 3083883853233669228L;
	private JPanel contentPane;
	private ServerSocket serverSocket;
	private JTextArea contentArea;
	private JTextArea messageBox;
	private List<Socket> sockets;
	private DataOutputStream dataOutputStream;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServer frame = new ChatServer();
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
	public ChatServer() {
		setTitle("ChatServer");
		sockets = new ArrayList<Socket>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		messageBox = new JTextArea();
		messageBox.setBounds(20, 678, 390, 33);
		contentPane.add(messageBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 503, 658);
		contentPane.add(scrollPane);
		
		contentArea = new JTextArea();
		scrollPane.setViewportView(contentArea);
		contentArea.setFont(new Font("PingFang SC ExtraLight", Font.PLAIN, 13));
		
		JButton send = new JButton("SEND");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (messageBox.getText().toString().length() != 0 ) {
					for(Socket socket: sockets) {
						if (socket != null && socket.isConnected()) {
							try {
								dataOutputStream = new DataOutputStream(socket.getOutputStream());
								dataOutputStream.writeUTF(messageBox.getText().toString());
								dataOutputStream.flush();
								
							} catch (IOException e1) {
								contentArea.setText(contentArea.getText().toString()+"An Error occurs! \n");
								e1.printStackTrace();
							}
							
						}else {
							JOptionPane.showInternalMessageDialog(getContentPane(), "Socket disconnected! Please reconnnect!");
						}
					}
					contentArea.setText(contentArea.getText().toString()+"I say: "+messageBox.getText().toString()+"\n");
					messageBox.setText(null);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "Please input message you want to send!");
				}
			}
		});
		send.setBounds(420, 676, 93, 35);
		contentPane.add(send);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(8888);
					contentArea.setText("Server is listening......\n");
				} catch (IOException e) {
					JOptionPane.showInternalMessageDialog(getContentPane(), "Port is bind! Systeml will exit");
					System.exit(0);
					e.printStackTrace();
				}
				
				
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						contentArea.setText(contentArea.getText().toString()+"A Client is conneted!\n");
						ClientThread clientThread = new ClientThread(socket);
						sockets.add(socket);
						clientThread.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		}).start();
	}
	
	class ClientThread extends Thread{
		
		private Socket socket;
		/**
		 * Creates a new instance of ChatServer.ClientThread.
		 * Description 
		 */

		public ClientThread(Socket socket) {
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
					String message = dataInputStream.readUTF();
					contentArea.setText(contentArea.getText().toString()+"Client "+socket.getInetAddress()+":"+socket.getPort()+" says that: "+ message +"\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
