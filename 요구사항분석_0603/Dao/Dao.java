package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Dao {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	//인터페이스
	public void connect () throws Exception {
		//connect
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema?serverTimezone=UTC", 
						"root","!ehdgml12");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//create
	public void execute(String query) throws Exception {
		try {
			statement = connect.createStatement();
			statement.execute(query);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	// retrieve 
	public ResultSet retrieve(String query) throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	public void update(String query) throws Exception{
		PreparedStatement stmt = null;
		try {
			stmt = connect.prepareStatement(query);
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public void updateDate(String query) throws Exception{
		
		PreparedStatement stmt = null;
		try {
			stmt = connect.prepareStatement(query);
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}

}
