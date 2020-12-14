package test;

import java.util.ArrayList;
import java.util.List;

import dao.CourseDao;
import dao.CreditDao;
import dao.ScoreDao;
import dao.StudentDao;
import impl.CourseDaoImpl;
import impl.CreditDaoIpml;
import impl.ScoreDaoImpl;
import impl.StudentDaoImpl;
import model.Credit;
import model.Score;
import model.ScoreCount;
import model.Student;
import util.StringUtil;

public class Test {

	private StudentDao studentDao = new StudentDaoImpl();
	private ScoreDao scoreDao = new ScoreDaoImpl();
	private CourseDao courseDao = new CourseDaoImpl();
	private CreditDao creditDao = new CreditDaoIpml();
	
	@org.junit.Test
	public void testStringUtil() throws Exception {
		System.out.println(StringUtil.isInt("123v"));
		System.out.println(StringUtil.isNumberString("123456"));
	}
	
	@org.junit.Test
	public void FindAllNum() throws Exception {
		int num = studentDao.getAllNum();
		System.out.println(num);
	}
	
	@org.junit.Test
	public void FindAll() throws Exception {
		List<Student> students = studentDao.findAll();
		for(Student s : students) {
			System.out.println(s);
		}
	}
	
	@org.junit.Test
	public void FindScoreAll() throws Exception {
		List<Score> score = scoreDao.findAll();
		for(Score s : score) {
			System.out.println(s);
		}
	}
	
	@org.junit.Test
	public void inCreateScore() throws Exception {
		Score score = new Score();
		score.setNumber("3118000803");
		score.setScore(0);
		int add = this.courseDao.getAllNum();
		System.out.println(add);
		try {
			int num = scoreDao.inCreateScore(score, add);
			if(num == add) {
				System.out.println("添加成功！");
			}
		}catch (Exception e) {
			System.out.println("有人");
		}
	}
	
	
	@org.junit.Test
	public void findAllGrade() throws Exception {
		try {
			List<Score> scores = scoreDao.findAllGrade("大二",2);
			for(Score s : scores) {
				System.out.println(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void findAllClass() throws Exception {
		try {
			List<Score> scores = scoreDao.findAllClass("180800",1);
			for(Score s : scores) {
				System.out.println(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void getGradeMax() throws Exception {
		try {
			ScoreCount scoreCount = scoreDao.getGradeScoreMax("大一", 5);
			System.out.println(scoreCount.getMax());
			for(String s : scoreCount.getNumber()) {
				System.out.println(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void getGradeMin() throws Exception {
		try {
			ScoreCount scoreCount = scoreDao.getGradeScoreMin("大一", 1);
			System.out.println(scoreCount.getMin());
			for(String s : scoreCount.getNumber()) {
				System.out.println(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void getGradeAvg() throws Exception {
		try {
			double db = scoreDao.getGradeScoreAvg("大一", 6);
			System.out.println(db);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void getClassMin() throws Exception {
		try {
			ScoreCount scoreCount = scoreDao.getClassScoreMin("180804", 1);
			System.out.println(scoreCount.getMin());
			for(String s : scoreCount.getNumber()) {
				System.out.println(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void getCredit() throws Exception {
		List<Credit> credits = this.creditDao.findGradeAll("大一");
		List<Credit> creditss = new ArrayList<Credit>();
		Credit cd = null;
		Student student = null;
		List<String> numbers = this.studentDao.findGradeNumber("大一");
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
		for(Credit c : creditss) {
			System.out.println(c);
		}
	}
}
