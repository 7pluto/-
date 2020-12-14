package util;

import java.awt.Color;
import java.util.Random;

public class GetRandomColorAndFont {

	public static Color getColor() {
		Random random = new Random();
		int i = random.nextInt(5);
		if(i == 0) {
			return Color.WHITE;
		}else if(i == 1) {
			return Color.BLACK;
		}else if(i == 2) {
			return Color.RED;
		}else if(i == 3) {
			return Color.YELLOW;
		}else if(i == 4) {
			return Color.BLUE;
		}
		return null;
	}
	
	public static int getFontSize() {
		Random random = new Random();
		int i = random.nextInt(13) + 10;
		return i;
	}
	
	public static boolean getFontBold() {
		Random random = new Random();
		int i = random.nextInt(2);
		if(i == 1) {
			return true;
		}
		return false;
	}
}
