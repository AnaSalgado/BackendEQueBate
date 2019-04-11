import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.mysql.jdbc.ResultSetMetaData;


public class ConnectionDB {

	static Connection connection = null;
	static String databaseName = "Safepet";
	static String url = "jdbc:mysql://35.205.27.245:3306/" + databaseName + "?autoReconnect=true&useSSL=false";
	
	static String username = "Safepet";
	static String password = "";
	
	public static String SelectQuery(String table) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
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
		}
		if(connection != null) {
			System.out.println("bd foi conectada");
		}
		
		return s;
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
	
	public static void DeleteQuery(String idcoluna, String id, String table) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query = "DELETE FROM " + table + " WHERE " + idcoluna + " = " + id;
		
		System.out.println(query);
		PreparedStatement sp = connection.prepareStatement(query);
		sp.executeQuery();
	}
}