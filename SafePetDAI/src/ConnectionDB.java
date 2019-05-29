import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import DataObjects.*;

public class ConnectionDB {

	static Connection connection = null;
	static String databaseName = "Safepet";
	static String url = "jdbc:mysql://35.205.27.245:3306/" + databaseName + "?autoReconnect=true&useSSL=false";
	
	static String username = "Safepet";
	static String password = "Safepetdai1819";
	
	
	
	public static ArrayList<User> getUsers(String table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
	
		PreparedStatement sp = connection.prepareStatement("SELECT * FROM" + table);
	    
		ResultSet stat = sp.executeQuery();
	
		ArrayList<User> user_list = new ArrayList<User>();
		
		while (stat.next()) 
		{
			user_list.add((User) DataObjectFactory.getDataObject(table, stat));
	
	}
		return user_list;
	}	
	
	public static String SelectQuery(String table) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(url, username, password);

		PreparedStatement sp = connection.prepareStatement("SELECT * FROM " + table);

		ResultSet stat = sp.executeQuery();

		ArrayList<Object> object_list = new ArrayList<Object>();
	
		while (stat.next()) 
		{
			object_list.add(DataObjectFactory.getDataObject(table, stat));
		}
		
		if(connection != null) {

			System.out.println("Conexão á Base de Dados efectuada com sucesso!");
			connection.close();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = "";
		
		try {
			jsonInString = mapper.writeValueAsString(object_list);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return jsonInString;	
	}
	
	public static String SelectWhereQuery(String table, String[] campos, Object[] valores_campos) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(url, username, password);

		String query = "SELECT * FROM " + table + " WHERE ";
		
		for (int i = 0; i < campos.length; i++) {
			
			query += campos[i] + " = '" + valores_campos[i] + "'";			
			
			if (i != campos.length-1)
			{
				query += "AND ";
			}
		}
		
		PreparedStatement sp = connection.prepareStatement(query);

		ResultSet stat = sp.executeQuery();

		ArrayList<Object> object_list = new ArrayList<Object>();
	
		while (stat.next()) 
		{
			object_list.add(DataObjectFactory.getDataObject(table, stat));
		}
		
		if(connection != null) {

			System.out.println("Conexão á Base de Dados efectuada com sucesso!");
			connection.close();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = "";
		
		try {
			jsonInString = mapper.writeValueAsString(object_list);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return jsonInString;	
	}
	
	public static void UpdateQuery(String table,String[] campos, Object[] valores_campos, String campo_id, String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query = "UPDATE " + table + " SET ";
		
		for (int i = 0; i < campos.length; i++) {
			
			query += campos[i] + " = '" + valores_campos[i] + "'";			
			
			if (i != campos.length-1)
			{
				query += ", ";
			}
		}
		
		query += "WHERE " + campo_id + " = " + id; 
		
		System.out.println(query);
		PreparedStatement sp = connection.prepareStatement(query);
		sp.execute();
	}
		
		
		public static void DeleteQuery(String idcoluna, String id, String table) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			
			String query = "DELETE FROM " + table + " WHERE " + idcoluna + " = " + id;
			
			System.out.println(query);
			PreparedStatement sp = connection.prepareStatement(query);
			sp.executeUpdate();
		}
	
		
	public static void InsertQuery(String table, String[] colunas, Object[] valores) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query ="INSERT INTO " + table + " (";
		
		//Coisinho das colunas
		for (int i = 0; i < colunas.length; i++)
		{
			query += colunas[i];
			
			if (i != colunas.length-1)
			{
				query += ",";
			}
			else
			{
				query += ") VALUES (";
			}
		}
		
		//Coisinho dos valores
		for (int i = 0; i < valores.length; i++)
		{
			query += "'" + valores[i].toString() + "'";
			
			if (i != valores.length-1)
			{
				query += ",";
			}
			else
			{
				query += ")";
			}
		}
		
		System.out.println(query);
		
		PreparedStatement sp = connection.prepareStatement(query);
		sp.execute();		
	}

	public static boolean checkUser (String email, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
/*
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			
			String query = "SELECT * FROM Donos WHERE email_dono = '" + email + "'"+ " AND password_dono = '" + pass + "'"; 
			String query3 = "SELECT * FROM Veterinarios WHERE email_vet = '" + email + "'"+ " AND pass_vet = '" + pass + "'"; 
			String query4 = "SELECT * FROM Seguradoras WHERE email_seg = '" + email + "'"+ " AND pass_seg = '" + pass + "'"; 
			
			String query2 = "SELECT password_dono FROM Donos WHERE email_dono = '" + email + "'";
			String query5 = "SELECT passvet FROM Veterinarios WHERE email_vet = '" + email + "'";
			String query6 = "SELECT pass_seg FROM Seguradoras WHERE email_seg = '" + email + "'";
			
			System.out.println(query + "\n" + query3 + "\n" + query4);
			System.out.println(query2 + "\n" + query5 + "\n" + query6);
			ArrayList<String[]> list_users = new ArrayList<String[]>();
			
			PreparedStatement ps = connection.prepareStatement(query);
			PreparedStatement ps2 = connection.prepareStatement(query3);
			PreparedStatement ps3 = connection.prepareStatement(query4);
			PreparedStatement sp = connection.prepareStatement(query2);
			PreparedStatement sp2 = connection.prepareStatement(query5);
			PreparedStatement sp3 = connection.prepareStatement(query6);
			
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			ResultSet rs3 = ps3.executeQuery();
			
			int columnCount = rs.getMetaData().getColumnCount();
			int columnCount2 = rs2.getMetaData().getColumnCount();
			int columnCount3 = rs3.getMetaData().getColumnCount();
			
			while(rs.next() || rs2.next() || rs3.next()) {
				String[] row = new String[columnCount];
				String[] row2 = new String[columnCount2];
				String[] row3 = new String[columnCount3];
				
				for(int i = 0; i<columnCount; i++) {
					row[i] = rs.getString(i + 1);
				}
				list_users.add(row);
				for(int i = 0; i<columnCount2; i++) {
					row2[i] = rs2.getString(i + 1);
				}
				list_users.add(row2);
				for(int i = 0; i<columnCount3; i++) {
					row3[i] = rs3.getString(i + 1);
				}
				list_users.add(row3);
			}
			
			ResultSet sr = sp.executeQuery();
			ResultSet sr2 = sp2.executeQuery();
			ResultSet sr3 = sp3.executeQuery();
			
			sr.next();
			sr2.next();
			sr3.next();
			String fim ="inicio";
			
			String password = sr.getString("password_dono");
			String password2 = sr2.getString("pass_vet");
			String password3 = sr3.getString("pass_seg");
			boolean vpass = BCrypt.checkpw(pass, password);
			if(vpass) {
			    fim = "Dados corretos!";
		} else {
			if(BCrypt.checkpw(pass, password2)) {
			    fim = "Dados corretos!";
		}else {
			if(BCrypt.checkpw(pass, password3)) {
			    fim = "Dados corretos!";
		}else {
			fim = "Dados incorretos";
			}
			}
			}
			
			System.out.println(fim);	
	return fim;*/
	/*
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(url, username, password);
	String query = "SELECT * FROM " + table + " WHERE " + wheremail + "='" + email + "' AND " + wherepass + "='" + pass + "'"; 
	String query2 = "SELECT " + wherepass + " FROM " + table + " WHERE " + wheremail + "='" + email + "'";
	System.out.println(query);
	System.out.println(query2);
	
	PreparedStatement ps = connection.prepareStatement(query);
	PreparedStatement sp = connection.prepareStatement(query2);
	
	ResultSet rs = ps.executeQuery();
	ResultSet sr = sp.executeQuery();
	rs.next();
	sr.next();
	String fim ="inicio";

	String password = sr.getString(wherepass);
	boolean vpass = BCrypt.checkpw(pass, password);
	if(vpass) {
	    fim = "Dados de Login corretos!";
} else {
	fim = "Dados incorretos"; }
	
	System.out.println(fim);	
return fim;*/
		
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		String query = "SELECT password_dono FROM Donos WHERE email_dono = '" + email + "'";
		String query2 = "SELECT * FROM Donos WHERE email_dono = '" + email + "' AND password_dono = '" + pass + "'";
		System.out.println(query + "\n" + query2);
		PreparedStatement sp = connection.prepareStatement(query2);
		PreparedStatement sp1 = connection.prepareStatement(query);
		ResultSet rs = sp.executeQuery();
		ResultSet rs1 = sp1.executeQuery();
		rs.next();
		rs1.next();
		String password = rs1.getString("password_dono");
		String s = "inicio";
		boolean b;
		
		if(BCrypt.checkpw(pass, password)) {
			b=true;
			s= "Dados de Login corretos";
			System.out.println(s);
		}else {
			b= false;
			s="Dados de Login incorretos";
			System.out.println(s);
		}
		return b;
	}
}