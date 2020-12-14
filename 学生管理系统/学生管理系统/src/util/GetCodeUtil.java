package util;

import java.util.Random;

public class GetCodeUtil {

	public static String[] getRandom() {
		Random random = new Random();
		int a , b , i , add = 0 ;
		char c = 0 ;
		a = random.nextInt(100);
		b = random.nextInt(100);
		i = random.nextInt(2);
		c = getChar(i);
		add = getAdd(a,c,b);
		String s[] = {String.valueOf(a),String.valueOf(c),String.valueOf(b),"=",String.valueOf(add)};
		return s;
	}

	private static int getAdd(int a, char c, int b) {
		if(c == '+') {
			return a + b;
		}else if(c == '-') {
			return a - b;
		}
		return 0 ;
	}

	private static char getChar(int i) {
		if(i == 0) {
			return '-';
		}else if(i == 1) {
			return '+';
		}
		return '0';
	}
}
