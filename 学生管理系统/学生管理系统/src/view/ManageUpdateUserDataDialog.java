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
	//��ͷ��������
    Object[] columnNames = {"ѧ��", "����", "�Ա�", "�༶", "�꼶", "��ݺ�", "��ַ", "�绰"};
    // �������������
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
			JOptionPane.showMessageDialog(this, "����������ش�������ϵ���ߣ�����xx0012");
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
		this.mLb1.setText("ѧ��:");
		this.mLb2.setBounds(140, 50, 60, 25);
		this.mLb2.setText("����:");
		this.mL3.setBounds(0, 180, 480, 1);
		this.mL3.setOpaque(true);
		this.mL3.setBackground(Color.BLACK);
		this.mText1.setBounds(50, 50, 80, 25);
		this.mText2.setBounds(180, 50, 80, 25);
		this.mComboBoxModel.setBounds(280, 50, 100, 25);
		this.mComboBoxModel.setBackground(Color.WHITE);
		this.mComboBoxModel.addItem("����");
		this.mComboBoxModel.addItem("��һ");
		this.mComboBoxModel.addItem("���");
		this.mComboBoxModel.addItem("����");
		this.mComboBoxModel.addItem("����");
		this.mBt.setBounds(400, 50, 60, 25);
		this.mBt.setBackground(Color.LIGHT_GRAY);
		this.mBt.setText("����");
		
		this.mLbNum.setBounds(100, 200, 60, 30);
		this.mLbNum.setText("ѧ��:");
		this.mLbName.setBounds(100, 240, 60, 30);
		this.mLbName.setText("����:");
		this.mLbSex1.setBounds(200, 280, 60, 30);
		this.mLbSex1.setText("��");
		add(this.mLbSex1);
		this.mLbSex2.setBounds(280, 280, 60, 30);
		this.mLbSex2.setText("Ů");
		add(this.mLbSex2);
		this.manJrb.setBounds(220, 285, 20, 20);
		this.manJrb.setBackground(Color.WHITE);
		this.manJrb.setSelected(true);
		this.girJrb.setBounds(300, 285, 20, 20);
		this.girJrb.setBackground(Color.WHITE);
		this.mLbGrade.setBounds(100, 310, 60, 30);
		this.mLbGrade.setText("�꼶:");
		this.mLbClass.setBounds(100, 350, 60, 30);
		this.mLbClass.setText("�༶:");
		this.mTextNum.setBounds(160, 200, 200, 30);
		this.mTextName.setBounds(160, 240, 200, 30);
		this.mComboBoxModel2.setBounds(160, 310, 200, 30);
		this.mComboBoxModel2.setBackground(Color.WHITE);
		this.mComboBoxModel2.addItem("��ѡ��");
		this.mComboBoxModel2.addItem("��һ");
		this.mComboBoxModel2.addItem("���");
		this.mComboBoxModel2.addItem("����");
		this.mComboBoxModel2.addItem("����");
		this.mComboBoxClass.setBounds(160, 350, 200, 30);
		this.mBtUpdate.setBounds(160, 400, 160, 30);
		this.mBtUpdate.setBackground(Color.LIGHT_GRAY);
		this.mBtUpdate.setText("�޸�");
		
		
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
		//ѡ��������ɫ
		this.mTable.setSelectionForeground(Color.RED);
		//ѡ���б���
		this.mTable.setSelectionBackground(Color.BLUE);
		// ����������ɫ
		this.mTable.setGridColor(Color.BLACK);
		// �����Ƿ���ʾ����
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
				student.setGrade("��һ");
			}else if(this.mComboBoxModel.getSelectedIndex() == 2) {
				student.setGrade("���");
			}else if(this.mComboBoxModel.getSelectedIndex() == 3) {
				student.setGrade("����");
			}else if(this.mComboBoxModel.getSelectedIndex() == 4) {
				student.setGrade("����");
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
				JOptionPane.showMessageDialog(this, "��������Ϊ��!");
				return;
			}
			if(StringUtil.isNull(this.mTextNum.getText())) {
				JOptionPane.showMessageDialog(this, "ѧ�Ų���Ϊ��!");
				return;
			}
			if(!StringUtil.isNumberString(this.mTextNum.getText())) {
				JOptionPane.showMessageDialog(this, "ע��ѧ�ŵĸ�ʽ!");
				this.mTextNum.setText("");
				return;
			}
			Student student = new Student();
			student.setName(this.mTextName.getText());
			if(this.manJrb.isSelected()) {
				student.setSex("��");
			}else {
				student.setSex("Ů");
			}
			student.setGradeInt(this.mComboBoxModel2.getSelectedIndex());
			student.setClassedInt(this.mComboBoxClass.getSelectedIndex()+1);
			student.setNumber(this.mTextNum.getText());
			int num;
			try {
				num = this.studentDao.updateStudent(student);
				if(num == 1) {
					JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "����������ش�������ϵ���ߣ�����xx0010");
			}
			
		});
	}

	
	/**
	 * ������¼�����
	 * @param em
	 */
	private void bookTableMousePressed(MouseEvent em) {
		int row = this.mTable.getSelectedRow();
		this.mTextNum.setText(mTable.getValueAt(row, 0).toString());
		this.mTextName.setText(mTable.getValueAt(row, 1).toString());
		String sex = mTable.getValueAt(row, 2).toString();
		if("��".equals(sex)) {
			this.manJrb.setSelected(true);
		}else if("Ů".equals(sex)){
			this.girJrb.setSelected(true);
		}
		if(mTable.getValueAt(row, 4).toString().equals("��һ")) {
			this.mComboBoxModel2.setSelectedIndex(1);
		}else if(mTable.getValueAt(row, 4).toString().equals("���")) {
			this.mComboBoxModel2.setSelectedIndex(2);
		}else if(mTable.getValueAt(row, 4).toString().equals("����")) {
			this.mComboBoxModel2.setSelectedIndex(3);
		}else if(mTable.getValueAt(row, 4).toString().equals("����")) {
			this.mComboBoxModel2.setSelectedIndex(4);
		}
		for(int i = 0;i <this.mComboBoxClass.getItemCount();i++) {
			if(mTable.getValueAt(row, 3).toString().equals(this.mComboBoxClass.getItemAt(i))) {
				this.mComboBoxClass.setSelectedIndex(i);
			}
		}
	}

	/**
	 * ��ʼ�����
	 * @param bookType
	 */
	private void repainTable(Student student) {
		DefaultTableModel dtm = (DefaultTableModel) this.mTable.getModel();
		//�ѱ�����
		dtm.setRowCount(0);
		@SuppressWarnings("rawtypes")
		List<Vector> vectors = null;
		try {
			vectors = this.studentDao.dataList(student);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "����������ش�������ϵ���ߣ�����xx0011");
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
