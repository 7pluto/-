package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MyMouseLambda extends MouseListener{

	/**
	 * ����lambda
	 * �õķ���ȥ������������default
	 */
	@Override
	public default void mouseReleased(MouseEvent e) {}
	
	@Override
	public default void mousePressed(MouseEvent e) {}
	
	@Override
	public default void mouseExited(MouseEvent e) {}
	
	@Override
	public default void mouseEntered(MouseEvent e) {}
	
	//@Override
	//public default void mouseClicked(MouseEvent e) {}
}
