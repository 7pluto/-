package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ScoreDao;
import dbc.DbUtil;
import model.Score;
import model.ScoreCount;
import model.Student;

public class ScoreDaoImpl implements ScoreDao {

	private DbUtil dbUtil = new DbUtil();
	private String sql = null;
	private Connection con = null;
	private PreparedStatement prepare = null;
	private ResultSet rs = null;
	
	@Override
	public List<Score> findAll() throws Exception{
		List<Score> scores = new ArrayList<Score>();
		Score score = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from score s,course c,courses cs where s.course=c.id and c.name=cs.id";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			score = new Score();
			score.setNumber(rs.getString("number"));
			score.setName(rs.getString("cs.name"));
			score.setTeacher(rs.getString("c.teacher"));
			score.setCredit(rs.getDouble("c.credit"));
			score.setScore(rs.getDouble("score"));
			scores.add(score);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scores;
	}

	@Override
	public List<Score> findAllOne(Score s) throws Exception{
		List<Score> scores = new ArrayList<Score>();
		Score score = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from score s,course c,courses cs where number=? and s.course=c.id and c.name=cs.id";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, s.getNumber());
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			score = new Score();
			score.setName(rs.getString("cs.name"));
			score.setTeacher(rs.getString("c.teacher"));
			score.setCredit(rs.getDouble("c.credit"));
			score.setScore(rs.getDouble("score"));
			scores.add(score);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scores;
	}

	@Override
	public int getAllNum() throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select count(*) from score";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			num = rs.getInt(1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public int getNum(Score score) throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select count(*) from score where number=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, score.getNumber());
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			num = rs.getInt(1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public int inCreateScore(Score score,int add) throws Exception {
		int num = 0;
		this.con = this.dbUtil.startMySql();
		StringBuffer buffers = new StringBuffer("insert into score values(?,?,?)");
		for(int i = 0;i < add-1;i++) {
			buffers.append(",(?,?,?)");
		}
		this.prepare = this.con.prepareStatement(buffers.toString());
		int j = 0;
		for(int i = 1;i <= add;i++) {
			this.prepare.setString(i+j, score.getNumber());
			this.prepare.setInt(i+j+1, i);
			this.prepare.setDouble(i+j+2, score.getScore());
			j += 2;
		}
		System.out.println(this.prepare.toString());
		num = this.prepare.executeUpdate();
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public Object[][] getManagerFindStudentScore(List<Student> student,int courseNum) throws Exception {
		Object[][] object = new Object[student.size()][courseNum];
		int courseNumArray[] = new int[courseNum];
		for(int i = 0;i < courseNum;i++) {
			courseNumArray[i] = 0;
		}
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from score";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			for(int i = 0;i < student.size();i++) {
				if(rs.getString("number").equals(student.get(i).getNumber())) {
					object[i][courseNumArray[i]] = rs.getDouble("score");
					courseNumArray[i]++;
				}
			}
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return object;
	}

	@Override
	public int UpdateScore(Score score,int port) throws Exception {
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "update score set score=? where number=? and course=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setDouble(1, score.getScore());
		this.prepare.setString(2, score.getNumber());
		this.prepare.setInt(3, port);
		num = this.prepare.executeUpdate();
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public List<Score> findAllClass(String className,int courseNum) throws Exception {
		List<Score> scores = new ArrayList<Score>();
		Score score = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select score.* from score where course=? and number in (select student.number from student,classed where student.classed=classed.id and classed.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, className);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			score = new Score();
			score.setNumber(rs.getString("number"));
			score.setScore(rs.getDouble("score"));
			scores.add(score);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scores;
	}

	@Override
	public List<Score> findAllGrade(String gradeName, int courseNum) throws Exception {
		List<Score> scores = new ArrayList<Score>();
		Score score = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from score where course=? and number in (select student.number from student,grade where student.grade=grade.id and grade.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, gradeName);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			score = new Score();
			score.setNumber(rs.getString("number"));
			score.setScore(rs.getDouble("score"));
			scores.add(score);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scores;
	}

	@Override
	public ScoreCount getGradeScoreMax(String gradeName, int courseNum) throws Exception {
		ScoreCount scoreCount = null;
		double max = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select max(score) from score where course=? and number in (select student.number from student,grade where student.grade=grade.id and grade.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, gradeName);
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			scoreCount = new ScoreCount();
			max = rs.getInt(1);
			scoreCount.setMax(max);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		List<String> number = new ArrayList<String>();
		this.con = this.dbUtil.startMySql();
		this.sql = "select number from score where course=? and score=? and number in (select student.number from student,grade where student.grade=grade.id and grade.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setDouble(2, max);
		this.prepare.setString(3, gradeName);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			number.add(rs.getString("number"));
			scoreCount.setNumber(number);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scoreCount;
	}

	@Override
	public ScoreCount getGradeScoreMin(String gradeName, int courseNum) throws Exception {
		ScoreCount scoreCount = null;
		double min = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select min(score) from score where course=? and number in (select student.number from student,grade where student.grade=grade.id and grade.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, gradeName);
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			scoreCount = new ScoreCount();
			min = rs.getInt(1);
			scoreCount.setMin(min);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		List<String> number = new ArrayList<String>();
		this.con = this.dbUtil.startMySql();
		this.sql = "select number from score where course=? and score=? and number in (select student.number from student,grade where student.grade=grade.id and grade.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setDouble(2, min);
		this.prepare.setString(3, gradeName);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			number.add(rs.getString("number"));
			scoreCount.setNumber(number);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scoreCount;
	}

	@Override
	public double getGradeScoreAvg(String gradeName, int courseNum) throws Exception {
		double db = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select avg(score) from score where course=? and number in (select student.number from student,grade where student.grade=grade.id and grade.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, gradeName);
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			db = rs.getInt(1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return db;
	}

	@Override
	public ScoreCount getClassScoreMin(String className, int courseNum) throws Exception {
		ScoreCount scoreCount = null;
		double min = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select min(score) from score where course=? and number in (select student.number from student,classed where student.classed=classed.id and classed.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, className);
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			scoreCount = new ScoreCount();
			min = rs.getInt(1);
			scoreCount.setMin(min);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		List<String> number = new ArrayList<String>();
		this.con = this.dbUtil.startMySql();
		this.sql = "select number from score where course=? and score=? and number in (select student.number from student,classed where student.classed=classed.id and classed.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setDouble(2, min);
		this.prepare.setString(3, className);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			number.add(rs.getString("number"));
			scoreCount.setNumber(number);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scoreCount;
	}

	@Override
	public double getClassScoreAvg(String className, int courseNum) throws Exception {
		double db = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select avg(score) from score where course=? and number in (select student.number from student,classed where student.classed=classed.id and classed.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, className);
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			db = rs.getInt(1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return db;
	}

	@Override
	public ScoreCount getClassScoreMax(String className, int courseNum) throws Exception {
		ScoreCount scoreCount = null;
		double max = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select max(score) from score where course=? and number in (select student.number from student,classed where student.classed=classed.id and classed.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setString(2, className);
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			scoreCount = new ScoreCount();
			max = rs.getInt(1);
			scoreCount.setMax(max);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		List<String> number = new ArrayList<String>();
		this.con = this.dbUtil.startMySql();
		this.sql = "select number from score where course=? and score=? and number in (select student.number from student,classed where student.classed=classed.id and classed.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setInt(1, courseNum);
		this.prepare.setDouble(2,max);
		this.prepare.setString(3, className);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			number.add(rs.getString("number"));
			scoreCount.setNumber(number);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return scoreCount;
	}

}
