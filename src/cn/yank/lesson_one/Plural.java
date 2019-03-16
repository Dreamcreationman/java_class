package cn.yank.lesson_one;

/**
 * 
 * @Description 
 * @author Yank
 * @date 2019年3月16日 下午3:08:27 
 * @version V0.1
 */
public class Plural {

	private double realPart = 0;
	private double imaginaryPart = 0;
	
	public Plural(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}
	
	/**
	 * 
	 * @Description Implementation the add operation of plural
	 * @author Yank
	 * @date 2019年3月16日 下午2:59:10 
	 * @param plural is another factor of add operation
	 * @return
	 */
	public Plural plus(Plural plural) {
		return new Plural(this.realPart + plural.realPart, this.imaginaryPart + plural.imaginaryPart);
	}
	
	/**
	 * 
	 * @Description Implementation the minus operation of plural
	 * @author Yank
	 * @date 2019年3月16日 下午3:01:53 
	 * @param plural is the subtracted of minus operation
	 * @return
	 */
	public Plural minus(Plural plural) {
		return new Plural(this.realPart - plural.realPart, this.imaginaryPart - plural.imaginaryPart);
	}
	
	/**
	 * 
	 * @Description Implementation the minus operation of plural
	 * @author Yank
	 * @date 2019年3月16日 下午3:05:51 
	 * @param plural is another factor of add operation
	 * @return
	 */
	public Plural multiply(Plural plural) {
		return new Plural(this.realPart * plural.realPart - this.imaginaryPart * plural.imaginaryPart, this.imaginaryPart * plural.realPart + this.realPart * plural.imaginaryPart);
	}
	
	/**
	 * 
	 * @Description Implementation the mod operation of plural
	 * @author Yank
	 * @date 2019年3月16日 下午3:06:57 
	 * @param plural is the divisor of minus operation
	 * @return
	 */
	public Plural divide(Plural plural) {
		double squareTemp = Math.pow(plural.realPart, 2) + Math.pow(plural.imaginaryPart, 2);
		return new Plural((this.realPart * plural.realPart + this.imaginaryPart * plural.imaginaryPart)/squareTemp, (this.imaginaryPart * plural.realPart - this.realPart * plural.imaginaryPart)  / squareTemp);
	}
	
	/**
	 * 
	 * @Description judge if a number is int
	 * @author Yank
	 * @date 2019年3月16日 下午3:54:30 
	 * @param num
	 * @return if it's a int, reuturn true
	 */
	private boolean judgeNumber(double num) {
		if ((num - Math.floor(num))  == 0.0) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Number real, imaginary;
		if (judgeNumber(this.realPart)) {
			real = (int)this.realPart;
		}else {
			real = this.realPart;
		}
		if (judgeNumber(this.imaginaryPart)) {
			imaginary = (int)this.imaginaryPart;
		}else {
			imaginary = this.imaginaryPart;
		}
		String result = "";
		if (this.realPart == 0 && this.imaginaryPart == 0) {
			result += "0";
		}else if (this.realPart == 0 && this.imaginaryPart != 0) {
			result += imaginary+"i";
		}else {
			if (this.imaginaryPart > 0) {
				result += real + "+" + imaginary + "i";
			}else if(this.imaginaryPart < 0){
				result += "" + real + imaginary + "i";
			}else {
				result += real;
			}
		}
		return result;
	}
}