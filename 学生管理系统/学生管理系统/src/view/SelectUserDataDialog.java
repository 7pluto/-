package view;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.StudentDao;
import impl.StudentDaoImpl;
import model.ImageList;
import model.Student;
import util.SetScrollPanes;

@SuppressWarnings("serial")
public class SelectUserDataDialog extends JDialog {

	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JScrollPane mS;
	private JTable mTable;
	//表头（列名）
    Object[] columnNames = {"学号", "姓名", "性别", "班级", "年级", "身份号", "地址", "电话"};
    // 表格所有行数据
    Object[][] rowData = null;
    private StudentDao studentDao = null;
	
	public SelectUserDataDialog(JFrame f,int x,int y) {
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
		this.mTable = new JTable(rowData,columnNames);
		this.mS = new JScrollPane(this.mTable);
	}

	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		this.mTable.getTableHeader().setBounds(20, 20, 440, 30);
		this.mS.setBounds(20, 50, 440, 220);
		add(this.mBtExit);
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
	}

	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(this.x+122, this.y+60, 478, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
