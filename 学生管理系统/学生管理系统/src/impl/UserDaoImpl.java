package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.UserDao;
import dbc.DbUtil;
import model.Student;
import model.User;

public class UserDaoImpl implements UserDao {

	private DbUtil dbUtil = new DbUtil();
	private String sql = null;
	private Connection con = null;
	private PreparedStatement prepare = null;
	private ResultSet rs = null;
	
	@Override
	public int inCrateUser(User user) throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "insert into user values(?,?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, user.getNumber());
		this.prepare.setString(2, user.getPassword());
		num = this.prepare.executeUpdate();
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public int updateUser(User user) throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "update user set password=? where number=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, user.getPassword());
		this.prepare.setString(2, user.getNumber());
		num = this.prepare.executeUpdate();
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public User findUser(User user) throws Exception{
		User u = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from user where number=? and password=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, user.getNumber());
		this.prepare.setString(2, user.getPassword());
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			u = new User();
			u.setNumber(rs.getString("number"));
			u.setPassword(rs.getString("password"));
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return u;
	}

	@Override
	public User findManage(User user) throws Exception{
		User u = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from manage where number=? and password=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, user.getNumber());
		this.prepare.setString(2, user.getPassword());
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			u = new User();
			u.setNumber(rs.getString("number"));
			u.setPassword(rs.getString("password"));
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return u;
	}

	@Override
	public Student findUserCredential(String number, String credential) throws Exception {
		Student student = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from student where number=? and identity=?";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, number);
		this.prepare.setString(2, credential);
		this.rs = this.prepare.executeQuery();
		if(rs.next()) {
			student = new Student();
			student.setNumber(rs.getString("number"));
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return student;
	}
	

}
