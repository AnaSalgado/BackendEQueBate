

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Animal_servlet
 */
@WebServlet (urlPatterns = {"/animals/*","/diseases/*","/vaccines/*","/observations/*"})
public class AnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//CORS
		response.setHeader("Access-Control-Allow-Origin", "https://preview.c9users.io");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		
		// TODO Auto-generated method stub
		String table = "";
		ArrayList<String> campos = new ArrayList<String>();
		ArrayList<Object> valores_campos = new ArrayList<Object>();
		String url = request.getRequestURI();
		String route = url;
		
		Map<String, String> valores = new HashMap<String,String>();
		boolean SearchByValue = URLHelper.UrlContainsValues(url);
		
		if (SearchByValue) {
			valores = URLHelper.UrlValues(url);
		    route = valores.get("route");
		    
			for(int i = 0; i < valores.keySet().size(); i++)
			{
				if (!valores.keySet().toArray()[i].equals("route"))
				{
					campos.add((String) valores.keySet().toArray()[i]);
					valores_campos.add(valores.values().toArray()[i]);
				}
			}
		}
		
		switch(route) 
		{
			case "/SafePetDAI/animals":
				table = "Animais";
			break;
				
			case "/SafePetDAI/diseases":
				table = "Doencas";
			break;
			
			case "/SafePetDAI/vaccines":
				table = "Vacinas";
			break;
				
			case "/SafePetDAI/observations":
				table = "Observacoes";
			break;
		}
		try {
			if (!SearchByValue)
				response.getWriter().append((ConnectionDB.SelectQuery(table)));
			else
				response.getWriter().append((ConnectionDB.SelectWhereQuery(table, campos.toArray(new String[campos.size()]), valores_campos.toArray())));
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CORS
		response.setHeader("Access-Control-Allow-Origin", "https://preview.c9users.io");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		
		// TODO Auto-generated method stub
		String table = "";
		String colunas[] = {};
		Object valores[] = {};
		
		switch(request.getRequestURI()){
		case "/SafePetDAI/animals":
		String nome_animal = request.getParameter("nome_animal");
		String raca = request.getParameter("raca");
		String especie = request.getParameter("especie");
		String data_nasc = request.getParameter("data_nasc");
		String id_seg = request.getParameter("id_seg");
		String id_dono = request.getParameter("id_dono");
		String id_vet = request.getParameter("id_vet");
		String ativo = request.getParameter("ativo");
		String genero = request.getParameter("genero");
		
		table = "Animais";
		String c[] = {"nome_animal", "raca", "especie", "data_nasc", "id_seg", "id_dono", "id_vet", "ativo", "genero"};
		colunas = c;
		Object v[] = {nome_animal, raca, especie, data_nasc, id_seg, id_dono, id_vet, ativo, genero};
		valores = v;
		break;
		
		case "/SafePetDAI/diseases":
			String nome_doenca = request.getParameter("nome_doenca");
			String descricao_doenca = request.getParameter("descricao_doenca");
			String id_animal = request.getParameter("id_animal");
			
			table = "Doencas";
			String d[] = {"nome_doenca", "descricao_doenca", "id_animal"};
			colunas = d;
			Object e[] = {nome_doenca, descricao_doenca, id_animal};
			valores = e;
		break;
		
		case "/SafePetDAI/vaccines":
			String nome_vac = request.getParameter("nome_vac");
			String descricao_vac = request.getParameter("descricao_vac");
			String data_validade = request.getParameter("data_validade");
			String id_animal2 = request.getParameter("id_animal");
			
			table = "Vacinas";
			String f[] = {"nome_vac", "descricao_vac", "data_validade", "id_animal"};
			colunas = f;
			Object g[] = {nome_vac, descricao_vac, data_validade, id_animal2};
			valores = g;
		break;
		
		case "/SafePetDAI/observations":
			String data_observ = request.getParameter("data_observ");
			String descricao = request.getParameter("descricao");
			String id_animal3 = request.getParameter("id_animal");
			
			table = "Observacoes";
			String t[] = {"data_observ", "descricao", "id_animal"};
			colunas = t;
			Object h[] = {data_observ, descricao, id_animal3};
			valores = h;
		break;
		}
		try {
			ConnectionDB.InsertQuery(table, colunas, valores);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//CORS
			response.setHeader("Access-Control-Allow-Origin", "https://preview.c9users.io");
			response.setHeader("Access-Control-Allow-Methods", "PUT");
		
			// TODO Auto-generated method stub
			String table = "";
			String campo_id = "";
			String id= "";
			String campos[] = {};
			String valores_campos[]= {};
			String url = request.getRequestURI();
			
			//O url recebi cont�m par�metros?
			if (URLHelper.UrlContainsValues(url))
			{
				Map<String, String> valores = URLHelper.UrlValues(url);
			    String route = valores.get("route");
			    
			    
			    switch(route){
				case "/SafePetDAI/animals":
				String nome_animal = valores.get("nome_animal");
				String raca = valores.get("raca");
				String especie = valores.get("especie");
				String data_nasc = valores.get("data_nasc");
				String id_seg = valores.get("id_seg");
				String id_dono = valores.get("id_dono");
				String id_vet = valores.get("id_vet");
				String ativo = valores.get("ativo");
				String genero = valores.get("genero");
				
				table = "Animais";
				String c[] = {"nome_animal", "raca", "especie", "data_nasc", "id_seg", "id_dono", "id_vet", "ativo", "genero"};
				campos = c;
				String v[] = {nome_animal, raca, especie, data_nasc, id_seg, id_dono, id_vet, ativo, genero};
				valores_campos = v;
				
				campo_id = "id_animal";
				id = valores.get("id_animal");
				break;
				
				case "/SafePetDAI/diseases":
					String nome_doenca = valores.get("nome_doenca");
					String descricao_doenca = valores.get("descricao_doenca");
					String id_animal = valores.get("id_animail");
					
					table = "Doencas";
					String d[] = {"nome_doenca", "descricao_doenca", "id_animal"};
					campos = d;
					String e[] = {nome_doenca, descricao_doenca, id_animal};
					valores_campos = e;
					campo_id = "id_doenca";
					id = valores.get("id_doenca");
				break;
				
				case "/SafePetDAI/vaccines":
					String nome_vac = valores.get("nome_vac");
					String descricao_vac = valores.get("descricao_vac");
					String data_validade = valores.get("data_validade");
					String id_animal2 = valores.get("id_animal");
					
					table = "Vacinas";
					String f[] = {"nome_vac", "descricao_vac", "data_validade", "id_animal"};
					campos = f;
					String g[] = {nome_vac, descricao_vac, data_validade, id_animal2};
					valores_campos = g;
					campo_id = "id_vacina";
					id = valores.get("id_vacina");
				break;
				
				case "/SafePetDAI/observations":
					String data_observ = valores.get("data_observ");
					String descricao = valores.get("descricao");
					String id_animal3 = valores.get("id_animal");
					
					table = "Observacoes";
					String t[] = {"data_observ", "descricao", "id_animal"};
					campos = t;
					String h[] = {data_observ, descricao, id_animal3};
					valores_campos = h;
					campo_id = "id_obser";
					id = valores.get("id_obser");
				break;
				    }
		
				
				
				try {
					 ConnectionDB.UpdateQuery(table, campos, valores_campos, campo_id, id);
				 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				 
				 e.printStackTrace();
				 }
			}
			else
			{
				System.out.println("O URL recebido n�o cont�m valores, UPDATE n�o realizado");
			}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CORS
		response.setHeader("Access-Control-Allow-Origin", "https://preview.c9users.io");
		response.setHeader("Access-Control-Allow-Methods", "DELETE");
		
		
		String idcoluna = "";
		String id = "";
		String table = "";
		String url = request.getRequestURI();
		
		Map<String, String> valores = URLHelper.UrlValues(url);
	    String route = valores.get("route");
		
		System.out.println(route);
		System.out.println(id);
		switch(route) 
		{
			case "/SafePetDAI/animals":
				table = "Animais";
				idcoluna = "id_animal";
				id = valores.get(idcoluna);
				System.out.println(id);
				break;
				
			case "/SafePetDAI/diseases":
				table = "Doencas";
				idcoluna = "id_doenca";
				id = valores.get(idcoluna);
				System.out.println(id);
				break;
				
			case "/SafePetDAI/vaccines":
				table = "Vacinas";
				idcoluna = "id_vacina";
				id = valores.get(idcoluna);
				System.out.println(id);
				break;
				
			case "/SafePetDAI/observations":
				table = "Observacoes";
				idcoluna = "id_obser";
				id = valores.get(idcoluna);
				System.out.println(id);
				break;
		}
		
		try {
			ConnectionDB.DeleteQuery(idcoluna, id, table);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}