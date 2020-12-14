package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.StudentDao;
import impl.StudentDaoImpl;
import model.ImageList;
import model.Student;
import util.StringUtil;

@SuppressWarnings("serial")
public class StudentMajorView extends JFrame {

	private String userNumber;
	private JFrame FFrame = null;
	private int xOld = 0,yOld = 0;
	private int x = 470, y = 220;
	
	private JButton mBtExit,mBtMinimize;
	private JLabel mL1,mL2,mL3,mL4;
	private JLabel mLName,mLText1,mLTime,mLTitle;
	private JButton mBt1,mBt2,mBt3;
	private JButton mBtModel1,mBtModel2;
	private JButton mBtUser,mBtCourse,mBtUpdateData;
	
	private ImageList iL = new ImageList(this.getClass());
	private Date nowTime = null; //更新时间
	private String timef = null;
	private StudentDao studentDao = null;
	
	public StudentMajorView(String userNumber,JFrame frame) {
		this.userNumber = userNumber;
		this.FFrame = frame;
		this.nowTime = new Date();
		this.timef = String.format("%tY-%tm-%td  %tH:%tM:%tS",this.nowTime,this.nowTime,this.nowTime,this.nowTime,this.nowTime,this.nowTime);
		viewBuild();
		listenerData();
		mouseBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
		setData();
	}


	private void setData() {
		Student student = new Student();
		student.setNumber(userNumber);
		this.studentDao = new StudentDaoImpl();
		Student s;
		try {
			s = this.studentDao.findOne(student);
			if(s != null) {
				this.mLName.setText(s.getName());
			}else {
				JOptionPane.showMessageDialog(this, "程序出现严重错误，请联系作者！错误：xx0000");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void controlDefine() {
		this.mBtExit = new JButton(this.iL.getImageButtonExit0());
		this.mBtMinimize = new JButton(this.iL.getImageButtonMinimize0());
		this.mL1 = new JLabel();
		this.mL2 = new JLabel();
		this.mL3 = new JLabel();
		this.mL4 = new JLabel();
		this.mLName = new JLabel();
		this.mLText1 = new JLabel();
		this.mLTime = new JLabel();
		this.mBt1 = new JButton();
		this.mBt2 = new JButton();
		this.mBt3 = new JButton();
		this.mBtModel1 = new JButton();
		this.mBtModel2 = new JButton();
		this.mLTitle = new JLabel("主页",JLabel.CENTER);
		this.mBtUser = new JButton();
		this.mBtCourse = new JButton();
		this.mBtUpdateData = new JButton();
	}
	
	private void controlBuild() {
		this.mBtExit.setBounds(570, 0, 30, 30);
		this.mBtMinimize.setBounds(540, 0, 30, 30);
		this.mL1.setBounds(0, 30, 600, 1);
		this.mL1.setOpaque(true);
		this.mL1.setBackground(Color.BLACK);
		this.mL2.setBounds(0, 95, 600, 1);
		this.mL2.setOpaque(true);
		this.mL2.setBackground(Color.BLACK);
		this.mL3.setBounds(120, 95, 1, 310);
		this.mL3.setOpaque(true);
		this.mL3.setBackground(Color.BLACK);
		this.mLTitle.setBounds(0, 96, 120, 35);
		this.mLTitle.setOpaque(true);
		this.mLTitle.setBackground(new Color(14670812));
		this.mLTitle.setFont(new Font("宋体",1,20));
		this.mL4.setBounds(0, 131, 120, 1);
		this.mL4.setOpaque(true);
		this.mL4.setBackground(Color.BLACK);
		this.mLName.setBounds(25, 40, 100, 20);
		this.mLName.setFont(new Font("宋体",0,12));
		this.mLName.setText("姓名");
		this.mLText1.setBounds(400, 40, 80, 20);
		this.mLText1.setFont(new Font("宋体",0,12));
		this.mLText1.setText("登录时间:");
		this.mLTime.setBounds(460, 40, 200, 20);
		this.mLTime.setFont(new Font("宋体",0,12));
		this.mLTime.setText(this.timef);
		this.mBt1.setBounds(20, 65, 60, 20);
		this.mBt1.setFont(new Font("宋体",0,12));
		this.mBt1.setForeground(Color.BLUE);
		this.mBt1.setText("修改密码");
		this.mBt2.setBounds(75, 65, 40, 20);
		this.mBt2.setFont(new Font("宋体",0,12));
		this.mBt2.setForeground(Color.BLUE);
		this.mBt2.setText("设置");
		this.mBt3.setBounds(108, 65, 40, 20);
		this.mBt3.setFont(new Font("宋体",0,12));
		this.mBt3.setForeground(Color.BLUE);
		this.mBt3.setText("注销");
		this.mBtModel1.setBounds(270, 70, 125, 24);
		this.mBtModel1.setBackground(new Color(14670812));
		this.mBtModel1.setFont(new Font("宋体",1,12));
		this.mBtModel1.setText("业务办理");
		this.mBtModel2.setBounds(400, 70, 125, 24);
		this.mBtModel2.setBackground(new Color(14670812));
		this.mBtModel2.setFont(new Font("宋体",1,12));
		this.mBtModel2.setText("信息查询");
		this.mBtUser.setBounds(0, 132, 120, 25);
		this.mBtUser.setText("个人信息");
		this.mBtUser.setVisible(false);
		this.mBtCourse.setBounds(0, 157, 120, 25);
		this.mBtCourse.setText("课程信息");
		this.mBtCourse.setVisible(false);
		this.mBtUpdateData.setBounds(0, 132, 120, 25);
		this.mBtUpdateData.setText("信息更改申请");
		this.mBtUpdateData.setVisible(false);
		
		add(this.mBtExit);
		add(this.mBtMinimize);
		add(this.mL1);
		add(this.mL2);
		add(this.mL3);
		add(this.mLTitle);
		add(this.mL4);
		add(this.mLName);
		add(this.mLText1);
		add(this.mLTime);
		add(this.mBt1);
		add(this.mBt2);
		add(this.mBt3);
		add(this.mBtModel1);
		add(this.mBtModel2);
		add(this.mBtUser);
		add(this.mBtCourse);
		add(mBtUpdateData);
	}
	
	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			buttonListenerImpl(0);
		});
		this.mBtMinimize.addActionListener(e -> {
			buttonListenerImpl(1);
		});
		
		this.mBtModel1.addActionListener(e -> {
			this.mLTitle.setText("业务办理");
			this.mBtCourse.setVisible(false);
			this.mBtUser.setVisible(false);
			this.mBtUpdateData.setVisible(true);
		});
		this.mBtModel2.addActionListener(e -> {
			this.mLTitle.setText("信息查询");
			this.mBtUpdateData.setVisible(false);
			this.mBtUser.setVisible(true);
			this.mBtCourse.setVisible(true);
		});
		this.mBtUser.addActionListener(e -> {
			new UserDataDialog(this, x, y,this.userNumber).setVisible(true);;
		});
		this.mBtCourse.addActionListener(e -> {
			new UserCourseDialog(this, x, y,this.userNumber).setVisible(true);
		});
		this.mBtUpdateData.addActionListener(e -> {
			new UpdateUserDataDialog(this, x, y,this.userNumber).setVisible(true);
		});
		this.mBt1.addActionListener(e -> {
			new UpdatePasswordDialog(this, x, y).setVisible(true);
		});
		this.mBt2.addActionListener(e -> {
			new InstallDialog(this, x, y).setVisible(true);
		});
		this.mBt3.addActionListener(e -> {
			int n = JOptionPane.showConfirmDialog(this, "是否注销该账号?", null, JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION) {
				setVisible(false);
				if(this.FFrame != null) {
					this.FFrame.setVisible(true);
				}
			}
		});
	}

	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorderPainted(false);
		this.mBtMinimize.setRolloverIcon(this.iL.getImageButtonMinimize1());
		this.mBtMinimize.setBorderPainted(false);
		this.mBt1.setContentAreaFilled(false);
		this.mBt1.setBorder(null);
		this.mBt2.setContentAreaFilled(false);
		this.mBt2.setBorder(null);
		this.mBt3.setContentAreaFilled(false);
		this.mBt3.setBorder(null);
		this.mBtUser.setContentAreaFilled(false);
		this.mBtCourse.setContentAreaFilled(false);
		this.mBtUpdateData.setContentAreaFilled(false);
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
	
	private void listenerData() {
		Student student = new Student();
		student.setNumber(this.userNumber);
		this.studentDao = new StudentDaoImpl();
		Student s = null;
		try {
			s = this.studentDao.findOne(student);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if("null".equals(s.getIdentity())) {
			this.setVisible(false);
			JOptionPane.showMessageDialog(this, "请完善你的个人信息");
			String identity = "";
			String address = "";
			String phone = "";
			while(true) {
				identity = JOptionPane.showInputDialog(this,"输入身份证","完善信息1",JOptionPane.PLAIN_MESSAGE);
				if(null == identity) {
					if(JOptionPane.showConfirmDialog(this, "是否跳过?",null,JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						break;
					}
				}
				if(StringUtil.isNull(identity)) {
					JOptionPane.showMessageDialog(this, "身份证不能为空!");
					continue;
				}else {
					if(!StringUtil.isNumberString(identity)) {
						JOptionPane.showMessageDialog(this, "身份证输入错误!");
						continue;
					}
				}
				
				address = JOptionPane.showInputDialog(this,"输入地址","完善信息2",JOptionPane.PLAIN_MESSAGE);
				if(null == address) {
					if(JOptionPane.showConfirmDialog(this, "是否跳过?",null,JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						break;
					}
				}
				if(StringUtil.isNull(address)) {
					JOptionPane.showMessageDialog(this, "地址不能为空!");
					continue;
				}else {
					if(null == address) {
						JOptionPane.showMessageDialog(this, "地址输入错误!");
						continue;
					}
				}
				
				phone = JOptionPane.showInputDialog(this,"输入电话号码","完善信息3",JOptionPane.PLAIN_MESSAGE);
				if(null == phone) {
					if(JOptionPane.showConfirmDialog(this, "是否跳过?",null,JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						break;
					}
				}
				if(StringUtil.isNull(phone)) {
					JOptionPane.showMessageDialog(this, "电话号码不能为空!");
					continue;
				}else {
					if(!StringUtil.isNumberString(phone)) {
						JOptionPane.showMessageDialog(this, "电话号码输入错误!");
						continue;
					}
				}
				break;
			}
			if(null != phone) {
				this.studentDao = new StudentDaoImpl();
				Student stu = new Student();
				stu.setNumber(this.userNumber);
				stu.setIdentity(identity);
				stu.setAddress(address);
				stu.setPhone(phone);
				int num;
				try {
					num = this.studentDao.updateStudents(stu);
					if(num == 0) {
						JOptionPane.showMessageDialog(this, "程序出现严重错误，请联系作者！错误：xx0001");
						System.exit(0);
					}
					JOptionPane.showMessageDialog(this, "登陆成功!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
