
/**   
 * @Title: HtmlParser.java 
 * @Package: cn.yank.lesson_six 
 * @Description: TODO
 * @author Yank  
 * @date May 7, 2019 11:18:49 PM 
 * @version 0.1 
 */


package cn.yank.lesson_six;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/** 
 * @Description 
 * @author Yank
 * @date May 7, 2019 11:18:49 PM 
 * @version V0.1
 */

public class HtmlParser {

	 /**
     * ��ȡurl��ҳ��Դ����
     * @param urlStr ����
     * @param charset ��ҳ�ı����ʽ
     * @return ��ҳԴ����
     */
    public static String getHtmlContent(String urlStr, String charset) {         
        //���urlStrͷ����ʽ�Ƿ���ȷ
        if (!urlStr.toLowerCase().startsWith("http://")) {  
            urlStr = "http://" + urlStr;  
        } 

        URL url=null;
        StringBuffer contentBuffer = new StringBuffer(); //����Դ����  
        int responseCode = -1;  //��ҳ������Ϣ��
        HttpURLConnection con = null;  

        try { 
            url = new URL(urlStr); 
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE�����������  
            con.setConnectTimeout(60000);  //���ӳ�ʱ����Ϊ60s��һ����
            con.setReadTimeout(60000);//��ҳͣ��ʱ��
            //con.setDoOutput(true); 
            // �����ҳ������Ϣ��  
            responseCode = con.getResponseCode();  
            if (responseCode == -1) {  
                System.out.println(url.toString() + " : connection is failure...");  
                con.disconnect();  
                return null;  
            }  
            if (responseCode >= 400) // ����ʧ��  
            {  
                System.out.println("����ʧ��:get response code: " + responseCode);  
                con.disconnect();  
                return null;  
            }  
            //��ȡ��ҳԴ����
            InputStream in = con.getInputStream();  
            //InputStream in = url.openStream();  
            InputStreamReader is = new InputStreamReader(in, charset);  
            BufferedReader br = new BufferedReader(is);  

            String str = null;  
            while ((str = br.readLine()) != null)  
                contentBuffer.append(str);  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            contentBuffer = null;  
            System.out.println("error: " + url.toString());  
        }finally {  
            con.disconnect();  
        }  
        return contentBuffer.toString();  
    }  

    /**
     * ����HTML����
     * @param content HTMLԴ��������
     * @param charset ��ҳ�����ʽ
     * @param savePath ����·��
     */
    public static void saveHtmlContent(String content,String charset,String savePath){        
        try {
            FileOutputStream fout = new FileOutputStream(savePath);
            OutputStreamWriter os = new OutputStreamWriter(fout, charset);
            BufferedWriter bw=new BufferedWriter(os);
            bw.write(content);
            bw.flush();
            bw.close();  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public static void main(String argsp[]){ 
        String urlStr="http://zmryyj.blog.163.com/blog/static/984507512011318104813331/";
        String charset="gb2312";     
        String content=getHtmlContent(urlStr,charset);
        System.out.println(content) ;   
        saveHtmlContent(content,charset,"./index.html");
    }  
}
