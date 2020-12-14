package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dao.UserDao;
import impl.UserDaoImpl;
import model.ImageList;
import model.Score;
import model.User;
import util.GetCodeUtil;
import util.GetRandomColorAndFont;
import util.MyMouseLambda;
import util.ScoreSortUtil;
import util.SetTextArea;
import util.StringUtil;

@SuppressWarnings("serial")
public class LogView extends JFrame {

	private int xOld = 0,yOld = 0;
	
	private JButton mBtExit,mBtMinimize,mBtLog,mBtForget;
	private JTextField mText1,mText2,mText3;
	private JTextPane mAreaCode;
	private JCheckBox checkBox;
	private JLabel mL1,mL2,mLNum;
	
	private ImageList iL = new ImageList(this.getClass());
	
	private int add = 0;
	
	private UserDao userDao = null;
	
	public static void main(String[] args) {
		new LogView().setVisible(true);
		
	}

	public LogView() {
		viewBuild();
		mouseBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
		getCode();
	}


	private void controlDefine() {
		this.mBtExit = new JButton(this.iL.getImageButtonExit0());
		this.mBtMinimize = new JButton(this.iL.getImageButtonMinimize0());
		this.mText1 = new JTextField();
		this.mText2 = new JTextField();
		this.mText3 = new JTextField();
		this.mAreaCode = new JTextPane();
		this.checkBox = new JCheckBox("管理员");
		this.mBtForget = new JButton();
		this.mBtLog = new JButton();
		this.mL1 = new JLabel();
		this.mLNum = new JLabel();
		this.mL2 = new JLabel();
		
	}

	private void controlBuild() {
		this.mBtExit.setBounds(400, 0, 30, 30);
		this.mBtMinimize.setBounds(370, 0, 30, 30);
		this.mText1.setBounds(115, 80, 200, 30);
		this.mText2.setBounds(115, 115, 200, 30);
		this.mText3.setBounds(115, 150, 100, 30);
		this.mAreaCode.setBounds(229, 150, 85, 29);
		this.mAreaCode.setBackground(new Color(13421772));
		this.mAreaCode.setEditable(false);
		this.checkBox.setBounds(135, 195, 80, 20);
		this.checkBox.setFont(new Font("宋体",0,12));
		this.mBtForget.setBounds(249, 195, 90, 15);
		this.mBtForget.setFont(new Font("宋体",0,12));
		this.mBtForget.setForeground(Color.BLUE);
		this.mBtForget.setText("忘记密码");
		this.mBtLog.setBounds(115, 225, 200, 30);
		this.mBtLog.setBackground(Color.LIGHT_GRAY);
		this.mBtLog.setText("登陆");
		this.mL1.setBounds(180, 300, 80, 20);
		this.mL1.setFont(new Font("宋体",0,11));
		this.mL1.setText("在线人数");
		this.mLNum.setBounds(230, 300, 80, 20);
		this.mLNum.setFont(new Font("宋体",0,11));
		this.mLNum.setForeground(Color.BLUE);
		this.mLNum.setText("236");
		this.mL2.setBounds(170, 320, 160, 20);
		this.mL2.setFont(new Font("宋体",0,11));
		this.mL2.setText("@2020 归作者所有");
		
		add(this.mBtExit);
		add(this.mBtMinimize);
		add(this.mText1);
		add(this.mText2);
		add(this.mText3);
		add(this.mAreaCode);
		add(this.checkBox);
		add(this.mBtForget);
		add(this.mBtLog);
		add(this.mL1);
		add(this.mLNum);
		add(this.mL2);
	}
	
	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorderPainted(false);
		this.mBtMinimize.setRolloverIcon(this.iL.getImageButtonMinimize1());
		this.mBtMinimize.setBorderPainted(false);
		this.mText1.setToolTipText("请输入学号");
		this.mText2.setToolTipText("请输入密码");
		this.mText3.setToolTipText("请输入验证码");
		this.mAreaCode.setToolTipText("点击更换");
		this.mBtForget.setContentAreaFilled(false);
		this.mBtForget.setBorder(null);
		this.checkBox.setContentAreaFilled(false);
		this.checkBox.setBorder(null);
	}
	
	/**
	 * 鼠标拖动事件
	 * 获取鼠标拖动应用窗口拖动
	 */
	
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
				setLocation(xx, yy);
			}
		});
	}

	private void viewBuild() {
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
		setBounds(470, 220 ,430, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			buttonListenerImpl(0);
		});
		this.mBtMinimize.addActionListener(e -> {
			buttonListenerImpl(1);
		});
		this.mAreaCode.addMouseListener((MyMouseLambda)(e) -> {
			getCode();
		});
		this.mBtForget.addActionListener(e -> {
			new FindPasswordDialog(this).setVisible(true);
		});
		this.mBtLog.addActionListener(e -> {
			if(StringUtil.isNull(this.mText1.getText())) {
				JOptionPane.showMessageDialog(this, "账号不能为空！");
				return;
			}
			if(!StringUtil.isNumberString(this.mText1.getText())) {
				JOptionPane.showMessageDialog(this, "账号输入错误！");
				clearText(this.mText1);
				clearText(this.mText2);
				clearText(this.mText3);
				getCode();
				return;
			}
			if(StringUtil.isNull(this.mText2.getText())) {
				JOptionPane.showMessageDialog(this, "密码不能为空！");
				return;
			}
			if(StringUtil.isNull(this.mText3.getText())) {
				JOptionPane.showMessageDialog(this, "验证码不能为空！");
				return;
			}
			if(Integer.parseInt(this.mText3.getText()) != this.add) {
				JOptionPane.showMessageDialog(this, "验证码错误，请重新输入！");
				clearText(this.mText3);
				getCode();
				return;
			}
			this.userDao = new UserDaoImpl();
			User user = new User();
			User u = null;
			user.setNumber(this.mText1.getText());
			user.setPassword(this.mText2.getText());
			try {
				if(this.checkBox.isSelected()) {
					//管理员登陆
					u = this.userDao.findManage(user);
					if(u != null) {
						JOptionPane.showMessageDialog(this, "登陆成功!");
						clearText(this.mText2);
						clearText(this.mText3);
						getCode();
						new ManageMajorView(this.mText1.getText()).setVisible(true);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(this, "账号或密码错误，请重新输入!");
						clearText(this.mText2);
						clearText(this.mText3);
						getCode();
					}
				}else {
					//学生登陆
					u = this.userDao.findUser(user);
					if(u != null) {
						JOptionPane.showMessageDialog(this, "登陆成功!");
						clearText(this.mText2);
						clearText(this.mText3);
						getCode();
						new StudentMajorView(this.mText1.getText(),this).setVisible(true);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(this, "账号或密码错误，请重新输入!");
						clearText(this.mText2);
						clearText(this.mText3);
						getCode();
					}
				}
			}catch (Exception es) {
				es.printStackTrace();
			}
		});
	}
	
	
	private void getCode() {
		String codeStr[] = GetCodeUtil.getRandom();
		int i = 0;
		this.mAreaCode.setText("");
		for(String s : codeStr) {
			if(i == 4) {
				this.add = Integer.parseInt(s);
				SetTextArea.setInfoWindosFont(this.mAreaCode,"?",GetRandomColorAndFont.getColor(),GetRandomColorAndFont.getFontBold(),GetRandomColorAndFont.getFontSize());
			}else {
				SetTextArea.setInfoWindosFont(this.mAreaCode,s,GetRandomColorAndFont.getColor(),GetRandomColorAndFont.getFontBold(),GetRandomColorAndFont.getFontSize());
			}
			i++;
		}
	}


	private void clearText(JTextField mText) {
		mText.setText("");
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
