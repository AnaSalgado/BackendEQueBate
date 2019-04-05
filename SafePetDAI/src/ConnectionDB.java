import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.mysql.jdbc.ResultSetMetaData;


public class ConnectionDB {

	static Connection connection = null;
	static String databaseName = "Safepet";
	static String url = "jdbc:mysql://35.205.27.245:3306/" + databaseName + "?autoReconnect=true&useSSL=false";
	
	static String username = "Safepet";
	static String password = "";
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = connection.prepareStatement("UPDATE Animais SET nome_animal='ola' WHERE id_animal='1'");
		PreparedStatement sp = connection.prepareStatement("SELECT * FROM Animais");
		
		int status = ps.executeUpdate();
		/*ResultSet stat = sp.executeQuery();
		String s = stat.getArray("nome_animal");
		System.out.println("ola");*/
		ResultSet stat = sp.executeQuery();
		ResultSetMetaData rsmd = (ResultSetMetaData) stat.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (stat.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(",  ");
		        String columnValue = stat.getString(i);
		        System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    }
		    System.out.println("");
		}
		if(connection != null) {
			System.out.println("bd foi conectada");
		}
		if(status != 0) {
			System.out.println("update feito");
		}
	}	
	
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
}