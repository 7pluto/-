package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import dao.StudentDao;
import dbc.DbUtil;
import model.Student;

public class StudentDaoImpl implements StudentDao {

	private DbUtil dbUtil = new DbUtil();
	private String sql = null;
	private Connection con = null;
	private PreparedStatement prepare = null;
	private ResultSet rs = null;
	
	@Override
	public int getAllNum() throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select count(*) from student";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			num = rs.getInt(1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public List<Student> findAll() throws Exception{
		List<Student> students = new ArrayList<Student>();
		Student student = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from student s,classed c,grade g where s.classed=c.id and s.grade=g.id";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = prepare.executeQuery();
		while(rs.next()) {
			student = new Student();
			student.setNumber(rs.getString("number"));
			student.setName(rs.getString("name"));
			student.setSex(rs.getString("sex"));
			student.setIdentity(rs.getString("identity"));
			student.setAddress(rs.getString("address"));
			student.setPhone(rs.getString("phone"));
			student.setClassed(rs.getString("c.name"));
			student.setGrade(rs.getString("g.name"));
			students.add(student);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return students;
	}

	@Override
	public int updateStudent(Student student) throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "update student set name=?,sex=?,classed=?,grade=? where number=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, student.getName());
		this.prepare.setString(2, student.getSex());
		this.prepare.setInt(3, student.getClassedInt());
		this.prepare.setInt(4, student.getGradeInt());
		this.prepare.setString(5, student.getNumber());
		System.out.println(this.prepare);
		num = this.prepare.executeUpdate();
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public Student findOne(Student student) throws Exception{
		Student s = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from student s,classed c,grade g where number=? and s.classed=c.id and s.grade=g.id";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, student.getNumber());
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			s = new Student();
			s.setNumber(rs.getString("number"));
			s.setName(rs.getString("name"));
			s.setSex(rs.getString("sex"));
			s.setIdentity(rs.getString("identity"));
			s.setAddress(rs.getString("address"));
			s.setPhone(rs.getString("phone"));
			s.setClassed(rs.getString("c.name"));
			s.setGrade(rs.getString("g.name"));
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return s;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Vector> dataList(Student student) throws Exception{
		List<Vector> vectors = new ArrayList<Vector>();
		Vector v = null;
		this.con = this.dbUtil.startMySql();
		StringBuffer buffers = new StringBuffer("select * from student s,classed c,grade g where s.classed=c.id and s.grade=g.id");
		if(!util.StringUtil.isNull(student.getNumber())) {
			//and前面没有空格会报错
			buffers.append(" and number like '%"+student.getNumber()+"%' ");
		}
		if(!util.StringUtil.isNull(student.getName())) {
			//and前面没有空格会报错
			buffers.append(" and s.name like '%"+student.getName()+"%' ");
		}
		if(!(student.getGrade() == null)) {
			//and前面没有空格会报错
			buffers.append(" and g.name='"+student.getGrade()+"'");
		}
		this.prepare = con.prepareStatement(buffers.toString());
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			v = new Vector();
			v.add(rs.getString("number"));
			v.add(rs.getString("name"));
			v.add(rs.getString("sex"));
			v.add(rs.getString("c.name"));
			v.add(rs.getString("g.name"));
			v.add(rs.getString("identity"));
			v.add(rs.getString("address"));
			v.add(rs.getString("phone"));
			vectors.add(v);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return vectors;
	}

	@Override
	public int increateStudent(Student student) throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "insert into student values(?,?,?,?,?,?,?,?)";
		this.prepare = this.con.prepareStatement(sql);
		this.prepare.setString(1, student.getNumber());
		this.prepare.setString(2, student.getName());
		this.prepare.setString(3, student.getSex());
		this.prepare.setString(4, "null");
		this.prepare.setString(5, "null");
		this.prepare.setString(6, "null");
		this.prepare.setInt(7, student.getClassedInt());
		this.prepare.setInt(8, student.getGradeInt());
		num = this.prepare.executeUpdate();
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public List<Student> dataListScore(Student student) throws Exception {
		List<Student> students = new ArrayList<Student>();
		Student student1 = null;
		this.con = this.dbUtil.startMySql();
		StringBuffer buffers = new StringBuffer("select * from student s,classed c,grade g where s.classed=c.id and s.grade=g.id");
		if(!util.StringUtil.isNull(student.getNumber())) {
			//and前面没有空格会报错
			buffers.append(" and number like '%"+student.getNumber()+"%' ");
		}
		if(!util.StringUtil.isNull(student.getName())) {
			//and前面没有空格会报错
			buffers.append(" and s.name like '%"+student.getName()+"%' ");
		}
		this.prepare = con.prepareStatement(buffers.toString());
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			student1 = new Student();
			student1.setNumber(rs.getString("number"));
			student1.setName(rs.getString("name"));
			students.add(student1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return students;
	}

	@Override
	public List<String> findGradeNumber(String gradeName) throws Exception {
		List<String> numbers = new ArrayList<String>();
		this.con = this.dbUtil.startMySql();
		this.sql = "select s.number from student s,grade g where s.grade=g.id and g.name=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, gradeName);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			numbers.add(rs.getString("number"));
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return numbers;
	}

	@Override
	public int updateStudents(Student student) throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "update student set identity=?,address=?,phone=? where number=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, student.getIdentity());
		this.prepare.setString(2, student.getAddress());
		this.prepare.setString(3, student.getPhone());
		this.prepare.setString(4, student.getNumber());
		num = this.prepare.executeUpdate();
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}
}
