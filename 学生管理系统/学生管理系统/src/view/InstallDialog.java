package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import model.ImageList;

@SuppressWarnings("serial")
public class InstallDialog extends JDialog {

	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());


	
	public InstallDialog(JFrame f,int x,int y) {
		super(f);
		this.x = x;
		this.y = y;
		viewBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
	}

	private void controlDefine() {
		
	}

	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		
		add(this.mBtExit);
	}

	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorder(null);
	}

	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			setVisible(false);
		});
	}

	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(this.x+121, this.y+96, 478, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
