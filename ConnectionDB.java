import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.ResultSetMetaData;



public class ConnectionDB {

	static Connection connection = null;
	static String databaseName = "Safepet";
	static String url = "jdbc:mysql://35.205.27.245:3306/" + databaseName + "?autoReconnect=true&useSSL=false";
	
	static String username = "Safepet";
	static String password = "Safepetdai1819";
	
	public static String SelectQuery(String table) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, JsonProcessingException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		PreparedStatement sp = connection.prepareStatement("SELECT * FROM " + table);

		ResultSet stat = sp.executeQuery();
		ResultSetMetaData rsmd = (ResultSetMetaData) stat.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		
		String s = "";
		
		while (stat.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) s+= ",  ";
		        String columnValue = stat.getString(i);
		        s += columnValue + " " + rsmd.getColumnName(i);
		        s += "\n";
		    }
		    s += "\n";
		    System.out.println(s);
		}
		if(connection != null) {
			System.out.println("bd foi conectada");
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
	
		
		Alerta jesus = new Alerta("1","titulo random", "descrição blabla", "2019-04-15", "00:00:00");
		
		
		String jsonInString = mapper.writeValueAsString(jesus);
		
		try {
			
			//Convert object to JSON string and save into file directly
		   //mapper.WriteValue(jesus);
			
			//convert object to JSON String
		    System.out.println(jsonInString);
			
			//Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jesus);
			System.out.println(jsonInString);
			
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
				e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
		sp.execute(query);
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
		sp.executeQuery();		
	}
}