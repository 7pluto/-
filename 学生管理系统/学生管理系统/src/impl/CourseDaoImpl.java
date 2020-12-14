package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.CourseDao;
import dbc.DbUtil;

public class CourseDaoImpl implements CourseDao {

	private DbUtil dbUtil = new DbUtil();
	private String sql = null;
	private Connection con = null;
	private PreparedStatement prepare = null;
	private ResultSet rs = null;
	
	@Override
	public int getAllNum() throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select count(*) from courses";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			num = rs.getInt(1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

}
