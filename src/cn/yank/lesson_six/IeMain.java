
/**   
 * @Title: IeMain.java 
 * @Package: cn.yank.lesson_six 
 * @Description: TODO
 * @author Yank  
 * @date Apr 29, 2019 2:30:40 PM 
 * @version 0.1 
 */


package cn.yank.lesson_six;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/** 
 * @Description 
 * @author Yank
 * @date Apr 29, 2019 2:30:40 PM 
 * @version V0.1
 */

public class IeMain extends JFrame {

	private JPanel contentPane;
	private JTextField urlInput;
	private JTextArea sourceCode;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IeMain frame = new IeMain();
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
	public IeMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		urlInput = new JTextField();
		urlInput.setFont(new Font("PingFang SC Regular", Font.PLAIN, 16));
		urlInput.setText("http://www.swust.edu.cn");
		urlInput.setBounds(10, 5, 824, 21);
		contentPane.add(urlInput);
		urlInput.setColumns(10);
		
		JButton getData = new JButton("\u2192");
		getData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String urlContent = HtmlParser.getHtmlContent(urlInput.getText().toString(), "utf-8");
				sourceCode.setText(urlContent);
				HtmlParser.saveHtmlContent(urlContent, "utf-8", "res/target/data.txt");
				
				List<String> srcList = new ArrayList<String>(); //�����洢��ȡ����ͼƬ��ַ
				Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");//ƥ���ַ����е�img��ǩ
				Matcher matcher = p.matcher(urlContent);
				boolean hasPic = matcher.find();
				if(hasPic == true)//�ж��Ƿ���ͼƬ
				{
					while(hasPic) //�������ͼƬ����ô�������в��ң�ֱ��ƥ�䲻��
					{
						String group = matcher.group(2);//��ȡ�ڶ�����������ݣ�Ҳ���� (.*?)ƥ�䵽��
						Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");//ƥ��ͼƬ�ĵ�ַ
						Matcher matcher2 = srcText.matcher(group);
						if( matcher2.find() ) 
						{
							srcList.add( matcher2.group(3) );//�ѻ�ȡ����ͼƬ��ַ��ӵ��б���
						}
						hasPic = matcher.find();//�ж��Ƿ���img��ǩ
					}
						
				}
				for (int i = 0; i < srcList.size(); i++) {
					try {
						URL url=new URL("http://www.swust.edu.cn/"+srcList.get(i));
				        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
				        conn.setRequestMethod("GET");
				        conn.setConnectTimeout(1000);//��ʱ��ʾ1��=1000����
				        InputStream inStream=conn.getInputStream();//��ȡ�����
				        byte[] data=readInputStream(inStream);
				        
				        File file=new File("res/target/"+i+".jpg");
				        FileOutputStream outStream=new FileOutputStream(file);
				        outStream.write(data);
			        
						outStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		getData.setFont(new Font("PingFang SC Bold", Font.PLAIN, 30));
		getData.setBounds(858, 4, 93, 23);
		contentPane.add(getData);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 946, 543);
		contentPane.add(scrollPane);
		
		sourceCode = new JTextArea();
		scrollPane.setViewportView(sourceCode);
		sourceCode.setLineWrap(true);
		sourceCode.setWrapStyleWord(true);
	}
	
	private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];//ת��Ϊ������
        int len=0;
        while((len =inStream.read(buffer))!=-1){
            outStream.write(buffer,0,len);
        }
        return  outStream.toByteArray();
    }


}
