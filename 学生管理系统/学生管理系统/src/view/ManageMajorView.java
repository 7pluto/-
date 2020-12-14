package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.ImageList;

@SuppressWarnings("serial")
public class ManageMajorView extends JFrame {
	
	@SuppressWarnings("unused")
	private String userNumber;
	private int xOld = 0,yOld = 0;
	private int x = 470, y = 220;
	
	private JButton mBtExit,mBtMinimize;
	private JLabel mL1,mL2;
	private JButton mBt1,mBt2,mBt3,mBt4;
	private JButton mBtIncreate,mBtUpdate,mBtFind,mBtInput;
	private JButton mBtScoreFind,mBtScoreAdd,mBtScoreInput;
	
	
	private boolean b1 = false,b2 = false;
	
	private ImageList iL = new ImageList(this.getClass());

	public ManageMajorView(String userNumber) {
		this.userNumber = userNumber;
		viewBuild();
		mouseBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
	}

	private void controlDefine() {
		this.mBtExit = new JButton(this.iL.getImageButtonExit0());
		this.mBtMinimize = new JButton(this.iL.getImageButtonMinimize0());
		this.mL1 = new JLabel();
		this.mL2 = new JLabel();
		this.mBt1 = new JButton();
		this.mBt2 = new JButton();
		this.mBt3 = new JButton();
		this.mBt4 = new JButton();
		this.mBtIncreate = new JButton();
		this.mBtUpdate = new JButton();
		this.mBtFind = new JButton();
		this.mBtInput = new JButton();
		this.mBtScoreFind = new JButton();
		this.mBtScoreAdd = new JButton();
		this.mBtScoreInput = new JButton();
	}
	
	private void controlBuild() {
		this.mBtExit.setBounds(570, 0, 30, 30);
		this.mBtMinimize.setBounds(540, 0, 30, 30);
		this.mL1.setBounds(0, 30, 600, 1);
		this.mL1.setOpaque(true);
		this.mL1.setBackground(Color.BLACK);
		this.mL2.setBounds(121, 30, 1, 370);
		this.mL2.setOpaque(true);
		this.mL2.setBackground(Color.BLACK);
		
		
		this.mBt1.setBounds(0, 32, 120, 30);
		this.mBt1.setBackground(Color.GRAY);
		this.mBt1.setText("管理学生信息");
		this.mBt2.setBounds(0, 60, 120, 30);
		this.mBt2.setBackground(Color.GRAY);
		this.mBt2.setText("管理学生成绩");
		this.mBt3.setBounds(0, 90, 120, 30);
		this.mBt3.setBackground(Color.GRAY);
		this.mBt3.setText("系统帮助");
		this.mBt4.setBounds(0, 120, 120, 30);
		this.mBt4.setBackground(Color.GRAY);
		this.mBt4.setText("退出");
		
		this.mBtInput.setBounds(0, 60, 120, 25);
		this.mBtInput.setBackground(Color.lightGray);
		this.mBtInput.setVisible(false);
		this.mBtInput.setText("查看学生信息");
		this.mBtFind.setBounds(0, 85, 120, 25);
		this.mBtFind.setBackground(Color.lightGray);
		this.mBtFind.setVisible(false);
		this.mBtFind.setText("查找学生信息");
		this.mBtIncreate.setBounds(0, 110, 120, 25);
		this.mBtIncreate.setBackground(Color.lightGray);
		this.mBtIncreate.setVisible(false);
		this.mBtIncreate.setText("添加学生信息");
		this.mBtUpdate.setBounds(0, 135, 120, 25);
		this.mBtUpdate.setBackground(Color.lightGray);
		this.mBtUpdate.setVisible(false);
		this.mBtUpdate.setText("修改学生信息");
		
		this.mBtScoreFind.setBounds(0, 90, 120, 25);
		this.mBtScoreFind.setBackground(Color.lightGray);
		this.mBtScoreFind.setVisible(false);
		this.mBtScoreFind.setText("查找学生成绩");
		this.mBtScoreAdd.setBounds(0, 115, 120, 25);
		this.mBtScoreAdd.setBackground(Color.lightGray);
		this.mBtScoreAdd.setVisible(false);
		this.mBtScoreAdd.setText("统计成绩");
		this.mBtScoreInput.setBounds(0, 140, 120, 25);
		this.mBtScoreInput.setBackground(Color.lightGray);
		this.mBtScoreInput.setVisible(false);
		this.mBtScoreInput.setText("修改学生成绩");
		
		
		add(this.mBtExit);
		add(this.mBtMinimize);
		add(this.mL1);
		add(this.mL2);
		add(this.mBt1);
		add(this.mBt2);
		add(this.mBt3);
		add(this.mBt4);
		add(this.mBtInput);
		add(this.mBtFind);
		add(this.mBtIncreate);
		add(this.mBtUpdate);
		add(this.mBtScoreFind);
		add(this.mBtScoreAdd);
		add(this.mBtScoreInput);
	}
	
	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			buttonListenerImpl(0);
		});
		this.mBtMinimize.addActionListener(e -> {
			buttonListenerImpl(1);
		});
		this.mBt1.addActionListener(e -> {
			if(this.b2 == false) {
				if(this.b1 == false) {
					this.mBtInput.setVisible(true);
					this.mBtFind.setVisible(true);
					this.mBtIncreate.setVisible(true);
					this.mBtUpdate.setVisible(true);
					
					this.mBt2.setBounds(0, 160, 120, 30);
					this.mBt3.setBounds(0, 190, 120, 30);
					this.mBt4.setBounds(0, 220, 120, 30);
					this.b1 = true;
				}else {
					this.mBtInput.setVisible(false);
					this.mBtFind.setVisible(false);
					this.mBtIncreate.setVisible(false);
					this.mBtUpdate.setVisible(false);
					
					this.mBt2.setBounds(0, 60, 120, 30);
					this.mBt3.setBounds(0, 90, 120, 30);
					this.mBt4.setBounds(0, 120, 120, 30);
					this.b1 = false;
				}
			}else {
				if(this.b1 == false) {
					this.mBtInput.setVisible(true);
					this.mBtFind.setVisible(true);
					this.mBtIncreate.setVisible(true);
					this.mBtUpdate.setVisible(true);
					
					this.mBt2.setBounds(0, 160, 120, 30);
					this.mBtScoreFind.setBounds(0, 190, 120, 25);
					this.mBtScoreAdd.setBounds(0, 215, 120, 25);
					this.mBtScoreInput.setBounds(0, 240, 120, 25);
					this.mBt3.setBounds(0, 265, 120, 30);
					this.mBt4.setBounds(0, 295, 120, 30);
					this.b1 = true;
				}else {
					this.mBtInput.setVisible(false);
					this.mBtFind.setVisible(false);
					this.mBtIncreate.setVisible(false);
					this.mBtUpdate.setVisible(false);
					
					this.mBt2.setBounds(0, 60, 120, 30);
					this.mBtScoreFind.setBounds(0, 90, 120, 25);
					this.mBtScoreAdd.setBounds(0, 115, 120, 25);
					this.mBtScoreInput.setBounds(0, 140, 120, 25);
					this.mBt3.setBounds(0, 165, 120, 30);
					this.mBt4.setBounds(0, 195, 120, 30);
					this.b1 = false;
				}
			}
		});
		this.mBt2.addActionListener(e -> {
			if(this.b1 == false) {
				if(this.b2 == false) {
					this.mBtScoreFind.setBounds(0, 90, 120, 25);
					this.mBtScoreAdd.setBounds(0, 115, 120, 25);
					this.mBtScoreInput.setBounds(0, 140, 120, 25);
					this.mBtScoreFind.setVisible(true);
					this.mBtScoreAdd.setVisible(true);
					this.mBtScoreInput.setVisible(true);
					
					this.mBt3.setBounds(0, 165, 120, 30);
					this.mBt4.setBounds(0, 195, 120, 30);
					this.b2 = true;
				}else {
					this.mBtScoreFind.setBounds(0, 90, 120, 25);
					this.mBtScoreAdd.setBounds(0, 115, 120, 25);
					this.mBtScoreInput.setBounds(0, 140, 120, 25);
					this.mBtScoreFind.setVisible(false);
					this.mBtScoreAdd.setVisible(false);
					this.mBtScoreInput.setVisible(false);
					
					this.mBt3.setBounds(0, 90, 120, 30);
					this.mBt4.setBounds(0, 120, 120, 30);
					this.b2 = false;
				}
			}else {
				if(this.b2 == false) {
					this.mBtScoreFind.setBounds(0, 190, 120, 25);
					this.mBtScoreAdd.setBounds(0, 215, 120, 25);
					this.mBtScoreInput.setBounds(0, 240, 120, 25);
					this.mBtScoreFind.setVisible(true);
					this.mBtScoreAdd.setVisible(true);
					this.mBtScoreInput.setVisible(true);
					
					this.mBt3.setBounds(0, 265, 120, 30);
					this.mBt4.setBounds(0, 295, 120, 30);
					this.b2 = true;
				}else {
					this.mBtScoreFind.setBounds(0, 190, 120, 25);
					this.mBtScoreAdd.setBounds(0, 215, 120, 25);
					this.mBtScoreInput.setBounds(0, 240, 120, 25);
					this.mBtScoreFind.setVisible(false);
					this.mBtScoreAdd.setVisible(false);
					this.mBtScoreInput.setVisible(false);
					
					this.mBt3.setBounds(0, 190, 120, 30);
					this.mBt4.setBounds(0, 220, 120, 30);
					this.b2 = false;
				}
			}
		});
		
		this.mBtInput.addActionListener(e -> {
			new SelectUserDataDialog(this, x, y).setVisible(true);
		});
		this.mBtFind.addActionListener(e -> {
			new FindUserDataDialog(this, x, y).setVisible(true);
		});
		this.mBtIncreate.addActionListener(e -> {
			new InCreateUserDataDialog(this, x, y).setVisible(true);
		});
		this.mBtUpdate.addActionListener(e -> {
			new ManageUpdateUserDataDialog(this, x, y).setVisible(true);
		});
		this.mBtScoreInput.addActionListener(e -> {
			new UpdateScoreDialog(this, x, y).setVisible(true);
		});
		this.mBtScoreFind.addActionListener(e -> {
			new FindScoreDialog(this, x, y).setVisible(true);
		});
		this.mBtScoreAdd.addActionListener(e -> {
			new CountScoreDialog(this, x, y).setVisible(true);
		});
	}

	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorderPainted(false);
		this.mBtMinimize.setRolloverIcon(this.iL.getImageButtonMinimize1());
		this.mBtMinimize.setBorderPainted(false);
		
		this.mBtInput.setFont(new Font("宋体",0,12));
		this.mBtFind.setFont(new Font("宋体",0,12));
		this.mBtIncreate.setFont(new Font("宋体",0,12));
		this.mBtUpdate.setFont(new Font("宋体",0,12));
		this.mBtScoreAdd.setFont(new Font("宋体",0,12));
		this.mBtScoreFind.setFont(new Font("宋体",0,12));
		this.mBtScoreInput.setFont(new Font("宋体",0,12));
	}


	private void mouseBuild() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
			public void mouseEntered(MouseEvent e) {
				xOld = e.getX();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				x = xOnScreen - xOld;
				y = yOnScreen - yOld;
				setLocation(xx, yy);
			}
		});
	}

	private void viewBuild() {
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
		setBounds(470, 220 ,600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void buttonListenerImpl(int port) {
		switch (port) {
			case 0:
				System.exit(0);
				break;

			case 1:
				this.setState(JFrame.ICONIFIED);
				break;
				
			default:
				break;
		}
	}
}
