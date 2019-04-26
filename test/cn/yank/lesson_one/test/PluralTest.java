
/**   
 * @Title: PluralTest.java 
 * @Package: cn.yank.lesson_one.test 
 * @Description: TODO
 * @author Yank  
 * @date 2019��3��16�� ����5:53:31 
 * @version 0.1 
 */

package cn.yank.lesson_one.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cn.yank.lesson_one.Plural;

/** 
 * @Description 
 * @author Yank
 * @date 2019��3��16�� ����5:53:31 
 * @version V0.1
 */

class PluralTest {

	@Test
	public void testPlus() {
		assertEquals(new Plural(2, 2).toString(), new Plural(1, 1).plus(new Plural(1, 1)).toString());
		assertEquals(new Plural(0, 4).toString(), new Plural(0, 2).plus(new Plural(0, 2)).toString());
		assertEquals(new Plural(0, 2).toString(), new Plural(0, 2).plus(new Plural(0, 0)).toString());
		assertEquals(new Plural(0, 2).toString(), new Plural(0, 0).plus(new Plural(0, 2)).toString());
		assertEquals(new Plural(7.5, 9).toString(), new Plural(3.3, 2.1).plus(new Plural(4.2, 6.9)).toString());
		assertEquals(new Plural(6, 0).toString(), new Plural(3, 0).plus(new Plural(3, 0)).toString());
		assertEquals(new Plural(3, 8).toString(), new Plural(3, 6).plus(new Plural(0, 2)).toString());
		assertEquals(new Plural(6, 6).toString(), new Plural(3, 0).plus(new Plural(3, 6)).toString());
		System.out.println(new Plural(3.3, 2.1).minus(new Plural(4.2, 6.9)).toString());
	}
	
	@Test
	public	void testMinus() {
		assertEquals(new Plural(0, 0).toString(), new Plural(1, 1).minus(new Plural(1, 1)).toString());
		assertEquals(new Plural(0, 0).toString(), new Plural(0, 2).minus(new Plural(0, 2)).toString());
		assertEquals(new Plural(0, 2).toString(), new Plural(0, 2).minus(new Plural(0, 0)).toString());
		assertEquals(new Plural(0, -2).toString(), new Plural(0, 0).minus(new Plural(0, 2)).toString());
		assertEquals(new Plural(-0.90, -4.80).toString(), new Plural(3.30, 2.10).minus(new Plural(4.20, 6.9)).toString());
		assertEquals(new Plural(0, 0).toString(), new Plural(3, 0).minus(new Plural(3, 0)).toString());
		assertEquals(new Plural(3, 4).toString(), new Plural(3, 6).minus(new Plural(0, 2)).toString());
		assertEquals(new Plural(0, -6).toString(), new Plural(3, 0).minus(new Plural(3, 6)).toString());
	}
	
	@Test
	public	void testMultiply() {
		assertEquals(new Plural(0, 2).toString(), new Plural(1, 1).multiply(new Plural(1, 1)).toString());
		assertEquals(new Plural(-4, 0).toString(), new Plural(0, 2).multiply(new Plural(0, 2)).toString());
		assertEquals(new Plural(0, 0).toString(), new Plural(0, 0).multiply(new Plural(0, 2)).toString());
		assertEquals(new Plural(0, 0).toString(), new Plural(0, 0).multiply(new Plural(0, 2)).toString());
		assertEquals(new Plural(-0.6300000000000026, 31.59).toString(), new Plural(3.3, 2.1).multiply(new Plural(4.2, 6.9)).toString());
		assertEquals(new Plural(9, 0).toString(), new Plural(3, 0).multiply(new Plural(3, 0)).toString());
		assertEquals(new Plural(-12, 6).toString(), new Plural(3, 6).multiply(new Plural(0, 2)).toString());
		assertEquals(new Plural(9, 18).toString(), new Plural(3, 0).multiply(new Plural(3, 6)).toString());
	}
	
	@Test
	public	void testDivide() {
		assertEquals(new Plural(1, 0).toString(), new Plural(1, 1).divide(new Plural(1, 1)).toString());
		assertEquals(new Plural(1, 0).toString(), new Plural(0, 2).divide(new Plural(0, 2)).toString());
		assertEquals(new Plural(0, 0).toString(), new Plural(0, 0).divide(new Plural(0, 2)).toString());
		assertEquals(new Plural(0, 0).toString(), new Plural(0, 0).divide(new Plural(0, 2)).toString());
		assertEquals(new Plural(0.4344827586206897, -0.21379310344827585).toString(), new Plural(3.3, 2.1).divide(new Plural(4.2, 6.9)).toString());
		assertEquals(new Plural(1, 0).toString(), new Plural(3, 0).divide(new Plural(3, 0)).toString());
		assertEquals(new Plural(3, -1.5).toString(), new Plural(3, 6).divide(new Plural(0, 2)).toString());
		assertEquals(new Plural(0.2, -0.4).toString(), new Plural(3, 0).divide(new Plural(3, 6)).toString());
	}

}
