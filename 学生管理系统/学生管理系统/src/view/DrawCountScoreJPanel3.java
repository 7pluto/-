package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import model.Student;
import util.ScoreSortUtil;

@SuppressWarnings("serial")
public class DrawCountScoreJPanel3 extends JPanel {

	
	private JComboBox<String> mComboBox = null;
	private JButton mBt = null;
	private JComboBox<String> mComboBoxGrade = null,mComboBoxClass = null,mComboBoxCourse = null;
	private JScrollPane mS;
	private JTable mTable;
	
	//表头（列名）
    Object[] columnNames = null;
    // 表格所有行数据
    Object[][] rowData = null;
    private ScoreDao scoreDao = null;
    @SuppressWarnings("unused")
	private CourseDao courseDao = null;
    private StudentDao studentDao = null;
    private CoursesDao coursesDao = null;
    boolean[] columnEditables = null;
	private ClassedDao classedDao = null;
	
	public DrawCountScoreJPanel3(){
		setLayout(null);
		setBackground(Color.WHITE);
		controlDefine();
		controlBuild();
		getMComboBoxClassData();
		getMComboBoxCourseData();
		setData();
		setTable();
		controlPrettify();
		controlListener();
	}

	private void setTable() {
		this.mTable = new JTable();
		this.mTable.setModel(new DefaultTableModel(rowData,columnNames) {
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.mS = new JScrollPane(this.mTable);
		
		this.mTable.getTableHeader().setBounds(120, 80, 520, 30);
		this.mS.setBounds(120, 140, 520, 320);
		
		add(this.mTable.getTableHeader());
		add(this.mS);
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
		this.mComboBox = new JComboBox<String>();
		this.mBt = new JButton();
		this.mComboBoxGrade = new JComboBox<String>();
		this.mComboBoxClass = new JComboBox<String>();
		this.mComboBoxCourse = new JComboBox<String>();
	}

	private void controlBuild() {
		this.mComboBox.setBounds(520, 10, 120, 25);
		this.mComboBox.setBackground(Color.WHITE);
		this.mComboBox.addItem("年级");
		this.mComboBox.addItem("班级");
		this.mBt.setBounds(660, 10, 80, 25);
		this.mBt.setBackground(Color.lightGray);
		this.mBt.setText("排序");
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
		
		add(this.mComboBox);
		add(this.mBt);
		add(this.mComboBoxGrade);
		add(this.mComboBoxClass);
		add(this.mComboBoxCourse);
	}

	private void controlPrettify() {
		
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
			setData();
			setTable();
		});
	}


	@SuppressWarnings("unused")
	private void setData() {
		this.scoreDao = new ScoreDaoImpl();
		this.courseDao = new CourseDaoImpl();
		this.studentDao = new StudentDaoImpl();
		List<Student> student = new ArrayList<Student>();
		this.columnEditables = new boolean[3];
		for(int b = 0;b < 3;b++) {
			this.columnEditables[b] = false;
		}
		try {
			List<Score> scores = null;
			if(this.mComboBox.getSelectedIndex() == 0) {
				scores = this.scoreDao.findAllGrade(this.mComboBoxGrade.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
			}else {
				scores = this.scoreDao.findAllClass(this.mComboBoxClass.getSelectedItem().toString(), this.mComboBoxCourse.getSelectedIndex()+1);
			}
			//排序
			List<Score> scoreSort = ScoreSortUtil.sortScore(scores);
			//下面的scores -> scoreSort
			
			Student st = null;
			Student st1 = null;
			for(Score s : scores) {
				st = new Student();
				st.setNumber(s.getNumber());
				st1 = new Student();
				st1.setNumber(this.studentDao.findOne(st).getNumber());
				st1.setName(this.studentDao.findOne(st).getName());
				student.add(st1);
			}
			this.rowData = new Object[scores.size()][3];
			int n = 0;
			for(Student s : student) {
				this.rowData[n][0] = s.getNumber();
				this.rowData[n][1] = s.getName();
				n++;
			}
			for(int m = 0;m < student.size();m++) {
				this.rowData[m][2] = scores.get(m).getScore();
			}
			this.columnNames = new Object[3];
			this.columnNames[0] = "学号";
			this.columnNames[1] = "姓名";
			this.columnNames[2] = this.mComboBoxCourse.getSelectedItem().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
