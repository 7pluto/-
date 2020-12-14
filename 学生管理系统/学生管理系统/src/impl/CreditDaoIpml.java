package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CreditDao;
import dbc.DbUtil;
import model.Credit;

public class CreditDaoIpml implements CreditDao {

	private DbUtil dbUtil = new DbUtil();
	private String sql = null;
	private Connection con = null;
	private PreparedStatement prepare = null;
	private ResultSet rs = null;
	
	@Override
	public List<Credit> findGradeAll(String gradeName) throws Exception {
		List<Credit> credits = new ArrayList<Credit>();
		Credit credit = null;
		double c = 0;
		this.con = this.dbUtil.startMySql();
		this.sql = "select * from score s,course c where s.course=c.id and s.number in(select st.number from student st,grade g where st.grade=g.id and g.name=?)";
		this.prepare = this.con.prepareStatement(this.sql);
		this.prepare.setString(1, gradeName);
		this.rs = this.prepare.executeQuery();
		while(rs.next()) {
			credit = new Credit();
			credit.setNumber(rs.getString("number"));
			c = 0.01*rs.getDouble("score")*rs.getDouble("credit");
			credit.setCredit(c);
			credits.add(credit);
		}
		this.dbUtil.closeConnection(con, prepare, rs);
		return credits;
	}

}
