package DataObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.ResultSetMetaData;

public class DataObjectFactory 
{
	public static Object getDataObject(String table, ResultSet resultset) throws SQLException
	{
		Object o = new Object();
		Map<String,String> valores = new HashMap<String, String>();
		ResultSetMetaData rsmd = (ResultSetMetaData) resultset.getMetaData();
		
		int columnsNumber = rsmd.getColumnCount();
		
	    for (int i = 1; i <= columnsNumber; i++) 
	    {
	        valores.put(rsmd.getColumnName(i), resultset.getString(i));
	    }
	    
		switch (table)
		{
			case "Alertas": 
				o = new Alerta(valores.get("id_alerta"),valores.get("titulo_alerta"),
			    		valores.get("descricao_alerta"),valores.get("data_alerta"),valores.get("hora_alerta"));
				break;
				
			case "Animais":
				o = new Animal(valores.get("id_animal"),valores.get("nome_animal"),valores.get("raca"),valores.get("especie"),
						valores.get("data_nasc"),valores.get("id_seg"),valores.get("id_dono"));
				break;
		}
		
		return o;
	}
}
