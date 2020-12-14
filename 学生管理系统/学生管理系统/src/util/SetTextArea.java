package util;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class SetTextArea {

	//���ý��տ��ı���������

	public static void setInfoWindosFont(JTextPane mAreaCode,String str, Color col,boolean bold,int fontSize) {

		SimpleAttributeSet attrSet = new SimpleAttributeSet();

		StyleConstants.setForeground(attrSet, col);//������ɫ

		if (bold) {

			StyleConstants.setBold(attrSet, bold);//���ô���

		}

		StyleConstants.setFontSize(attrSet, fontSize);//�����ֺ�

			

		/*********infoWindowΪJTextPane�ı��������*****************/

		Document doc = mAreaCode.getDocument();	

		try {

			doc.insertString(doc.getLength(), str, attrSet);

		} catch (Exception e) {


			JOptionPane.showMessageDialog(null, "�������ô���", "��ʾ", JOptionPane.ERROR_MESSAGE);	

		}

	}
}
