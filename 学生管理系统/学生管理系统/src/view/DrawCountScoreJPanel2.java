package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.CreditDao;
import dao.StudentDao;
import impl.CreditDaoIpml;
import impl.StudentDaoImpl;
import model.Credit;
import model.Student;

@SuppressWarnings("serial")
public class DrawCountScoreJPanel2 extends JPanel {

	private JComboBox<String> mComboBoxModel,mComboBoxModel1;
	private JButton mBt,mBt1;
	private JScrollPane mS;
	private JTable mTable;
	private JLabel mLb,mLb1,mLb2,mLb3;
	private JTextField mText1,mText2,mText3;
	
	private StudentDao studentDao = null;
	private CreditDao creditDao = null;
	
	//表头（列名）
    Object[] columnNames = {"学号", "姓名", "总学分"};
    // 表格所有行数据
    Object[][] rowData = null;
	
	public DrawCountScoreJPanel2(){
		setLayout(null);
		setBackground(Color.WHITE);
		controlDefine();
		controlBuild();
		getData();
		setTable();
		controlListener();
	}

	
	private void setTable() {
		this.mTable = new JTable();
		this.mTable.setModel(new DefaultTableModel(rowData,columnNames) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.mS = new JScrollPane(this.mTable);
		
		this.mTable.getTableHeader().setBounds(160, 30, 440, 30);
		this.mS.setBounds(160, 90, 440, 85);
		
		add(this.mTable.getTableHeader());
		add(this.mS);
	}


	private void controlDefine() {
		this.mComboBoxModel = new JComboBox<String>();
		this.mBt = new JButton();
		this.mComboBoxModel1 = new JComboBox<String>();
		this.mBt1 = new JButton();
		this.mLb = new JLabel();
		this.mLb1 = new JLabel();
		this.mLb2 = new JLabel();
		this.mLb3 = new JLabel();
		this.mText1 = new JTextField();
		this.mText2 = new JTextField();
		this.mText3 = new JTextField();
	}


	private void controlBuild() {
		this.mComboBoxModel.setBounds(400, 20, 200, 25);
		this.mComboBoxModel.setBackground(Color.WHITE);
		this.mComboBoxModel.addItem("大一");
		this.mComboBoxModel.addItem("大二");
		this.mComboBoxModel.addItem("大三");
		this.mComboBoxModel.addItem("大四");
		this.mBt.setBounds(620, 20, 100, 25);
		this.mBt.setBackground(Color.lightGray);
		this.mBt.setText("查询");
		this.mComboBoxModel1.setBounds(400, 200, 200, 25);
		this.mComboBoxModel1.setBackground(Color.WHITE);
		this.mComboBoxModel1.addItem("大一");
		this.mComboBoxModel1.addItem("大二");
		this.mComboBoxModel1.addItem("大三");
		this.mComboBoxModel1.addItem("大四");
		this.mBt1.setBounds(620, 200, 100, 25);
		this.mBt1.setBackground(Color.lightGray);
		this.mBt1.setText("评奖学金");
		this.mLb.setBounds(30, 220, 100, 30);
		this.mLb.setText("评选条件 :");
		this.mLb1.setBounds(80, 250, 100, 30);
		this.mLb1.setText("一等奖学金 :");
		this.mLb2.setBounds(80, 290, 100, 30);
		this.mLb2.setText("二等奖学金 :");
		this.mLb3.setBounds(80, 330, 100, 30);
		this.mLb3.setText("三等奖学金 :");
		this.mText1.setBounds(160, 250, 150, 30);
		this.mText2.setBounds(160, 290, 150, 30);
		this.mText3.setBounds(160, 330, 150, 30);
		
		add(this.mComboBoxModel);
		add(this.mBt);
		add(this.mComboBoxModel1);
		add(this.mBt1);
		add(this.mLb);
		add(this.mLb1);
		add(this.mLb2);
		add(this.mLb3);
		add(this.mText1);
		add(this.mText2);
		add(this.mText3);
	}

	private void controlListener() {
		this.mBt.addActionListener(e -> {
			getData();
			setTable();
		});
	}

	private void getData() {
		this.studentDao = new StudentDaoImpl();
		this.creditDao = new CreditDaoIpml();
		try {
			List<Credit> credits = this.creditDao.findGradeAll(this.mComboBoxModel.getSelectedItem().toString());
			List<Credit> creditss = new ArrayList<Credit>();
			Credit cd = null;
			Student student = null;
			List<String> numbers = this.studentDao.findGradeNumber(this.mComboBoxModel.getSelectedItem().toString());
			double creditAdd[] = new double[numbers.size()];
			for(int i = 0;i < numbers.size();i++) {
				for(Credit c : credits) {
					if(numbers.get(i).equals(c.getNumber())) {
						creditAdd[i] += c.getCredit();
					}
				}
				cd = new Credit();
				cd.setNumber(numbers.get(i));
				student = new Student();
				student.setNumber(numbers.get(i));
				Student st = this.studentDao.findOne(student);
				cd.setName(st.getName());
				cd.setCredit(Double.parseDouble(String.format("%.2f", creditAdd[i])));
				creditss.add(cd);
			}
			this.rowData = new Object[creditss.size()][3];
			int i = 0;
			for(Credit c : creditss) {
				if(i == creditss.size()) {
					break;
				}
				this.rowData[i][0] = c.getNumber();
				this.rowData[i][1] = c.getName();
				this.rowData[i][2] = c.getCredit();
				i++;
			}
		}catch (Exception e) {
			
		}
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
