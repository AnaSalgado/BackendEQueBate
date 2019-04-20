import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import DataObjects.DataObjectFactory;
import java.util.ArrayList;
import DataObjects.User;

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
}