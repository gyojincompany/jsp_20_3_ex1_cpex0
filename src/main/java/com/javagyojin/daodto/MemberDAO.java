package com.javagyojin.daodto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {

//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String uid = "scott";
//	private String upw = "tiger";
	private String query ="select * from members";
	
	private DataSource dataSource;
	
	public MemberDAO() {
		super();
		// TODO Auto-generated constructor stub
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<MemberDTO> memberSelect() {
		
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//con = DriverManager.getConnection(url, uid, upw);
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
						
			while(rs.next()){
				String name = rs.getString("name");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String phone1 = rs.getString("phone1");
				String phone2 = rs.getString("phone2");
				String phone3 = rs.getString("phone3");
				String gender = rs.getString("gender");
				
				MemberDTO dto = new MemberDTO(name, id, pw, phone1, phone2, phone3, gender);
				dtos.add(dto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con !=null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		return dtos;
		
	}	
	
	
}
