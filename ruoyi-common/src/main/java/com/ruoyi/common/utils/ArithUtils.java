package com.ruoyi.common.utils;

import java.math.BigDecimal;
/**
 * BigDecimal加减乘除运算封装
 * @author miki
 *
 */
public class ArithUtils {

	/**
	 * BigDecimal的加法运算封装
	 * @param b1
	 * @param bn
	 * @return
	 */
	public static BigDecimal safeAdds(BigDecimal b1, BigDecimal... bn) {
	       if (null == b1) {
	           b1 = BigDecimal.ZERO;
	       }
	       if (null != bn) {
	           for (BigDecimal b : bn) {
	               b1 = b1.add(null == b ? BigDecimal.ZERO : b);
	           }
	       }
	       return b1.setScale(5,BigDecimal.ROUND_HALF_UP);
	   }
	
	/**
	 * BigDecimal的减法运算封装
	 * @param b1
	 * @param bn
	 * @return
	 */
	public static BigDecimal safeSubtracts(BigDecimal b1, BigDecimal... bn) {
	       if (null == b1) {
	           b1 = BigDecimal.ZERO;
	       }
	       if (null != bn) {
	           for (BigDecimal b : bn) {
	               b1 = b1.subtract(null == b ? BigDecimal.ZERO : b);
	           }
	       }
	       return b1.setScale(5,BigDecimal.ROUND_HALF_UP);
	   }
	
	/**
	 * BigDecimal的乘法运算封装
	 * @param b1
	 * @param bn
	 * @return
	 */
	public static BigDecimal safeMultiplys(BigDecimal b1, BigDecimal... bn) {
	       if (null == b1) {
	           b1 = BigDecimal.ZERO;
	       }
	       if (null != bn) {
	           for (BigDecimal b : bn) {
	               b1 = b1.multiply(null == b ? BigDecimal.ZERO : b);
	           }
	       }
	       return b1.setScale(5,BigDecimal.ROUND_HALF_UP);
	   }
	
	/**
	 * BigDecimal的除法运算封装
	 * @param b1
	 * @param bn
	 * @return
	 */
	public static BigDecimal safeDivides(BigDecimal b1, BigDecimal... bn) {
	       if (null == b1) {
	           b1 = BigDecimal.ZERO;
	       }
	       if (null != bn) {
	           for (BigDecimal b : bn) {
	        	   if(null == b || b.compareTo(new BigDecimal("0.000")) == 0) {
	        		   return BigDecimal.ZERO;
		    	   }
	               b1 = b1.divide(null == b ? BigDecimal.ZERO : b,5,BigDecimal.ROUND_HALF_UP);
	           }
	       }
	       return b1.setScale(5,BigDecimal.ROUND_HALF_UP);
	   }
	
	/**
	 * 判断空则初始化0
	 * @param b
	 * @return
	 */
	public static BigDecimal isNullBigDecimal(BigDecimal b) {
		if(b==null)b=BigDecimal.ZERO;
	       return b;
	}
	
	public static BigDecimal setScale(BigDecimal b,int length) {
		if(b==null)b=BigDecimal.ZERO;
	       return b.setScale(length,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 整数向上取整如(0.1 = 1)
	 * @param b
	 * @return
	 */
	public static BigDecimal setScale(BigDecimal b) {
		if(b==null)return b=BigDecimal.ZERO;
		Integer res = compare(b, new BigDecimal("1"));
		if(res == -1) {
			return b.setScale(0,BigDecimal.ROUND_UP);
		}
		else {
			return b.setScale(0,BigDecimal.ROUND_HALF_UP);
		}
	}
	
	/**
	 * BigDecimal比较大小
	 * @param a
	 * @param b
	 * @return -1 表示a小于b 0 表示 a等于b 1 表示a大于b
	 */
	public static Integer compare(BigDecimal a,BigDecimal b) {
		if(null == a)a = BigDecimal.ZERO;
		if(null == b)b = BigDecimal.ZERO;
		Integer flag = a.compareTo(b);
		return flag;
	}
	
	public static BigDecimal stringToBigDecimal(String b) {
		if(StringUtils.isBlank(b))new BigDecimal(0);
	       return new BigDecimal(b);
	}
	
	/**
	 * 	判断是否等于0
	 * @param b
	 * @return true or false
	 */
	public static boolean valueIsZero(BigDecimal b) {
		if(null == b) b = BigDecimal.ZERO;
		boolean flag = false;
		Integer res = compare(b, BigDecimal.ZERO);
		if(0 == res) {
			flag = true;
		}
		return flag;
	}
	
	public static void main(String[] args) {
//		 safeAdds(new BigDecimal("1"), new BigDecimal("1"),new BigDecimal("1"),new BigDecimal("1"));
//		 safeMultiplys(new BigDecimal("1.1"), new BigDecimal("2.2"));
//		 safeSubtracts(new BigDecimal("2.2"), new BigDecimal("1.1"),new BigDecimal("0.1"));
//		 safeDivides(new BigDecimal("10"), new BigDecimal("2"), new BigDecimal("2"));
//
//		safeDivides(new BigDecimal("512"), new BigDecimal("2"), new BigDecimal("11550.00"));
//
//		BigDecimal v1=new BigDecimal(String.valueOf(Math.pow(Double.valueOf("82.14"),Double.valueOf(" -0.347"))));
//		System.out.println(safeMultiplys(new BigDecimal("2.2139"),v1));
		
		System.out.println(new BigDecimal("0.5").setScale(0,BigDecimal.ROUND_UP));
	}
	

}
