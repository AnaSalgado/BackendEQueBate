import java.util.HashMap;
import java.util.Map;

public class URLHelper 
{
	/**
	 * 
	 * @param url Url recebido atrav�s de um pedido HTTP
	 * @return Tabela que cont�m os par�metros e valores enviados pelo url, inclui tamb�m a rota original
	 */
	public static Map<String,String> UrlValues(String url)
	{
		Map<String,String> valores = new HashMap<String, String>();	//Criar a tabela onde v�o ser guardados os par�metros
		
		String split_url[] = url.split("/");	//Split para descobrir a rota
		String route = "";
		
	    String[] url_split = url.split("/!");	//Split para receber os valores
	    System.out.println(url_split[1]);
	    url_split = url_split[1].split("&");	//Split para separar cada valor
		
	    //Associar na tabela cada par�metro ao seu valor
	    for (int i=0; i<url_split.length; i++)
	    { 
	    	String[] url_values = url_split[i].split("=");
	    	url_values[0] = url_values[0].replaceAll("/", "");
	    	valores.put(url_values[0], url_values[1]);
	    	System.out.println(url_values[0] + ": " + url_values[1]);
	    }
	    
	    //Descobrir a rota original do pedido
	    for (int i=0; i < split_url.length; i++) {
			if (i < 2)
				route += "/" + split_url[i+1];
		}
	    
	    valores.put("route", route);	//Adicionar a rota � tabela
	    
		return valores;
	}
	
	public static boolean UrlContainsValues(String url)
	{
		boolean contains_values = url.split("/!").length > 1;
		return contains_values;
	}
}
