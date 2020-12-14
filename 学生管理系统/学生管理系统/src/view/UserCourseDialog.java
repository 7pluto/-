package view;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.ScoreDao;
import impl.ScoreDaoImpl;
import model.ImageList;
import model.Score;
import util.SetScrollPanes;

@SuppressWarnings("serial")
public class UserCourseDialog extends JDialog {

	@SuppressWarnings("unused")
	private String userNumber;
	private int x, y;
	private ImageList iL = new ImageList(this.getClass());
	
	private JButton mBtExit = new JButton(iL.getImageButtonExit0());
	private JScrollPane mS;
	private JTable mTable;
	//表头（列名）
    Object[] columnNames = {"编号", "课程名称", "指导老师", "学分", "成绩"};
    // 表格所有行数据
    Object[][] rowData = null;
    private ScoreDao scoreDao = null;
	
	public UserCourseDialog(JFrame f,int x,int y,String userNumber) {
		super(f);
		this.x = x;
		this.y = y;
		this.userNumber = userNumber;
		setData();
		viewBuild();
		controlDefine();
		controlBuild();
		controlPrettify();
		controlListener();
	}

	private void setData() {
		this.scoreDao = new ScoreDaoImpl();
		Score score = new Score();
		score.setNumber("3118000800");
		int num = 0;
		try {
			num = this.scoreDao.getNum(score);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(num == 0) {
			JOptionPane.showMessageDialog(this, "程序出现严重错误，请联系作者！错误：xx001");
		}else {
			List<Score> scores = null;
			try {
				scores = this.scoreDao.findAllOne(score);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.rowData = new Object[num][5];
			int i = 0;
			for(Score s : scores) {
				if(i == num) {
					break;
				}
				rowData[i][0] = i + 1;
				rowData[i][1] = s.getName();
				rowData[i][2] = s.getTeacher();
				rowData[i][3] = s.getCredit();
				rowData[i][4] = s.getScore();
				i++;
			}
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
		setBounds(this.x+121, this.y+96, 478, 305);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
	}
}
