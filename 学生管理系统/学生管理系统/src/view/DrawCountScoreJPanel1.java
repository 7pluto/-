package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassedDao;
import dao.CourseDao;
import dao.CoursesDao;
import dao.ScoreDao;
import dao.StudentDao;
import impl.ClassedDaoImpl;
import impl.CourseDaoImpl;
import impl.CoursesDaoImpl;
import impl.ScoreDaoImpl;
import impl.StudentDaoImpl;
import model.Classed;
import model.Courses;
import model.Score;
import model.ScoreCount;
import model.Student;

@SuppressWarnings("serial")
public class DrawCountScoreJPanel1 extends JPanel {

	private JLabel mLb = null;
	private JComboBox<String> mComboBox = null;
	private JButton mBt = null,mBtGatData1 = null,mBtGatData2 = null;
	private JComboBox<String> mComboBoxGrade = null,mComboBoxClass = null,mComboBoxCourse = null;
	private JLabel mLbMax = null,mLbMin = null,mLbAvg = null;
	private JLabel mLbMax1 = null,mLbMin1 = null,mLbAvg1 = null;
	private JScrollPane mS;
	private JTable mTable;
	
	//表头（列名）
    Object[] columnNames = null;
    // 表格所有行数据
    Object[][] rowData = null;
    private ScoreDao scoreDao = null;
    private CourseDao courseDao = null;
    private StudentDao studentDao = null;
    private CoursesDao coursesDao = null;
    boolean[] columnEditables = null;
	private ClassedDao classedDao = null;
	
	private int yx = 0,lh = 0,jg = 0,bjg = 0;
	private List<String> maxNumber,minNumber;
	
	public DrawCountScoreJPanel1(){
		setLayout(null);
		setBackground(Color.WHITE);
		controlDefine();
		controlBuild();
		getMComboBoxClassData();
		getMComboBoxCourseData();
		getDrawData();
		controlPrettify();
		controlListener();
	}

	private void getDrawData() {
		this.scoreDao = new ScoreDaoImpl();
		List<Score> scores = new ArrayList<Score>();
		ScoreCount scoreCountMax = null;
		ScoreCount scoreCountMin = null;
		double avg = 0;
		try {
			if(this.mComboBox.getSelectedIndex() == 0) {
				System.out.println("年级");
				scores = this.scoreDao.findAllGrade(this.mComboBoxGrade.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
				scoreCountMax = this.scoreDao.getGradeScoreMax(this.mComboBoxGrade.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
				scoreCountMin = this.scoreDao.getGradeScoreMin(this.mComboBoxGrade.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
				avg = this.scoreDao.getGradeScoreAvg(this.mComboBoxGrade.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
			}else {
				System.out.println("班级");
				scores = this.scoreDao.findAllClass(this.mComboBoxClass.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
				scoreCountMax = this.scoreDao.getClassScoreMax(this.mComboBoxClass.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
				scoreCountMin = this.scoreDao.getClassScoreMin(this.mComboBoxClass.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
				avg = this.scoreDao.getClassScoreAvg(this.mComboBoxClass.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
			}
		}catch (Exception e) {
			
		}
		getScoreGroup(scores);
		this.mLbMax1.setText(String.valueOf(scoreCountMax.getMax()));
		this.maxNumber = scoreCountMax.getNumber();
		this.mLbMin1.setText(String.valueOf(scoreCountMin.getMin()));
		this.minNumber = scoreCountMin.getNumber();
		this.mLbAvg1.setText(String.valueOf(avg));
		repaint();
	}

	private void getScoreGroup(List<Score> scores) {
		int num[] = new int[4];
		for(Score s : scores) {
			if(s.getScore() >=85) {
				num[0]++;
			}else if(s.getScore() <=84 && s.getScore() >=75) {
				num[1]++;
			}else if(s.getScore() <=74 && s.getScore() >=60) {
				num[2]++;
			}else {
				num[3]++;
			}
		}
		this.yx = DoubleToInt(scores.size(),num[0]);
		this.lh = DoubleToInt(scores.size(),num[1]);
		this.jg = DoubleToInt(scores.size(),num[2]);
		this.bjg = DoubleToInt(scores.size(),num[3]);
	}

	private int DoubleToInt(int size, int i) {
		double n = 1.0*360*(1.0*i/size);
		return (int) Math.round(n);
	}

	private void getMComboBoxCourseData() {
		this.coursesDao = new CoursesDaoImpl();
		try {
			List<Courses> courses = this.coursesDao.findAll();
			for(Courses c : courses) {
				this.mComboBoxCourse.addItem(c.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void controlDefine() {
		this.mLb = new JLabel();
		this.mComboBox = new JComboBox<String>();
		this.mBt = new JButton();
		this.mComboBoxGrade = new JComboBox<String>();
		this.mComboBoxClass = new JComboBox<String>();
		this.mComboBoxCourse = new JComboBox<String>();
		this.mLbMax = new JLabel();
		this.mLbMin = new JLabel();
		this.mLbAvg = new JLabel();
		this.mLbMax1 = new JLabel();
		this.mLbMin1 = new JLabel();
		this.mLbAvg1 = new JLabel();
		this.mBtGatData1 = new JButton();
		this.mBtGatData2 = new JButton();
	}

	private void controlBuild() {
		this.mLb.setBounds(80, 280, 50, 50);
		this.mLb.setFont(new Font("宋体",1,20));
		this.mLb.setForeground(Color.RED);
		this.mLb.setText("？");
		this.mComboBox.setBounds(520, 10, 120, 25);
		this.mComboBox.setBackground(Color.WHITE);
		this.mComboBox.addItem("年级");
		this.mComboBox.addItem("班级");
		this.mBt.setBounds(660, 10, 80, 25);
		this.mBt.setBackground(Color.lightGray);
		this.mBt.setText("统计");
		this.mComboBoxGrade.setBounds(520, 40, 120, 25);
		this.mComboBoxGrade.setBackground(Color.WHITE);
		this.mComboBoxGrade.addItem("大一");
		this.mComboBoxGrade.addItem("大二");
		this.mComboBoxGrade.addItem("大三");
		this.mComboBoxGrade.addItem("大四");
		this.mComboBoxGrade.setVisible(true);
		this.mComboBoxClass.setBounds(520, 40, 120, 25);
		this.mComboBoxClass.setBackground(Color.WHITE);
		this.mComboBoxClass.setVisible(false);
		this.mComboBoxCourse.setBounds(520, 70, 120, 25);
		this.mComboBoxCourse.setBackground(Color.WHITE);
		this.mLbMax.setBounds(540, 220, 100, 30);
		this.mLbMax.setText("最高分:");
		this.mLbMin.setBounds(540, 260, 100, 30);
		this.mLbMin.setText("最低分:");
		this.mLbAvg.setBounds(540, 300, 100, 30);
		this.mLbAvg.setText("平均分:");
		this.mLbMax1.setBounds(600, 220, 100, 30);
		this.mLbMax1.setText("0.0");
		this.mLbMin1.setBounds(600, 260, 100, 30);
		this.mLbMin1.setText("0.0");
		this.mLbAvg1.setBounds(600, 300, 100, 30);
		this.mLbAvg1.setText("0.0");
		this.mBtGatData1.setBounds(660, 225, 80, 20);
		this.mBtGatData1.setBackground(Color.lightGray);
		this.mBtGatData1.setFont(new Font("宋体",0,10));
		this.mBtGatData1.setText("学生信息");
		this.mBtGatData2.setBounds(660, 265, 80, 20);
		this.mBtGatData2.setBackground(Color.lightGray);
		this.mBtGatData2.setFont(new Font("宋体",0,10));
		this.mBtGatData2.setText("学生信息");
		
		add(this.mLb);
		add(this.mComboBox);
		add(this.mBt);
		add(this.mComboBoxGrade);
		add(this.mComboBoxClass);
		add(this.mComboBoxCourse);
		add(this.mLbMax);
		add(this.mLbMin);
		add(this.mLbAvg);
		add(this.mLbMax1);
		add(this.mLbMin1);
		add(this.mLbAvg1);
		add(this.mBtGatData1);
		add(this.mBtGatData2);
	}

	private void controlPrettify() {
		this.mLb.setToolTipText("<html>优秀 : 100>=&&>=85<br>良好 : 84>=&&>=75<br>合格 : 74>=&&>=60<br>不合格 : 59>=&&>=0<br>");
	}

	private void controlListener() {
		this.mComboBox.addActionListener(e -> {
			if(this.mComboBox.getSelectedIndex() == 1) {
				this.mComboBoxGrade.setVisible(false);
				this.mComboBoxClass.setVisible(true);
			}else {
				this.mComboBoxClass.setVisible(false);
				this.mComboBoxGrade.setVisible(true);
			}
		});
		this.mBt.addActionListener(e -> {
			getDrawData();
		});
		this.mBtGatData1.addActionListener(e -> {
			setData(this.maxNumber);
			setTable();
		});
		this.mBtGatData2.addActionListener(e -> {
			setData(this.minNumber);
			setTable();
		});
	}

	private void setTable() {
		this.mTable = new JTable();
		this.mTable.setModel(new DefaultTableModel(rowData,columnNames) {
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.mS = new JScrollPane(this.mTable);
		
		this.mTable.getTableHeader().setBounds(20, 320, 440, 30);
		this.mS.setBounds(20, 380, 720, 100);
		
		add(this.mTable.getTableHeader());
		add(this.mS);
	}

	private void setData(List<String> numbers) {
		this.scoreDao = new ScoreDaoImpl();
		this.courseDao = new CourseDaoImpl();
		this.studentDao = new StudentDaoImpl();
		Student stu = null;
		List<Student> student = new ArrayList<Student>();
		try {
			for(String num : numbers) {
				stu = new Student();
				stu.setNumber(num);
				student.add(this.studentDao.findOne(stu));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		int courseNum = 0;
		try {
			courseNum = this.courseDao.getAllNum();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.columnEditables = new boolean[courseNum+2];
		for(int b = 0;b < courseNum+2;b++) {
			this.columnEditables[b] = false;
		}
		try {
			Object[][] rowDatas = this.scoreDao.getManagerFindStudentScore(student, courseNum);
			this.rowData = new Object[student.size()][courseNum+2];
			int n = 0;
			for(Student s : student) {
				this.rowData[n][0] = s.getNumber();
				this.rowData[n][1] = s.getName();
				n++;
			}
			for(int m = 0;m < student.size();m++) {
				for(int k = 2;k < courseNum+2;k++) {
					this.rowData[m][k] = rowDatas[m][k-2];
				}
			}
			this.coursesDao = new CoursesDaoImpl();
			List<Courses> coursesList = this.coursesDao.findAll();
			this.columnNames = new Object[coursesList.size()+2];
			this.columnNames[0] = "学号";
			this.columnNames[1] = "姓名";
			int i = 2;
			for(Courses c : coursesList) {
				this.columnNames[i++] = c.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillArc(150, 50, 200, 200, 30, this.yx);
		g.setColor(Color.GREEN);
		g.fillArc(150, 50, 200, 200, 30+this.yx, this.lh);
		g.setColor(Color.YELLOW);
		g.fillArc(150, 50, 200, 200, 30+this.yx+this.lh, this.jg);
		g.setColor(new Color(0, 210, 255));
		g.fillArc(150, 50, 200, 200, 30+this.yx+this.lh+this.jg, this.bjg);
		g.setColor(Color.BLACK);
		g.drawString(100.0*this.yx/360+"%", 135, 290);
		g.drawString(100.0*this.lh/360+"%", 205, 290);
		g.drawString(100.0*this.jg/360+"%", 275, 290);
		g.drawString(100.0*this.bjg/360+"%", 345, 290);
		g.drawString("优秀", 135, 310);
		g.drawString("良好", 205, 310);
		g.drawString("合格", 275, 310);
		g.drawString("不合格", 345, 310);
		g.setColor(Color.RED);
		g.fillRect(115, 303, 10, 6);
		g.setColor(Color.GREEN);
		g.fillRect(185, 303, 10, 6);
		g.setColor(Color.YELLOW);
		g.fillRect(255, 303, 10, 6);
		g.setColor(new Color(0, 210, 255));
		g.fillRect(315, 303, 10, 6);
	}
}
