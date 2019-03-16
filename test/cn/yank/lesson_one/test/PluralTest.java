
/**   
 * @Title: PluralTest.java 
 * @Package: cn.yank.lesson_one.test 
 * @Description: TODO
 * @author Yank  
 * @date 2019年3月16日 下午5:53:31 
 * @version 0.1 
 */


package cn.yank.lesson_one.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cn.yank.lesson_one.Plural;

/** 
 * @Description 
 * @author Yank
 * @date 2019年3月16日 下午5:53:31 
 * @version V0.1
 */

class PluralTest {

	@Test
	public void testPlus() {
		assertEquals(new Plural(2, 2).toString(), new Plural(1, 1).plus(new Plural(1, 1)).toString());
	}
	
	@Test
	public	void testMinus() {
		assertEquals(new Plural(0, 0).toString(), new Plural(1, 1).minus(new Plural(1, 1)).toString());
	}
	
	@Test
	public	void testMultiply() {
		assertEquals(new Plural(0, 2).toString(), new Plural(1, 1).minus(new Plural(1, 1)).toString());
	}
	
	@Test
	public	void testDivide() {
		assertEquals(new Plural(0, 2).toString(), new Plural(1, 1).divide(new Plural(1, 1)).toString());
	}

}
