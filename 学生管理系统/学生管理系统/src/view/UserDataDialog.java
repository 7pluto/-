package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.StudentDao;
import impl.StudentDaoImpl;
import model.ImageList;
import model.Student;

@SuppressWarnings("serial")
public class UserDataDialog extends JDialog {

	@SuppressWarnings("unused")
	private String userNumber;
	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JLabel mLbNum,mLbName,mLbSex,mLbId,mLbAddress,mLbPhone,mLbClass,mLbGrade;
	private JLabel mLbNum1,mLbName1,mLbSex1,mLbId1,mLbAddress1,mLbPhone1,mLbClass1,mLbGrade1;
	private JLabel mImage;
	
	private StudentDao studentDao = null;
	
	public UserDataDialog(JFrame f,int x,int y,String userNumber) {
		super(f);
		this.x = x;
		this.y = y;
		this.userNumber = userNumber;
		viewBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
		setData();
	}

	private void setData() {
		Student student = new Student();
		student.setNumber("3118000800");
		this.studentDao = new StudentDaoImpl();
		Student s = null;
		try {
			s = this.studentDao.findOne(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(s != null) {
			this.mLbNum1.setText("3118000800");
			this.mLbName1.setText(s.getName());
			this.mLbSex1.setText(s.getSex());
			this.mLbId1.setText(s.getIdentity());
			this.mLbAddress1.setText(s.getAddress());
			this.mLbClass1.setText(s.getClassed());
			this.mLbPhone1.setText(s.getPhone());
			this.mLbGrade1.setText(s.getGrade());
		}else {
			JOptionPane.showMessageDialog(this, "程序出现严重错误，请联系作者！错误：xx000");
		}
	}

	private void controlDefine() {
		this.mLbNum = new JLabel();
		this.mLbName = new JLabel();
		this.mLbSex = new JLabel();
		this.mLbId = new JLabel();
		this.mLbAddress = new JLabel();
		this.mLbClass = new JLabel();
		this.mLbPhone = new JLabel();
		this.mLbGrade = new JLabel();
		this.mImage = new JLabel();
		this.mLbNum1 = new JLabel();
		this.mLbName1 = new JLabel();
		this.mLbSex1 = new JLabel();
		this.mLbId1 = new JLabel();
		this.mLbAddress1 = new JLabel();
		this.mLbClass1 = new JLabel();
		this.mLbPhone1 = new JLabel();
		this.mLbGrade1 = new JLabel();
	}

	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		this.mLbNum.setBounds(80, 50, 100, 30);
		this.mLbNum.setText("学号:");
		this.mLbName.setBounds(80, 75, 100, 30);
		this.mLbName.setText("姓名:");
		this.mLbSex.setBounds(80, 100, 100, 30);
		this.mLbSex.setText("性别:");
		this.mLbId.setBounds(55, 125, 100, 30);
		this.mLbId.setText("身份证号:");
		this.mLbAddress.setBounds(80, 200, 100, 30);
		this.mLbAddress.setText("地址:");
		this.mLbPhone.setBounds(80, 225, 100, 30);
		this.mLbPhone.setText("电话:");
		this.mLbClass.setBounds(80, 150, 100, 30);
		this.mLbClass.setText("班级:");
		this.mLbGrade.setBounds(80, 175, 100, 30);
		this.mLbGrade.setText("年级:");
		this.mImage.setBounds(280, 70, 120, 170);
		this.mImage.setOpaque(true);
		this.mImage.setBackground(Color.lightGray);
		
		this.mLbNum1.setBounds(120, 50, 100, 30);
		this.mLbName1.setBounds(120, 75, 100, 30);
		this.mLbSex1.setBounds(120, 100, 100, 30);
		this.mLbId1.setBounds(120, 125, 200, 30);
		this.mLbAddress1.setBounds(120, 200, 100, 30);
		this.mLbPhone1.setBounds(120, 225, 100, 30);
		this.mLbClass1.setBounds(120, 150, 100, 30);
		this.mLbGrade1.setBounds(120, 175, 100, 30);
		
		add(this.mBtExit);
		add(this.mLbNum);
		add(this.mLbName);
		add(this.mLbSex);
		add(this.mLbId);
		add(this.mLbAddress);
		add(this.mLbPhone);
		add(this.mLbClass);
		add(this.mLbGrade);
		add(this.mImage);
		add(this.mLbNum1);
		add(this.mLbName1);
		add(this.mLbSex1);
		add(this.mLbId1);
		add(this.mLbAddress1);
		add(this.mLbPhone1);
		add(this.mLbClass1);
		add(this.mLbGrade1);
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
		setBounds(this.x+121, this.y+96, 479, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
