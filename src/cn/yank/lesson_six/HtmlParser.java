
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
     * 获取url网页的源代码
     * @param urlStr 链接
     * @param charset 网页的编码格式
     * @return 网页源代码
     */
    public static String getHtmlContent(String urlStr, String charset) {         
        //检查urlStr头部格式是否正确
        if (!urlStr.toLowerCase().startsWith("http://")) {  
            urlStr = "http://" + urlStr;  
        } 

        URL url=null;
        StringBuffer contentBuffer = new StringBuffer(); //内容源代码  
        int responseCode = -1;  //网页返回信息吗
        HttpURLConnection con = null;  

        try { 
            url = new URL(urlStr); 
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE代理进行下载  
            con.setConnectTimeout(60000);  //连接超时设置为60s即一分钟
            con.setReadTimeout(60000);//网页停留时间
            //con.setDoOutput(true); 
            // 获得网页返回信息码  
            responseCode = con.getResponseCode();  
            if (responseCode == -1) {  
                System.out.println(url.toString() + " : connection is failure...");  
                con.disconnect();  
                return null;  
            }  
            if (responseCode >= 400) // 请求失败  
            {  
                System.out.println("请求失败:get response code: " + responseCode);  
                con.disconnect();  
                return null;  
            }  
            //获取网页源代码
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
     * 保存HTML内容
     * @param content HTML源代码内容
     * @param charset 网页编码格式
     * @param savePath 保存路径
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
