package util;

public class StringUtil {

	/**
	 * �ж��Ƿ�Ϊ��
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
	 * �ж��Ƿ�ΪINT����
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
	 * �ж��Ƿ�Ϊ��������
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
     * ��������ַ���
     *
     * @param stringLength:���ɵ��ַ�������
     * @return
     */
    public static String getRandomString(int stringLength) {
        String string = "abcdefghijklmnopqrstuvwxyz";
        String strings = string.toUpperCase();
        string += strings;
        string += "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//����ȡ��0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }

    /**
     * �����������
     * @return
     */
    public static String getRandomPassword() {
    	String string = "abcdefghijklmnopqrstuvwxyz";
        String strings = string.toUpperCase();
        string += strings;
        string += "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//����ȡ��0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }
}
