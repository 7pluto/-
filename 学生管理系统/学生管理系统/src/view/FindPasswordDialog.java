package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dao.UserDao;
import impl.UserDaoImpl;
import model.ImageList;
import model.Student;
import model.User;
import util.GetRandomColorAndFont;
import util.MyMouseLambda;
import util.SetTextArea;
import util.StringUtil;

@SuppressWarnings("serial")
public class FindPasswordDialog extends JDialog {

	private ImageList iL = new ImageList(this.getClass());
	
	private JButton mBtExit = new JButton(iL.getImageButtonExitDialog());
	private JLabel mL1,mL2,mL3,mL4,mL5;
	private JTextField mText1,mText2,mText3;
	private JTextPane mAreaCode;
	private JButton mBt;
	
	private String codeStr = null;
	private UserDao userDao = null;
	
	public FindPasswordDialog(JFrame f) {
		super(f);
		viewBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
		getCode();
	}

	private void controlDefine() {
		this.mL1 = new JLabel();
		this.mL2 = new JLabel();
		this.mL3 = new JLabel();
		this.mL4 = new JLabel();
		this.mL5 = new JLabel();
		this.mText1 = new JTextField();
		this.mText2 = new JTextField();
		this.mText3 = new JTextField();
		this.mAreaCode = new JTextPane();
		this.mBt = new JButton();
	}

	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorder(null);
		this.mText3.setBorder(null);
		this.mAreaCode.setToolTipText("点击更换");
	}

	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			setVisible(false);
		});
		this.mAreaCode.addMouseListener((MyMouseLambda)(e) -> {
			getCode();
		});
		this.mBt.addActionListener(e -> {
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
				JOptionPane.showMessageDialog(this, "凭据不能为空！");
				return;
			}
			if(!StringUtil.isNumberString(this.mText2.getText())) {
				JOptionPane.showMessageDialog(this, "凭据输入错误！");
				clearText(this.mText2);
				clearText(this.mText3);
				getCode();
				return;
			}
			if(StringUtil.isNull(this.mText3.getText())) {
				JOptionPane.showMessageDialog(this, "验证码不能为空！");
				return;
			}
			if(!this.mText3.getText().toUpperCase().equals(this.codeStr)) {
				JOptionPane.showMessageDialog(this, "验证码错误，请重新输入！");
				clearText(this.mText3);
				getCode();
				return;
			}
			try {
				this.userDao = new UserDaoImpl();
				Student student = this.userDao.findUserCredential(this.mText1.getText(), this.mText2.getText());
				if(student != null) {
					String paww = StringUtil.getRandomPassword();
					User user = new User();
					user.setNumber(student.getNumber());
					user.setPassword(paww);
					int num = userDao.updateUser(user);
					if(num == 1) {
						JOptionPane.showMessageDialog(this, "找回成功，密码为:"+paww+",请及时更改密码!");
						setVisible(false);
					}
				}else {
					JOptionPane.showMessageDialog(this, "账号或凭据错误，请重新输入!");
					clearText(this.mText2);
					clearText(this.mText3);
					getCode();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	private void controlBuild() {
		this.mBtExit.setBounds(270, 0, 30, 30);
		this.mL1.setBounds(10, 0, 80, 30);
		this.mL1.setFont(new Font("宋体",0,13));
		this.mL1.setText("找回密码");
		this.mL2.setBounds(60, 50, 60, 30);
		this.mL2.setFont(new Font("宋体",0,13));
		this.mL2.setText("账号 :");
		this.mL3.setBounds(60, 90, 60, 30);
		this.mL3.setFont(new Font("宋体",0,13));
		this.mL3.setText("凭据 :");
		this.mL4.setBounds(48, 130, 60, 30);
		this.mL4.setFont(new Font("宋体",0,13));
		this.mL4.setText("验证码 :");
		this.mText1.setBounds(105, 50, 150, 30);
		this.mText2.setBounds(105, 90, 150, 30);
		this.mText3.setBounds(105, 130, 75, 30);
		this.mAreaCode.setBounds(180, 130, 75, 30);
		this.mAreaCode.setEditable(false);
		this.mAreaCode.setBackground(new Color(13421772));
		this.mL5.setBounds(10, 180, 200, 20);
		this.mL5.setFont(new Font("宋体",0,13));
		this.mL5.setText("凭据默认为身份证");
		this.mBt.setBounds(220, 180, 60, 30);
		this.mBt.setBackground(Color.BLUE);
		this.mBt.setForeground(Color.WHITE);
		this.mBt.setFont(new Font("宋体",0,13));
		this.mBt.setText("找回");
		
		add(this.mBtExit);
		add(this.mL1);
		add(this.mL2);
		add(this.mL3);
		add(this.mL4);
		add(this.mText1);
		add(this.mText2);
		add(this.mText3);
		add(this.mAreaCode);
		add(this.mL5);
		add(this.mBt);
	}

	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(540, 270, 300, 220);
		getContentPane().setBackground(new Color(15658734));
		setUndecorated(true);
	}
	
	private void getCode() {
		String str = StringUtil.getRandomString(4);
		this.codeStr = str.toUpperCase();
		char codeStr[] = str.toCharArray();
		this.mAreaCode.setText("");
		for(char s : codeStr) {
			SetTextArea.setInfoWindosFont(this.mAreaCode,String.valueOf(s),GetRandomColorAndFont.getColor(),GetRandomColorAndFont.getFontBold(),GetRandomColorAndFont.getFontSize());
		}
	}
	
	private void clearText(JTextField mText) {
		mText.setText("");
	}
}
