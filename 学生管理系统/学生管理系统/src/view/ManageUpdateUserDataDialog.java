package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ClassedDao;
import dao.StudentDao;
import impl.ClassedDaoImpl;
import impl.StudentDaoImpl;
import model.Classed;
import model.ImageList;
import model.Student;
import util.SetScrollPanes;
import util.StringUtil;

@SuppressWarnings("serial")
public class ManageUpdateUserDataDialog extends JDialog {

	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	
	private JLabel mLb1,mLb2,mL3;
	private JTextField mText1,mText2;
	private JComboBox<String> mComboBoxModel;
	private JScrollPane mS;
	private JTable mTable;
	private JButton mBt;
	private JLabel mLbNum,mLbName,mLbSex1,mLbSex2,mLbGrade,mLbClass;
	private JTextField mTextNum,mTextName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton manJrb;
	private JRadioButton girJrb ;
	private JComboBox<String> mComboBoxModel2,mComboBoxClass;
	private JButton mBtUpdate;
	//表头（列名）
    Object[] columnNames = {"学号", "姓名", "性别", "班级", "年级", "身份号", "地址", "电话"};
    // 表格所有行数据
    Object[][] rowData = null;
    private StudentDao studentDao = null;
    private ClassedDao classedDao = null;
	
	public ManageUpdateUserDataDialog(JFrame f,int x,int y) {
		super(f);
		this.x = x;
		this.y = y;
		setData();
		viewBuild();
		controlDefine();
		controlBuild();
		getMComboBoxClassData();
		controlPrettify();
		controlListener();
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
	
	private void setData() {
		this.studentDao = new StudentDaoImpl();
		int num = 0;
		List<Student> students = null;
		try {
			num = this.studentDao.getAllNum();
			students = this.studentDao.findAll();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "程序出现严重错误，请联系作者！错误：xx0012");
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
				false, false, false, false, false, false, false,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.mS = new JScrollPane(this.mTable);
		this.mLb1 = new JLabel();
		this.mLb2 = new JLabel();
		this.mL3 = new JLabel();
		this.mText1 = new JTextField();
		this.mText2 = new JTextField();
		this.mComboBoxModel = new JComboBox<String>();
		this.mBt = new JButton();
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
		this.mComboBoxModel2 = new JComboBox<String>();
		this.mComboBoxClass = new JComboBox<String>();
		this.mBtUpdate = new JButton();
	}


	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		this.mTable.getTableHeader().setBounds(20, 30, 440, 30);
		this.mS.setBounds(20, 90, 440, 85);
		this.mLb1.setBounds(10, 50, 60, 25);
		this.mLb1.setText("学号:");
		this.mLb2.setBounds(140, 50, 60, 25);
		this.mLb2.setText("姓名:");
		this.mL3.setBounds(0, 180, 480, 1);
		this.mL3.setOpaque(true);
		this.mL3.setBackground(Color.BLACK);
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
		
		this.mLbNum.setBounds(100, 200, 60, 30);
		this.mLbNum.setText("学号:");
		this.mLbName.setBounds(100, 240, 60, 30);
		this.mLbName.setText("姓名:");
		this.mLbSex1.setBounds(200, 280, 60, 30);
		this.mLbSex1.setText("男");
		add(this.mLbSex1);
		this.mLbSex2.setBounds(280, 280, 60, 30);
		this.mLbSex2.setText("女");
		add(this.mLbSex2);
		this.manJrb.setBounds(220, 285, 20, 20);
		this.manJrb.setBackground(Color.WHITE);
		this.manJrb.setSelected(true);
		this.girJrb.setBounds(300, 285, 20, 20);
		this.girJrb.setBackground(Color.WHITE);
		this.mLbGrade.setBounds(100, 310, 60, 30);
		this.mLbGrade.setText("年级:");
		this.mLbClass.setBounds(100, 350, 60, 30);
		this.mLbClass.setText("班级:");
		this.mTextNum.setBounds(160, 200, 200, 30);
		this.mTextName.setBounds(160, 240, 200, 30);
		this.mComboBoxModel2.setBounds(160, 310, 200, 30);
		this.mComboBoxModel2.setBackground(Color.WHITE);
		this.mComboBoxModel2.addItem("请选择");
		this.mComboBoxModel2.addItem("大一");
		this.mComboBoxModel2.addItem("大二");
		this.mComboBoxModel2.addItem("大三");
		this.mComboBoxModel2.addItem("大四");
		this.mComboBoxClass.setBounds(160, 350, 200, 30);
		this.mBtUpdate.setBounds(160, 400, 160, 30);
		this.mBtUpdate.setBackground(Color.LIGHT_GRAY);
		this.mBtUpdate.setText("修改");
		
		
		add(this.mBtExit);
		add(this.mLb1);
		add(this.mLb2);
		add(this.mL3);
		add(this.mText1);
		add(this.mText2);
		add(this.mComboBoxModel);
		add(this.mBt);
		add(this.mTable.getTableHeader());
		add(this.mS);
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
		add(this.mComboBoxModel2);
		add(this.mComboBoxClass);
		add(this.mBtUpdate);
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
		this.mTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent em) {
				bookTableMousePressed(em);
			}
		});
		this.mBtUpdate.addActionListener(e -> {
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
			Student student = new Student();
			student.setName(this.mTextName.getText());
			if(this.manJrb.isSelected()) {
				student.setSex("男");
			}else {
				student.setSex("女");
			}
			student.setGradeInt(this.mComboBoxModel2.getSelectedIndex());
			student.setClassedInt(this.mComboBoxClass.getSelectedIndex()+1);
			student.setNumber(this.mTextNum.getText());
			int num;
			try {
				num = this.studentDao.updateStudent(student);
				if(num == 1) {
					JOptionPane.showMessageDialog(this, "修改成功！");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "程序出现严重错误，请联系作者！错误：xx0010");
			}
			
		});
	}

	
	/**
	 * 表格点击事件处理
	 * @param em
	 */
	private void bookTableMousePressed(MouseEvent em) {
		int row = this.mTable.getSelectedRow();
		this.mTextNum.setText(mTable.getValueAt(row, 0).toString());
		this.mTextName.setText(mTable.getValueAt(row, 1).toString());
		String sex = mTable.getValueAt(row, 2).toString();
		if("男".equals(sex)) {
			this.manJrb.setSelected(true);
		}else if("女".equals(sex)){
			this.girJrb.setSelected(true);
		}
		if(mTable.getValueAt(row, 4).toString().equals("大一")) {
			this.mComboBoxModel2.setSelectedIndex(1);
		}else if(mTable.getValueAt(row, 4).toString().equals("大二")) {
			this.mComboBoxModel2.setSelectedIndex(2);
		}else if(mTable.getValueAt(row, 4).toString().equals("大三")) {
			this.mComboBoxModel2.setSelectedIndex(3);
		}else if(mTable.getValueAt(row, 4).toString().equals("大四")) {
			this.mComboBoxModel2.setSelectedIndex(4);
		}
		for(int i = 0;i <this.mComboBoxClass.getItemCount();i++) {
			if(mTable.getValueAt(row, 3).toString().equals(this.mComboBoxClass.getItemAt(i))) {
				this.mComboBoxClass.setSelectedIndex(i);
			}
		}
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
			JOptionPane.showMessageDialog(this, "程序出现严重错误，请联系作者！错误：xx0011");
		}
		for(@SuppressWarnings("rawtypes") Vector v : vectors) {
			dtm.addRow(v);
		}
	}

	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(this.x+122, this.y+30, 478, 440);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
