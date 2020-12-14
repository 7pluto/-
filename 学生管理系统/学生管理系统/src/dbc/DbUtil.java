package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.GetLocalIpUtil;


public class DbUtil {

	private GetLocalIpUtil getLocalIpUtil = new GetLocalIpUtil();
	private String url = "jdbc:mysql://localhost:3306/studentmanage?useSSL=false&characterEncoding=utf-8";
	//String url = "jdbc:mysql://localhost:3306/my?useSSL=false";
	private String user = "root";
	private String password = "123456";
	
	public Connection startMySql() throws Exception {
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		return con;
	}
	
	public void closeConnection(Connection con,PreparedStatement prepare,ResultSet rs) throws Exception{
		if(rs != null) {
			rs.close();
		}
		if(prepare != null) {
			prepare.close();
		}
		if(con != null) {
			con.close();
		}
		
	}
}
