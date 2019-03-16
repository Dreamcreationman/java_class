
/**   
 * @Title: TestMain.java 
 * @Package: cn.yank.lesson_one 
 * @Description: TODO
 * @author Yank  
 * @date 2019年3月16日 下午3:08:12 
 * @version 0.1 
 */


package cn.yank.lesson_one;

/** 
 * @Description 
 * @author Yank
 * @date 2019年3月16日 下午3:08:12 
 * @version V0.1
 */

public class Main {

	public static void main(String[] args) {
		Plural testData1 = new Plural(1, 1);
		Plural testData2 = new Plural(1, 1);
		
		System.out.println("testData1 is: "+ testData1.toString() + " \ntestData2 is: " + testData2.toString());
		System.out.println("test operation add: " + testData1.plus(testData2).toString());
		System.out.println("test operation minus: " + testData1.minus(testData2).toString());
		System.out.println("test operation multiply: " + testData1.multiply(testData2).toString());
		System.out.println("test operation mod: " + testData1.divide(testData2).toString());
	}
}
