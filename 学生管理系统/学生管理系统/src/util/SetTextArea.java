package util;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class SetTextArea {

	//设置接收框文本字体属性

	public static void setInfoWindosFont(JTextPane mAreaCode,String str, Color col,boolean bold,int fontSize) {

		SimpleAttributeSet attrSet = new SimpleAttributeSet();

		StyleConstants.setForeground(attrSet, col);//设置颜色

		if (bold) {

			StyleConstants.setBold(attrSet, bold);//设置粗体

		}

		StyleConstants.setFontSize(attrSet, fontSize);//设置字号

			

		/*********infoWindow为JTextPane文本域的名称*****************/

		Document doc = mAreaCode.getDocument();	

		try {

			doc.insertString(doc.getLength(), str, attrSet);

		} catch (Exception e) {


			JOptionPane.showMessageDialog(null, "字体设置错误！", "提示", JOptionPane.ERROR_MESSAGE);	

		}

	}
}
