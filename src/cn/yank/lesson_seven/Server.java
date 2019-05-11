
/**   
 * @Title: Server.java 
 * @Package: cn.yank.lesson_seven 
 * @Description: TODO
 * @author Yank  
 * @date May 9, 2019 9:35:44 PM 
 * @version 0.1 
 */


package cn.yank.lesson_seven;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 
 * @Description 
 * @author Yank
 * @date May 9, 2019 9:35:44 PM 
 * @version V0.1
 */

public class Server {
	
	private static final int PORT = 8888;
	private ServerSocket serverSocket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	private Map<String, String> clientName;
	private List<Socket> clients;
	
	public static void main(String[] args) {
		new Server().start();
	}
	
	
	/**
	 * Creates a new instance of Server.
	 * Description 
	 */

	public Server() {
		// TODO Auto-generated constructor stub
		clientName = new HashMap<String, String>();
		clients = new ArrayList<Socket>();
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server starts at "+ PORT);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	private void start() {
		System.out.println("Sever starts listening......");
		while (true) {
			try {
				Socket client = serverSocket.accept();
				dataInputStream = new DataInputStream(client.getInputStream());
				dataOutputStream = new DataOutputStream(client.getOutputStream());
				dataOutputStream.writeUTF("请输入用户名：");
				dataOutputStream.flush();
				String username = dataInputStream.readUTF().toString();
				synchronized (clients) {
					clientName.put(client.getInetAddress()+":"+client.getPort(), username);
					clients.add(client);
				}
				new ServerThread(client).start();
				dataOutputStream.writeUTF(username + ", 您好！您已成功登录。当前在线人数：" + clients.size());
				dataOutputStream.flush();
				System.out.println(username + "已成功登录。当前在线人数：" + clients.size());		
				sendBoardcastToAll("Server:  	"+username + "已成功登录。当前在线人数：" + clients.size(), client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Port is in use, please stop it first!");
			}
		}
			
	}
	
	/**
	 * 
	 * @Description send message to all 
	 * @author Yank
	 * @date May 9, 2019 10:42:35 PM 
	 * @param message
	 * @param excludeClient
	 * @throws IOException
	 */
	private synchronized void sendBoardcastToAll(String message, Socket excludeClient) throws IOException {
		for (Iterator<Socket> iterator = clients.iterator(); iterator.hasNext();) {
			Socket socket = (Socket) iterator.next();
			String ipString = socket.getInetAddress()+":"+socket.getPort();
			if (!ipString.equals(excludeClient.getInetAddress()+":"+excludeClient.getPort())) {
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
			}
		}
	}
	
	class ServerThread extends Thread{
		
		private Socket client;
		/**
		 * Creates a new instance of Server.ServerThread.
		 * Description 
		 */

		public ServerThread(Socket client) {
			this.client = client;
		}
		
		/**
		 * Description  
		 * @see java.lang.Thread#run() 
		 */ 
			
		@Override
		public void run() {
			DataInputStream dataInputStream;
			try {
				dataInputStream = new DataInputStream(client.getInputStream());
				while (true) {
					String message = dataInputStream.readUTF();
					sendBoardcastToAll(clientName.get(client.getInetAddress()+":"+client.getPort())+":  	"+message, client);
				}
			} catch (IOException e) {
				
			} finally {
				clients.remove(client);
				String ip =client.getInetAddress()+":"+client.getPort();
				try {
					System.out.println(clientName.get(ip)+" 下线了……当前在线人数：" + clients.size());
					sendBoardcastToAll(clientName.get(ip)+" 下线了……当前在线人数：" + clients.size(), client);
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clientName.remove(ip);
				
			}
			
		}
	}
	
}
