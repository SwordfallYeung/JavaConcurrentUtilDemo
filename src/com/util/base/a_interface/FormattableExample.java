package com.util.base.a_interface;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Locale;

import static java.util.FormattableFlags.ALTERNATE;
import static java.util.FormattableFlags.LEFT_JUSTIFY;

/**
 * @author y15079
 * @create 2017-09-23 16:28
 * @desc 一个接口，可以被其他类implement，再作为参数传给formatter.format()。
 **/
public class FormattableExample {

	public static class StockName implements Formattable{
		private String symbol,companyName,frenchCompanyName;

		public StockName(String symbol, String companyName, String frenchCompanyName) {
			this.symbol = symbol;
			this.companyName = companyName;
			this.frenchCompanyName = frenchCompanyName;
		}

		/**
		 *
		 * @param formatter
		 * @param flags  为 FormattableFlags
		 * @param width
		 * @param precision
		 */
		@Override
		public void formatTo(Formatter formatter, int flags, int width, int precision) {
			StringBuilder sb=new StringBuilder();

			//定义名字
			String name=companyName;
			//如果语言不同，则切换
			if (formatter.locale().equals(Locale.FRANCE))
				name=frenchCompanyName;
			boolean alternate=(flags & ALTERNATE) == ALTERNATE;
			boolean usesymbol=alternate || (precision != -1 && precision < 10);
			String out = (usesymbol ? symbol : name);

			//apply precision
			if (precision == -1 || out.length() < precision){
				//write it all
				sb.append(out);
			}else {
				sb.append(out.substring(0,precision-1)).append('*');
			}

			//apply width and justification
			int len=sb.length();
			if (len<width){
				for (int i=0;i<width-len;i++)
					if ((flags & LEFT_JUSTIFY) == LEFT_JUSTIFY)
						sb.append(' ');
				    else
				    	sb.insert(0,' ');
			formatter.format(sb.toString());
			}
		}

		@Override
		public String toString() {
			return String.format("%s -%s",symbol,companyName);
		}
	}

	public static void main(String[] args) {
		Formatter formatter=new Formatter();
		StockName sn=new StockName("HUGE","Huge Fruit, Inc.","Fruit Titanesque, Inc.");
		formatter.format("%s",sn);                         //   -> "Huge Fruit, Inc."
		formatter.format("%s",sn.toString());             //   -> "HUGE - Huge Fruit, Inc."
		formatter.format("%#s",sn);                       //   -> "HUGE"
		formatter.format("%-10.8s",sn);                   //   -> "HUGE      "
		formatter.format("%.12s",sn);                     //   -> "Huge Fruit,*"
		formatter.format(Locale.FRANCE,"%25s",sn); //   -> "   Fruit Titanesque, Inc."

	}
}
