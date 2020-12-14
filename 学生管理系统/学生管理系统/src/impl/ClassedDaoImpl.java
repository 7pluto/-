package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ClassedDao;
import dbc.DbUtil;
import model.Classed;

public class ClassedDaoImpl implements ClassedDao {

	private DbUtil dbUtil = new DbUtil();
	private String sql = null;
	private Connection con = null;
	private PreparedStatement prepare = null;
	private ResultSet rs = null;
	
	@Override
	public int getNum() throws Exception{
		int num = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select count(*) from classed";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			num = rs.getInt(1);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return num;
	}

	@Override
	public List<Classed> findAll() throws Exception{
		List<Classed> classeds = new ArrayList<Classed>();
		Classed classed = null;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from classed";
		this.prepare = this.con.prepareStatement(this.sql);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			classed = new Classed();
			classed.setName(rs.getString("name"));
			classeds.add(classed);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return classeds;
	}



}
