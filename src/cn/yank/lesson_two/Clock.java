
/**   
 * @Title: Clock.java 
 * @Package: cn.yank.lesson_two 
 * @Description: TODO
 * @author Yank  
 * @date 2019年3月23日 下午2:10:40 
 * @version 0.1 
 */


package cn.yank.lesson_two;
import java.applet.Applet;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;



/** 
 * @Description 
 * @author Yank
 * @date 2019年3月23日 下午2:10:40 
 * @version V0.1
 */

@SuppressWarnings("deprecation")
public class Clock extends Applet{
	
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1L;
	public Thread thread;

	/**
	 * Description  
	 * @see java.applet.Applet#init() 
	 */ 
		
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		/*thread = new Thread(new Runnable() {
					
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					repaint();
				}
			}
		});
		thread.start();*/
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
				
			}
		}, 1000,1000);
		System.out.println(Math.sin(Math.PI));
	}
	
	
	/**
	 * Description 
	 * @param arg0 
	 * @see java.awt.Container#paint(java.awt.Graphics) 
	 */ 
		
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
		arg0.drawOval(0, 0, 200, 200);
		arg0.drawOval(99, 99, 2, 2);
		for (int i = 0; i < 60; i++) {
			double theta = i*(Math.PI/30);
			int startX1 = (int)(100+100*Math.sin(theta));
			int endY1 = (int)(0+100-100*Math.cos(theta));
			int length = 0;
			if (i%5==0) {
				length = 20;
			}else {
				length = 5;
			}
			int startX2 = (int)(100+(100-length)*Math.sin(theta));
			int endY2 = (int)(100-(100-length)*Math.cos(theta));
			/*int radius = 2;*/
			
			/*arg0.drawOval(startX1, endY1, radius, radius);*/
			arg0.drawLine(startX1, endY1, startX2, endY2);
		}
		
		Date date = Calendar.getInstance().getTime();
		int hours = date.getHours();
		int minutes = date.getMinutes();
		int seconds = date.getSeconds();
		String hour = String.valueOf(hours).length() == 2?String.valueOf(hours):0+String.valueOf(hours);
		String minute = String.valueOf(minutes).length() == 2?String.valueOf(minutes):0+String.valueOf(minutes);
		String second = String.valueOf(seconds).length() == 2?String.valueOf(seconds):0+String.valueOf(seconds);
		arg0.drawString(hour+" : "+minute+" : "+second, 100, 250);
		
		double aHours = 2*Math.PI*hours/12 + minutes * Math.PI /360;
		double aminutes = 2*Math.PI*minutes/60;
		double aSeconds = 2*Math.PI*seconds/60;
		int hoursLength = 40;
		int minutesLength = 60;
		int secondLength = 80;
		arg0.drawLine(100, 100, (int)(100+hoursLength*Math.sin(aHours)), (int)(100-hoursLength*Math.cos(aHours)));
		arg0.drawLine(100, 100, (int)(100+minutesLength*Math.sin(aminutes)), (int)(100-minutesLength*Math.cos(aminutes)));
		arg0.drawLine(100, 100, (int)(100+secondLength*Math.sin(aSeconds)), (int)(100-secondLength*Math.cos(aSeconds)));
		
		
		

		/*try {
			thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
	}
}
