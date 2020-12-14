package util;

public class StringUtil {

	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		boolean isFlag = false;
		if("".equals(str)) {
			isFlag = true;
		}
		return isFlag;
	}
	
	/**
	 * 判断是否为INT类型
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		boolean isFlag = true;
		try {
			Integer.parseInt(str);
		}catch (Exception e) {
			isFlag = false;
		}
		return isFlag;
	}

	/**
	 * 判断是否为数字序列
	 * @param str
	 * @return
	 */
	public static boolean isNumberString(String str) {
		boolean isFlag = true;
		try {
			Long.parseLong(str);
		}catch (Exception e) {
			isFlag = false;
		}
		return isFlag;
	}

    /**
     * 生成随机字符串
     *
     * @param stringLength:生成的字符串长度
     * @return
     */
    public static String getRandomString(int stringLength) {
        String string = "abcdefghijklmnopqrstuvwxyz";
        String strings = string.toUpperCase();
        string += strings;
        string += "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//向下取整0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 随机生成密码
     * @return
     */
    public static String getRandomPassword() {
    	String string = "abcdefghijklmnopqrstuvwxyz";
        String strings = string.toUpperCase();
        string += strings;
        string += "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//向下取整0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }
}
