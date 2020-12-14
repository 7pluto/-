package view;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.StudentDao;
import impl.StudentDaoImpl;
import model.ImageList;
import model.Student;
import util.SetScrollPanes;

@SuppressWarnings("serial")
public class FindUserDataDialog extends JDialog {

	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JLabel mLb1,mLb2;
	private JTextField mText1,mText2;
	private JComboBox<String> mComboBoxModel;
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JScrollPane mS;
	private JTable mTable;
	private JButton mBt;
	//表头（列名）
    Object[] columnNames = {"学号", "姓名", "性别", "班级", "年级", "身份号", "地址", "电话"};
    // 表格所有行数据
    Object[][] rowData = null;
    private StudentDao studentDao = null;
	
	public FindUserDataDialog(JFrame f,int x,int y) {
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
		this.studentDao = new StudentDaoImpl();
		int num = 0;
		List<Student> students = null;
		try {
			num = this.studentDao.getAllNum();
			students = this.studentDao.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.rowData = new Object[num][8];
		int i = 0;
		for(Student s : students) {
			if(i == num) {
				break;
			}
			rowData[i][0] = s.getNumber();
			rowData[i][1] = s.getName();
			rowData[i][2] = s.getSex();
			rowData[i][3] = s.getClassed();
			rowData[i][4] = s.getGrade();
			rowData[i][5] = s.getIdentity();
			rowData[i][6] = s.getAddress();
			rowData[i][7] = s.getPhone();
			i++;
		}
	}
	
	private void controlDefine() {
		this.mTable = new JTable();
		this.mTable.setModel(new DefaultTableModel(rowData,columnNames) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.mS = new JScrollPane(this.mTable);
		this.mLb1 = new JLabel();
		this.mLb2 = new JLabel();
		this.mText1 = new JTextField();
		this.mText2 = new JTextField();
		this.mComboBoxModel = new JComboBox<String>();
		this.mBt = new JButton();
	}

	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		this.mTable.getTableHeader().setBounds(20, 30, 440, 30);
		this.mS.setBounds(20, 90, 440, 200);
		this.mLb1.setBounds(10, 50, 60, 25);
		this.mLb1.setText("学号:");
		this.mLb2.setBounds(140, 50, 60, 25);
		this.mLb2.setText("姓名:");
		this.mText1.setBounds(50, 50, 80, 25);
		this.mText2.setBounds(180, 50, 80, 25);
		this.mComboBoxModel.setBounds(280, 50, 100, 25);
		this.mComboBoxModel.setBackground(Color.WHITE);
		this.mComboBoxModel.addItem("不限");
		this.mComboBoxModel.addItem("大一");
		this.mComboBoxModel.addItem("大二");
		this.mComboBoxModel.addItem("大三");
		this.mComboBoxModel.addItem("大四");
		this.mBt.setBounds(400, 50, 60, 25);
		this.mBt.setBackground(Color.LIGHT_GRAY);
		this.mBt.setText("查找");
		
		
		add(this.mBtExit);
		add(this.mLb1);
		add(this.mLb2);
		add(this.mText1);
		add(this.mText2);
		add(this.mComboBoxModel);
		add(this.mBt);
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
			if(this.mComboBoxModel.getSelectedIndex() == 1) {
				student.setGrade("大一");
			}else if(this.mComboBoxModel.getSelectedIndex() == 2) {
				student.setGrade("大二");
			}else if(this.mComboBoxModel.getSelectedIndex() == 3) {
				student.setGrade("大三");
			}else if(this.mComboBoxModel.getSelectedIndex() == 4) {
				student.setGrade("大四");
			}
			repainTable(student);
		});
	}

	/**
	 * 初始化表格
	 * @param bookType
	 */
	private void repainTable(Student student) {
		DefaultTableModel dtm = (DefaultTableModel) this.mTable.getModel();
		//把表格清空
		dtm.setRowCount(0);
		@SuppressWarnings("rawtypes")
		List<Vector> vectors = null;
		try {
			vectors = this.studentDao.dataList(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(@SuppressWarnings("rawtypes") Vector v : vectors) {
			dtm.addRow(v);
		}
	}

	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(this.x+122, this.y+60, 478, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
