package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CoursesDao;
import dbc.DbUtil;
import model.Courses;

public class CoursesDaoImpl implements CoursesDao {

	private DbUtil dbUtil = new DbUtil();
	private String sql = null;
	private Connection con = null;
	private PreparedStatement prepare = null;
	private ResultSet rs = null;
	
	@Override
	public List<Courses> findAll() throws Exception {
		List<Courses> courses = new ArrayList<Courses>();
		Courses c = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from courses";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			c = new Courses();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			courses.add(c);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return courses;
	}

}
