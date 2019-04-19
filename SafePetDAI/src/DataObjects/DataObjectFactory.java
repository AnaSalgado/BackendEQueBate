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
			    		valores.get("descricao_alerta"),valores.get("data_alerta"),
			    		valores.get("hora_alerta"),valores.get("id_dono"));
				break;
				
			case "Animais":
				o = new Animal(valores.get("id_animal"),valores.get("nome_animal"),valores.get("raca"),valores.get("especie"),
						valores.get("data_nasc"),valores.get("id_seg"),valores.get("id_dono"),valores.get("ativo"));
				break;
				
			case "Boletins":
				o = new Boletim(valores.get("id_bol"),valores.get("id_animal"),valores.get("id_vet"));
				break;
				
			case "CriteriosUtilizador":
				o = new CriterioUtilizador(valores.get("id_animal"),valores.get("ritmo_min"),valores.get("ritmo_max"),
						valores.get("raio"),valores.get("latitude"),valores.get("longitude"));
				break;
				
			case "Doencas":
				o = new Doenca(valores.get("id_doenca"),valores.get("nome_doenca"),valores.get("descricao_doenca"),valores.get("id_bol"));
				break;
				
			case "Donos":
				o = new Dono(valores.get("email_dono"),valores.get("password_dono"),valores.get("id_dono"),valores.get("nome_dono"),
						valores.get("morada_dono"),valores.get("telemovel_dono"),valores.get("estado"));
				break;
	
			case "Observacoes":
				o = new Observacao(valores.get("id_obser"),valores.get("data_observ"),valores.get("descricao"),valores.get("id_bol"));
				break;
	
			case "Seguradoras":
				o = new Seguradora(valores.get("email_seg"),valores.get("pass_seg"),valores.get("id_seg"),valores.get("nome_seg"),
						valores.get("morada_seg"),valores.get("telefone_seg"),valores.get("estado"));
				break;
	
			case "SituacoesAlerta":
				o = new SituacaoAlerta(valores.get("id_sit"),valores.get("titulo_sit"),valores.get("descricao_sit"),valores.get("id_seg"));
				break;
	
			case "Vacinas":
				o = new Vacina(valores.get("id_vacina"),valores.get("nome_vac"),valores.get("descricao_vac"),valores.get("data_validade"),valores.get("id_bol"));
				break;
	
			case "Veterinarios":
				o = new Veterinario(valores.get("email_vet"),valores.get("pass_vet"),valores.get("id_vet"),valores.get("nome_vet"),
						valores.get("data_nasc_vet"),valores.get("telemovel_vet"),valores.get("morada_vet"),valores.get("id_seg"),valores.get("estado"));
				break;
		}
		
		return o;
	}
}