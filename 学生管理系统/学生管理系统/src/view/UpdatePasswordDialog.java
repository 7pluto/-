package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.UserDao;
import impl.UserDaoImpl;
import model.ImageList;
import model.User;
import util.StringUtil;

@SuppressWarnings("serial")
public class UpdatePasswordDialog extends JDialog {

	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JLabel mLb1,mLb2,mLb3,mLb4;
	private JTextField mText1,mText2,mText3,mText4;
	private JButton mBt;

	private UserDao userDao = null;
	
	public UpdatePasswordDialog(JFrame f,int x,int y) {
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
		this.mLb1 = new JLabel();
		this.mLb2 = new JLabel();
		this.mLb3 = new JLabel();
		this.mLb4 = new JLabel();
		this.mText1 = new JTextField();
		this.mText2 = new JTextField();
		this.mText3 = new JTextField();
		this.mText4 = new JTextField();
		this.mBt = new JButton();
	}

	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		this.mLb1.setBounds(110,50,100,30);
		this.mLb1.setText("账号:");
		this.mLb2.setBounds(100,90,100,30);
		this.mLb2.setText("原密码:");
		this.mLb3.setBounds(100,130,100,30);
		this.mLb3.setText("新密码:");
		this.mLb4.setBounds(75,170,100,30);
		this.mLb4.setText("确认新密码:");
		this.mText1.setBounds(180,50,200,30);
		this.mText2.setBounds(180,90,200,30);
		this.mText3.setBounds(180,130,200,30);
		this.mText4.setBounds(180,170,200,30);
		this.mBt.setBounds(110,230,260,35);
		this.mBt.setBackground(Color.GRAY);
		this.mBt.setText("修改密码");
		
		add(this.mBtExit);
		add(this.mLb1);
		add(this.mLb2);
		add(this.mLb3);
		add(this.mLb4);
		add(this.mText1);
		add(this.mText2);
		add(this.mText3);
		add(this.mText4);
		add(this.mBt);
	}

	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorder(null);
	}

	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			setVisible(false);
		});
		this.mBt.addActionListener(e -> {
			if(StringUtil.isNull(this.mText1.getText())) {
				JOptionPane.showMessageDialog(this, "账号不能为空!");
				return;
			}
			if(StringUtil.isNull(this.mText2.getText())) {
				JOptionPane.showMessageDialog(this, "原密码不能为空!");
				return;
			}
			if(StringUtil.isNull(this.mText3.getText())) {
				JOptionPane.showMessageDialog(this, "新密码不能为空!");
				return;
			}
			if(StringUtil.isNull(this.mText4.getText())) {
				JOptionPane.showMessageDialog(this, "确认密码不能为空!");
				return;
			}
			if(!this.mText3.getText().equals(this.mText4.getText())) {
				JOptionPane.showMessageDialog(this, "密码和确认密码不一致!");
				clearText(this.mText3);
				clearText(this.mText4);
				return;
			}
			this.userDao = new UserDaoImpl();
			User user = new User();
			user.setNumber(this.mText1.getText());
			user.setPassword(this.mText2.getText());
			try {
				User u = this.userDao.findUser(user);
				if(u != null) {
					user = new User();
					user.setNumber(this.mText1.getText());
					user.setPassword(this.mText3.getText());
					int n = this.userDao.updateUser(user);
					if(n == 1) {
						JOptionPane.showMessageDialog(this, "修改成功!");
						setVisible(false);
					}
				}else {
					JOptionPane.showMessageDialog(this,"账号或密码错误!");
					clearText(this.mText1);
					clearText(this.mText2);
					clearText(this.mText3);
					clearText(this.mText4);
					return;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(this.x+121, this.y+96, 478, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
	
	private void clearText(JTextField mText) {
		mText.setText("");
	}
}
