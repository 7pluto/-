package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.CoursesDao;
import dao.ScoreDao;
import dao.StudentDao;
import impl.CourseDaoImpl;
import impl.CoursesDaoImpl;
import impl.ScoreDaoImpl;
import impl.StudentDaoImpl;
import model.Courses;
import model.ImageList;
import model.Score;
import model.Student;
import util.SetScrollPanes;

@SuppressWarnings("serial")
public class UpdateScoreDialog extends JDialog {

	@SuppressWarnings("unused")
	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JLabel mLb1,mLb2;
	private JTextField mText1,mText2;
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JScrollPane mS;
	private JTable mTable;
	private JButton mBt,mBtUpdate;
	//表头（列名）
    Object[] columnNames = null;
    // 表格所有行数据
    Object[][] rowData = null;
    private ScoreDao scoreDao = null;
    private CourseDao courseDao = null;
    private StudentDao studentDao = null;
    private CoursesDao coursesDao = null;
    boolean[] columnEditables = null;
    boolean isUpdate = false;
    List<Integer> updateRow = new ArrayList<Integer>();
    List<Integer> updateColumn = new ArrayList<Integer>();
	
	public UpdateScoreDialog(JFrame f,int x,int y) {
		super(f);
		this.x = x;
		this.y = y;
		setData();
		viewBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
	}

	private void setData() {
		this.scoreDao = new ScoreDaoImpl();
		this.courseDao = new CourseDaoImpl();
		this.studentDao = new StudentDaoImpl();
		List<Student> student = null;
		try {
			student = this.studentDao.findAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int courseNum = 0;
		try {
			courseNum = this.courseDao.getAllNum();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.columnEditables = new boolean[courseNum+2];
		this.columnEditables[0] = false;
		this.columnEditables[1] = false;
		for(int b = 2;b < courseNum+2;b++) {
			this.columnEditables[b] = true;
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
	
	private void controlDefine() {
		this.mTable = new JTable();
		this.mTable.setModel(new DefaultTableModel(rowData,columnNames) {
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.mS = new JScrollPane(this.mTable);
		this.mLb1 = new JLabel();
		this.mLb2 = new JLabel();
		this.mText1 = new JTextField();
		this.mText2 = new JTextField();
		this.mBt = new JButton();
		this.mBtUpdate = new JButton();
	}

	private void controlBuild() {
		this.mBtExit.setBounds(970, 0, 30, 30);
		this.mTable.getTableHeader().setBounds(20, 30, 440, 30);
		this.mS.setBounds(20, 90, 960, 500);
		this.mLb1.setBounds(100, 50, 60, 25);
		this.mLb1.setText("学号:");
		this.mLb2.setBounds(400, 50, 60, 25);
		this.mLb2.setText("姓名:");
		this.mText1.setBounds(140, 50, 150, 25);
		this.mText2.setBounds(440, 50, 150, 25);
		this.mBt.setBounds(650, 50, 150, 25);
		this.mBt.setBackground(Color.LIGHT_GRAY);
		this.mBt.setText("查找");
		this.mBtUpdate.setBounds(820, 50, 150, 25);
		this.mBtUpdate.setBackground(Color.LIGHT_GRAY);
		this.mBtUpdate.setText("修改");
		
		add(this.mBtExit);
		add(this.mLb1);
		add(this.mLb2);
		add(this.mText1);
		add(this.mText2);
		add(this.mBt);
		add(this.mBtUpdate);
		add(this.mTable.getTableHeader());
		add(this.mS);
	}

	private void controlPrettify() {
		this.mBtExit.setRolloverIcon(this.iL.getImageButtonExit1());
		this.mBtExit.setBorder(null);
		//选中字体颜色
		this.mTable.setSelectionForeground(Color.RED);
		//选中行背景
		this.mTable.setSelectionBackground(Color.BLUE);
		// 设置网格颜色
		this.mTable.setGridColor(Color.BLACK);
		// 设置是否显示网格
		this.mTable.setShowGrid(true);
		SetScrollPanes.setScrollPane(this.mS);
	}

	private void controlListener() {
		this.mBtExit.addActionListener(e -> {
			setVisible(false);
		});
		this.mBt.addActionListener(e -> {
			Student student = new Student();
			student.setNumber(this.mText1.getText());
			student.setName(this.mText2.getText());
			repainTable(student);
			this.isUpdate = false;
			this.updateRow.clear();
			this.updateColumn.clear();
		});
		this.mBtUpdate.addActionListener(e -> {
			if(this.isUpdate == false) {
				JOptionPane.showMessageDialog(this, "请更改想要修改的内容！");
				return;
			}
			@SuppressWarnings("unused")
			int row = this.mTable.getRowCount();
			Score score = null;
			int columnAdd = 0;
			try {
				for(Integer u : this.updateRow) {
					score = new Score();
					score.setNumber(this.mTable.getValueAt(u, 0).toString());
					score.setScore(Double.parseDouble(this.mTable.getValueAt(u, this.updateColumn.get(columnAdd)).toString()));
					System.out.println(u+"  "+this.updateColumn.get(columnAdd));
					this.scoreDao.UpdateScore(score, this.updateColumn.get(columnAdd++)-1);
				}
				JOptionPane.showMessageDialog(this, "修改成功！");
				this.isUpdate = false;
				this.updateRow.clear();
				this.updateColumn.clear();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		this.mTable.getModel().addTableModelListener(e -> {
			if(e.getType() == TableModelEvent.UPDATE) {
				this.updateRow.add(e.getFirstRow());
				this.updateColumn.add(e.getColumn());
				this.isUpdate = true;
			}
		});
	}

	
	/**
	 * 初始化表格
	 * @param bookType
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void repainTable(Student student) {
		DefaultTableModel dtm = (DefaultTableModel) this.mTable.getModel();
		//把表格清空
		dtm.setRowCount(0);
		int courseNum = 0;
		try {
			courseNum = this.courseDao.getAllNum();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		List<Student> student1 = null;
		try {
			student1 = this.studentDao.dataListScore(student);
			Object[][] rowDatas = this.scoreDao.getManagerFindStudentScore(student1, courseNum);
			this.rowData = new Object[student1.size()][courseNum+2];
			int n = 0;
			for(Student s : student1) {
				this.rowData[n][0] = s.getNumber();
				this.rowData[n][1] = s.getName();
				n++;
			}
			for(int m = 0;m < student1.size();m++) {
				for(int k = 2;k < courseNum+2;k++) {
					this.rowData[m][k] = rowDatas[m][k-2];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Vector> vectors = new ArrayList<Vector>();
		Vector v = null;
		for(int i = 0;i < student1.size();i++) {
			v = new Vector();
			for(int j = 0;j < courseNum+2;j++) {
				v.add(this.rowData[i][j]);
			}
			vectors.add(v);
		}
		for(Vector vs : vectors) {
			dtm.addRow(vs);
		}
	}
	
	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(200, 60, 1000, 600);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
