package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.ImageList;

@SuppressWarnings("serial")
public class CountScoreDialog extends JDialog {

	@SuppressWarnings("unused")
	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JTabbedPane mTabbedPane = null;
	
	
	public CountScoreDialog(JFrame f,int x,int y) {
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
		this.mTabbedPane = new JTabbedPane();
	}


	private void controlBuild() {
		this.mBtExit.setBounds(770, 0, 30, 30);
		this.mTabbedPane.setBounds(20, 50, 760, 520);
		this.mTabbedPane.add("统计学生成绩情况", new DrawCountScoreJPanel1());
		this.mTabbedPane.add("统计学分情况", new DrawCountScoreJPanel2());
		this.mTabbedPane.add("排序情况", new DrawCountScoreJPanel3());
		
		add(this.mTabbedPane);
		add(this.mBtExit);
	}


	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorder(null);
		this.mTabbedPane.setBackgroundAt(0, Color.BLACK);
		this.mTabbedPane.setBackgroundAt(1, Color.BLACK);
		this.mTabbedPane.setBackgroundAt(2, Color.BLACK);
		this.mTabbedPane.setForegroundAt(0, Color.RED);
		this.mTabbedPane.setForegroundAt(1, Color.BLUE);
		this.mTabbedPane.setForegroundAt(2, Color.GREEN);
	}


	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			setVisible(false);
		});
	}


	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(300, 60, 800, 600);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
