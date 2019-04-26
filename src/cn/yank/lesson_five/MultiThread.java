
/**   
 * @Title: MultiThread.java 
 * @Package: cn.yank.lesson_five 
 * @Description: TODO
 * @author Yank  
 * @date Apr 20, 2019 2:09:06 PM 
 * @version 0.1 
 */


package cn.yank.lesson_five;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

/** 
 * @Description 
 * @author Yank
 * @date Apr 20, 2019 2:09:06 PM 
 * @version V0.1
 */

public class MultiThread extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text;
	private JTextField resultText;
	private JLabel label_1;
	public ComputationThread thread;
	public UIThread uiThread;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiThread frame = new MultiThread();
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
	public MultiThread() {
		thread = new ComputationThread();
		uiThread = new UIThread(thread);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BA1\u7B97\u8FDB\u7A0B");
		lblNewLabel.setBounds(26, 77, 54, 15);
		contentPane.add(lblNewLabel);
		
		text = new JTextField();
		text.setBounds(84, 67, 303, 35);
		contentPane.add(text);
		text.setColumns(10);
		
		JLabel label = new JLabel("\u8BA1\u7B97\u7ED3\u679C");
		label.setBounds(26, 134, 54, 15);
		contentPane.add(label);
		
		resultText = new JTextField();
		resultText.setColumns(10);
		resultText.setBounds(84, 112, 303, 35);
		contentPane.add(resultText);
		
		label_1 = new JLabel("\u591A\u7EBF\u7A0B\u8BA1\u7B97\u7A0B\u5E8F");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(166, 27, 113, 15);
		contentPane.add(label_1);
		
		JButton button = new JButton("\u5F00\u59CB");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				thread.start();
				new Thread(uiThread).start();
			}
		});
		button.setBounds(186, 228, 93, 23);
		contentPane.add(button);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(84, 169, 303, 35);
		contentPane.add(progressBar);
		
		JLabel label_2 = new JLabel("\u8BA1\u7B97\u8FDB\u5EA6");
		label_2.setBounds(26, 178, 54, 15);
		contentPane.add(label_2);
	}
	
	class ComputationThread extends Thread{
		
		public double result = 1;
		public int sleep = 0;
		public int n = 0;
		/**
		 * Description  
		 * @see java.lang.Thread#run() 
		 */ 
			
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				sleep = (int)(Math.random() * 1000);
				result = (n+1) * n / 2.0 ;
				try {
					sleep(sleep);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				n++;
			}
		}
	}
	
	class UIThread implements Runnable{

		public ComputationThread thread;
		int progress = 0;
		/**
		 * Description  
		 * @see java.lang.Runnable#run() 
		 */ 
		/**
		 * Creates a new instance of MultiThread.UIThread.
		 * Description 
		 */

		public UIThread(ComputationThread thread) {
			// TODO Auto-generated constructor stub
			this.thread = thread;
		}
			
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(true) {
				String s = "1";
				for(int i =2 ;i<=thread.n;i++) {
					s+="+"+i;
				}
				text.setText(s);
				resultText.setText(String.valueOf(thread.result));
				System.out.println(s);
				System.out.println(thread.result);
				progressBar.setValue(progress);
				progress++;
				if (progress>=20) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
