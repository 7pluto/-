package util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class SetScrollPanes {

	/**
	 * 重绘scrollPane
	 * @param scrollPane
	 */
	public static void setScrollPane(JScrollPane scrollPane) {
		JScrollPane newScrollPane = scrollPane;
		newScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			protected void configureScrollBarColors() {
				// 把手
				thumbColor = Color.lightGray;
				// 滑道         
				trackColor = Color.white;
				//setThumbBounds(0, 0, 3, 10);
			}
			public Dimension getPreferredSize(JComponent c) {
				c.setPreferredSize(new Dimension(6, 0));
				return super.getPreferredSize(c);
			}
			//滑倒上按钮
			protected JButton createDecreaseButton(int orientation) {
				JButton button = new JButton();
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setBorder(null);
				return button;
			}
			//滑倒下按钮
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
