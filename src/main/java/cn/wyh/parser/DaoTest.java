package cn.wyh.parser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * @author wyh
 *
 */
public class DaoTest {
	private String url;
	
	public DaoTest(String url) throws Exception{
		this.url = url;
		if (url == null || url.trim().equals("")) {
			throw new Exception("url不能为空");
		}
	}
	
	// TODO: 优化为数据库连接池技术
	// TODO: orm sql 注入
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("url = " + url);
		}
		return conn;
	}
	
	private void executeSql(String sql){
		System.err.println(sql);
		try {
			Connection conn = getConnection();
			Statement statement = (Statement) conn.createStatement();
			statement.execute(sql);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加数据
	 * @param date
	 * @param level
	 * @param ip
	 * @param cost
	 */
	public void insertValue(String table, String date, String level, String ip, int cost){
		String sql = "insert into log (time, levels, ip, cost) values('" + date +"','" + level + "','"  + ip + "'," + cost + ");";
		executeSql(sql);
	}
	
	public void deleteValue(String table, int id){
		String sql = "delete from "+ table + " where id= " + id;
		executeSql(sql);
	}
	
	public String selectValue(String table, int id){
		String sql = "select * from " + table + " where id=" + id;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				stringBuffer.append(resultSet.getString("time") + " " + resultSet.getString("levels") + " " + resultSet.getString("ip"));
			}
			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 
	 */
	public void updateValue(String table, int id, String date, String level, String ip, int cost){
		String sql = "update " + table + " set date='" + date + "', levels='" + level + "', ip='" + ip + "', cost='" + cost +"' where id=" + id;
		executeSql(sql);
	}
	//
	public void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
