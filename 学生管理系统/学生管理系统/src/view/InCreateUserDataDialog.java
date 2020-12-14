package view;

import java.awt.Color;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.ClassedDao;
import dao.CourseDao;
import dao.ScoreDao;
import dao.StudentDao;
import dao.UserDao;
import impl.ClassedDaoImpl;
import impl.CourseDaoImpl;
import impl.ScoreDaoImpl;
import impl.StudentDaoImpl;
import impl.UserDaoImpl;
import model.Classed;
import model.ImageList;
import model.Score;
import model.Student;
import model.User;
import util.StringUtil;

@SuppressWarnings("serial")
public class InCreateUserDataDialog extends JDialog {

	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	
	private JLabel mLbNum,mLbName,mLbSex1,mLbSex2,mLbGrade,mLbClass;
	private JTextField mTextNum,mTextName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton manJrb;
	private JRadioButton girJrb ;
	private JComboBox<String> mComboBoxModel,mComboBoxClass;
	private JButton mBt;
	
	private StudentDao studentDao = null;
	private UserDao userDao = null;
	private ScoreDao scoreDao = null;
	private CourseDao courseDao = null;
	private ClassedDao classedDao = null;
	
	public InCreateUserDataDialog(JFrame f,int x,int y) {
		super(f);
		this.x = x;
		this.y = y;
		viewBuild();
		controlDefine();
		controlBuild();
		getMComboBoxClassData();
		controlPrettify();
		controlListener();
	}


	private void controlDefine() {
		this.mLbNum = new JLabel();
		this.mLbName = new JLabel();
		this.mLbSex1 = new JLabel();
		this.mLbSex2 = new JLabel();
		this.mLbGrade = new JLabel();
		this.mLbClass = new JLabel();
		this.manJrb = new JRadioButton("\u7537");
		this.girJrb = new JRadioButton("\u5973");
		this.mTextNum = new JTextField();
		this.mTextName = new JTextField();
		this.mComboBoxModel = new JComboBox<String>();
		this.mComboBoxClass = new JComboBox<String>();
		this.mBt = new JButton();
	}


	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		this.mLbNum.setBounds(100, 60, 60, 30);
		this.mLbNum.setText("学号:");
		this.mLbName.setBounds(100, 100, 60, 30);
		this.mLbName.setText("姓名:");
		this.mLbSex1.setBounds(200, 140, 60, 30);
		this.mLbSex1.setText("男");
		add(this.mLbSex1);
		this.mLbSex2.setBounds(280, 140, 60, 30);
		this.mLbSex2.setText("女");
		add(this.mLbSex2);
		this.manJrb.setBounds(220, 145, 20, 20);
		this.manJrb.setBackground(Color.WHITE);
		this.manJrb.setSelected(true);
		this.girJrb.setBounds(300, 145, 20, 20);
		this.girJrb.setBackground(Color.WHITE);
		this.mLbGrade.setBounds(100, 170, 60, 30);
		this.mLbGrade.setText("年级:");
		this.mLbClass.setBounds(100, 210, 60, 30);
		this.mLbClass.setText("班级:");
		this.mTextNum.setBounds(160, 60, 200, 30);
		this.mTextName.setBounds(160, 100, 200, 30);
		this.mComboBoxModel.setBounds(160, 170, 200, 30);
		this.mComboBoxModel.setBackground(Color.WHITE);
		this.mComboBoxModel.addItem("请选择");
		this.mComboBoxModel.addItem("大一");
		this.mComboBoxModel.addItem("大二");
		this.mComboBoxModel.addItem("大三");
		this.mComboBoxModel.addItem("大四");
		this.mComboBoxClass.setBounds(160, 210, 200, 30);
		this.mComboBoxClass.setBackground(Color.WHITE);
		this.mBt.setBounds(160, 260, 160, 30);
		this.mBt.setBackground(Color.LIGHT_GRAY);
		this.mBt.setText("添加");
		
		add(this.mBtExit);
		add(this.mLbNum);
		add(this.mLbName);
		buttonGroup.add(manJrb);
		buttonGroup.add(girJrb);
		add(this.manJrb);
		add(this.girJrb);
		add(this.mLbGrade);
		add(this.mLbClass);
		add(this.mTextNum);
		add(this.mTextName);
		add(this.mComboBoxModel);
		add(this.mComboBoxClass);
		add(this.mBt);
	}


	private void getMComboBoxClassData() {
		this.classedDao = new ClassedDaoImpl();
		try {
			List<Classed> classeds = this.classedDao.findAll();
			for(Classed c : classeds) {
				this.mComboBoxClass.addItem(c.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			if(StringUtil.isNull(this.mTextName.getText())) {
				JOptionPane.showMessageDialog(this, "姓名不能为空!");
				return;
			}
			if(StringUtil.isNull(this.mTextNum.getText())) {
				JOptionPane.showMessageDialog(this, "学号不能为空!");
				return;
			}
			if(!StringUtil.isNumberString(this.mTextNum.getText())) {
				JOptionPane.showMessageDialog(this, "注意学号的格式!");
				this.mTextNum.setText("");
				return;
			}
			this.studentDao = new StudentDaoImpl();
			Student student = new Student();
			student.setNumber(this.mTextNum.getText());
			student.setName(this.mTextName.getText());
			if(this.manJrb.isSelected()) {
				student.setSex("男");
			}else {
				student.setSex("女");
			}
			student.setClassedInt(this.mComboBoxClass.getSelectedIndex()+1);
			student.setGradeInt(this.mComboBoxModel.getSelectedIndex());
			try {
				int num = this.studentDao.increateStudent(student);
				if(num == 1) {
					this.userDao = new UserDaoImpl();
					User user = new User();
					user.setNumber(this.mTextNum.getText());
					user.setPassword("123456");
					int num1 = this.userDao.inCrateUser(user);
					if(num1 == 1) {
						this.scoreDao = new ScoreDaoImpl();
						Score score = new Score();
						score.setNumber(this.mTextNum.getText());
						score.setScore(0);
						this.courseDao = new CourseDaoImpl();
						int add = this.courseDao.getAllNum();
						System.out.println(add);
						int num2 = scoreDao.inCreateScore(score, add);
						if(num2 == add) {
							JOptionPane.showMessageDialog(this, "添加成功！");
						}
					}
				}
			}catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "已经存在该学号学生！");
			}
		});
	}


	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(this.x+122, this.y+60, 478, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
