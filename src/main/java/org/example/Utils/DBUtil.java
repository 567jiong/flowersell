package org.example.Utils;

import java.sql.*;

//���ݿ���ʵĹ�����
public class DBUtil {
	
	static{
		//1.ע����������--ִֻ��һ�μ���
		//driverManagerֱ��ע���������򣬻�ȡconnection����
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection(){
		String url = "jdbc:mysql://localhost:3306/flowersell?characterEncoding=utf-8&serverTimezone=GMT%2B8";
		String name = "root";
		String password = "a123456";
		Connection conn = null;
		try {
			//��ȡ���ʹӶ�ע��
			//Class.forName("com.mysql.jdbc.Driver");
			//��ȡConncetion�ӿڵ�ʵ��������֪��ʵ���࣬����ʵ�ֵ�ϸ��
			conn = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//�رղ����������д򿪵���Դ���йر�
	public static void close(Connection conn, Statement st, PreparedStatement ps, ResultSet rs){
		try {
			if(rs != null)
				rs.close();
			if(st != null)
				st.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
