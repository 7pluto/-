package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.StudentDao;
import impl.StudentDaoImpl;
import model.ImageList;
import model.Student;
import util.StringUtil;

@SuppressWarnings("serial")
public class UpdateUserDataDialog extends JDialog {

	private String userNumber;
	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JLabel mImage;
	private JLabel mLbNum,mLbName,mLbSex,mLbId,mLbAddress,mLbPhone,mLbClass,mLbGrade;
	private JLabel mLbNum1,mLbId1,mLbClass1,mLbGrade1;
	private JTextField mTextName,mTextSex,mTextAddress,mTextPhone;
	private JButton mBt;
	
	private StudentDao studentDao = null;
	
	public UpdateUserDataDialog(JFrame f,int x,int y,String userNumber) {
		super(f);
		this.x = x;
		this.y = y;
		this.userNumber = userNumber;
		viewBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
		setData();
	}

	private void setData() {
		Student student = new Student();
		student.setNumber(this.userNumber);
		this.studentDao = new StudentDaoImpl();
		Student s = null;
		try {
			s = this.studentDao.findOne(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(s != null) {
			this.mLbNum1.setText(this.userNumber);
			this.mTextName.setText(s.getName());
			this.mTextSex.setText(s.getSex());
			this.mLbId1.setText(s.getIdentity());
			this.mTextAddress.setText(s.getAddress());
			this.mLbClass1.setText(s.getClassed());
			this.mTextPhone.setText(s.getPhone());
			this.mLbGrade1.setText(s.getGrade());
		}else {
			JOptionPane.showMessageDialog(this, "����������ش�������ϵ���ߣ�����xx002");
		}
	}
	
	private void controlDefine() {
		this.mImage = new JLabel();
		this.mLbNum = new JLabel();
		this.mLbName = new JLabel();
		this.mLbSex = new JLabel();
		this.mLbId = new JLabel();
		this.mLbAddress = new JLabel();
		this.mLbClass = new JLabel();
		this.mLbPhone = new JLabel();
		this.mLbGrade = new JLabel();
		this.mLbNum1 = new JLabel();
		this.mLbId1 = new JLabel();
		this.mLbClass1 = new JLabel();
		this.mLbGrade1 = new JLabel();
		this.mTextName = new JTextField();
		this.mTextSex = new JTextField();
		this.mTextAddress = new JTextField();
		this.mTextPhone = new JTextField();
		this.mBt = new JButton();
	}

	private void controlBuild() {
		this.mBtExit.setBounds(450, 0, 30, 30);
		this.mImage.setBounds(200, 40, 80, 100);
		this.mImage.setOpaque(true);
		this.mImage.setBackground(Color.lightGray);
		this.mLbNum.setBounds(60, 160, 100, 30);
		this.mLbNum.setText("ѧ��:");
		this.mLbName.setBounds(60, 185, 100, 30);
		this.mLbName.setText("����:");
		this.mLbSex.setBounds(60, 210, 100, 30);
		this.mLbSex.setText("�Ա�:");
		this.mLbId.setBounds(34, 235, 100, 30);
		this.mLbId.setText("���֤��:");
		this.mLbAddress.setBounds(270, 235, 100, 30);
		this.mLbAddress.setText("��ַ:");
		this.mLbPhone.setBounds(270, 210, 100, 30);
		this.mLbPhone.setText("�绰:");
		this.mLbClass.setBounds(270, 160, 100, 30);
		this.mLbClass.setText("�༶:");
		this.mLbGrade.setBounds(270, 185, 100, 30);
		this.mLbGrade.setText("�꼶:");
		this.mLbNum1.setBounds(100, 160, 100, 30);
		this.mLbId1.setBounds(100, 235, 200, 30);
		this.mLbClass1.setBounds(310, 160, 100, 30);
		this.mLbGrade1.setBounds(310, 185, 100, 30);
		this.mTextName.setBounds(100, 185, 130, 25);
		this.mTextSex.setBounds(100, 210, 130, 25);
		this.mTextAddress.setBounds(310, 235, 130, 25);
		this.mTextPhone.setBounds(310, 210, 130, 25);
		this.mBt.setBounds(180, 270, 120, 26);
		this.mBt.setBackground(Color.LIGHT_GRAY);
		this.mBt.setText("����");
		
		add(this.mBtExit);
		add(this.mImage);
		add(this.mLbNum);
		add(this.mLbName);
		add(this.mLbSex);
		add(this.mLbId);
		add(this.mLbAddress);
		add(this.mLbPhone);
		add(this.mLbClass);
		add(this.mLbGrade);
		add(this.mLbNum1);
		add(this.mLbId1);
		add(this.mLbClass1);
		add(this.mLbGrade1);
		add(this.mTextName);
		add(this.mTextSex);
		add(this.mTextAddress);
		add(this.mTextPhone);
		add(this.mBt);
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
				JOptionPane.showMessageDialog(this, "���ֲ���Ϊ��!");
				return;
			}
			if(StringUtil.isNull(this.mTextSex.getText())) {
				JOptionPane.showMessageDialog(this, "�Ա���Ϊ��!");
				return;
			}
			if(StringUtil.isNull(this.mTextPhone.getText())) {
				JOptionPane.showMessageDialog(this, "�绰���벻��Ϊ��!");
				return;
			}
			if(StringUtil.isNull(this.mTextAddress.getText())) {
				JOptionPane.showMessageDialog(this, "��ַ����Ϊ��!");
				return;
			}
			if(!"��".equals(this.mTextSex.getText()) && !"Ů".equals(this.mTextSex.getText())) {
				JOptionPane.showMessageDialog(this, "��������ȷ���Ա�!");
				return;
			}
			if(!StringUtil.isNumberString(this.mTextPhone.getText())) {
				JOptionPane.showMessageDialog(this, "��������ȷ�ĵ绰����!");
				return;
			}
			
			//���ͷ��������
			//��ʱû��ʵ��
			JOptionPane.showMessageDialog(this, "����ѧУ�������룬�ȴ����!");
		});
	}

	private void viewBuild() {
		setLayout(null);
		setModal(true);
		setBounds(this.x+121, this.y+96, 478, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
