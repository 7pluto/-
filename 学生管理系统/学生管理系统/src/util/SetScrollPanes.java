package util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class SetScrollPanes {

	/**
	 * �ػ�scrollPane
	 * @param scrollPane
	 */
	public static void setScrollPane(JScrollPane scrollPane) {
		JScrollPane newScrollPane = scrollPane;
		newScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			protected void configureScrollBarColors() {
				// ����
				thumbColor = Color.lightGray;
				// ����         
				trackColor = Color.white;
				//setThumbBounds(0, 0, 3, 10);
			}
			public Dimension getPreferredSize(JComponent c) {
				c.setPreferredSize(new Dimension(6, 0));
				return super.getPreferredSize(c);
			}
			//�����ϰ�ť
			protected JButton createDecreaseButton(int orientation) {
				JButton button = new JButton();
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setBorder(null);
				return button;
			}
			//�����°�ť
			protected JButton createIncreaseButton(int orientation) {
				JButton button = new JButton();
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setBorder(null);
				return button;
			}
		});
	}
}
