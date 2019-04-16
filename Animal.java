

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Animal
 */
@WebServlet (urlPatterns = {"/animals/*", "/newsletters/*","/diseases/*","/vaccines/*","/observations/*"})
public class Animal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Animal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String table = "";
		switch(request.getRequestURI()) {
		case "/SafePetDAI/animals":
			table = "Animais";
		break;
		
		case "/SafePetDAI/newsletters":
			table = "Boletins";
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
				response.getWriter().append((ConnectionDB.SelectQuery(table)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String table = "";
		String colunas[] = {};
		Object valores[] = {};
		
		switch(request.getRequestURI()){
		case "/SafePetDAI/animals":
		System.out.println("FUNCIONA CARALHO");
		String id_animal = request.getParameter("id_animal");
		String nome_animal = request.getParameter("nome_animal");
		String raca = request.getParameter("raca");
		String especie = request.getParameter("especie");
		String data_nasc = request.getParameter("data_nasc");
		String id_seg = request.getParameter("id_seg");
		String id_dono = request.getParameter("id_dono");
		
		table = "Animais";
		String c[] = {"id_animal", "nome_animal", "raca", "especie", "data_nasc", "id_seg", "id_dono"};
		colunas = c;
		Object v[] = {id_animal, nome_animal, raca, especie, data_nasc, id_seg, id_dono};
		valores = v;
		break;
		
		case "/SafePetDAI/newsletters":
			System.out.println("FUNCIONA CARALHO");
			String id_bol = request.getParameter("id_bol");
			String id_animal2 = request.getParameter("id_animal");
			String id_vet = request.getParameter("id_vet");
			
			table = "Boletins";
			String a[] = {"id_bol", "id_animal", "id_vet"};
			colunas = a;
			Object b[] = {id_bol, id_animal2, id_vet};
			valores = b;
		break;
		
		case "/SafePetDAI/diseases":
			System.out.println("FUNCIONA CARALHO");
			String id_doenca = request.getParameter("id_doenca");
			String nome_doenca = request.getParameter("nome_doenca");
			String descricao_doenca = request.getParameter("descricao_doenca");
			String id_bol2 = request.getParameter("id_bol");
			
			table = "Doencas";
			String d[] = {"id_doenca", "nome_doenca", "descricao_doenca", "id_bol"};
			colunas = d;
			Object e[] = {id_doenca, nome_doenca, descricao_doenca, id_bol2};
			valores = e;
		break;
		
		case "/SafePetDAI/vaccines":
			System.out.println("FUNCIONA CARALHO");
			String id_vacina = request.getParameter("id_vacina");
			String nome_vac = request.getParameter("nome_vac");
			String descricao_vac = request.getParameter("descricao_vac");
			String data_validade = request.getParameter("data_validade");
			String id_bol3 = request.getParameter("id_bol");
			
			table = "Vacinas";
			String f[] = {"id_vacina", "nome_vac", "descricao_vac", "data_validade", "id_bol"};
			colunas = f;
			Object g[] = {id_vacina, nome_vac, descricao_vac, data_validade, id_bol3};
			valores = g;
		break;
		
		case "/SafePetDAI/observations":
			System.out.println("FUNCIONA CARALHO");
			String id_obser = request.getParameter("id_obser");
			String data_observ = request.getParameter("data_observ");
			String descricao = request.getParameter("descricao");
			String id_bol4 = request.getParameter("id_bol");
			
			table = "Observacoes";
			String t[] = {"id_obser", "data_observ", "descricao", "id_bol"};
			colunas = t;
			Object h[] = {id_obser, data_observ, descricao, id_bol4};
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
			// TODO Auto-generated method stub
			String table = "";
			String campo_id = "";
			String id= "";
			String campos[] = {};
			String valores_campos[]= {};
			String url = request.getRequestURI();
			
			//O url recebi contém parâmetros?
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
				
				table = "Animais";
				String c[] = {"nome_animal", "raca", "especie", "data_nasc", "id_seg", "id_dono"};
				campos = c;
				String v[] = {nome_animal, raca, especie, data_nasc, id_seg, id_dono};
				valores_campos = v;
				
				campo_id = "id_animal";
				id = valores.get("id_animal");
				break;
				
				case "/SafePetDAI/newsletters":
					String id_animal2 = valores.get("id_animal");
					String id_vet = valores.get("id_vet");
					
					table = "Boletins";
					String a[] = {"id_animal", "id_vet"};
					campos = a;
					String b[] = {id_animal2, id_vet};
					valores_campos = b;
					campo_id = "id_bol";
					id = valores.get("id_bol");
				break;
				
				case "/SafePetDAI/diseases":
					String nome_doenca = valores.get("nome_doenca");
					String descricao_doenca = valores.get("descricao_doenca");
					String id_bol2 = valores.get("id_bol");
					
					table = "Doencas";
					String d[] = {"nome_doenca", "descricao_doenca", "id_bol"};
					campos = d;
					String e[] = {nome_doenca, descricao_doenca, id_bol2};
					valores_campos = e;
					campo_id = "id_doenca";
					id = valores.get("id_doenca");
				break;
				
				case "/SafePetDAI/vaccines":
					String nome_vac = valores.get("nome_vac");
					String descricao_vac = valores.get("descricao_vac");
					String data_validade = valores.get("data_validade");
					String id_bol3 = valores.get("id_bol");
					
					table = "Vacinas";
					String f[] = {"nome_vac", "descricao_vac", "data_validade", "id_bol"};
					campos = f;
					String g[] = {nome_vac, descricao_vac, data_validade, id_bol3};
					valores_campos = g;
					campo_id = "id_vacina";
					id = valores.get("id_vacina");
				break;
				
				case "/SafePetDAI/observations":
					String data_observ = valores.get("data_observ");
					String descricao = valores.get("descricao");
					String id_bol4 = valores.get("id_bol");
					
					table = "Observacoes";
					String t[] = {"data_observ", "descricao", "id_bol"};
					campos = t;
					String h[] = {data_observ, descricao, id_bol4};
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
				System.out.println("O URL recebido não contém valores, UPDATE não realizado");
			}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				
			case "/SafePetDAI/newsletters":
				table = "Boletins";
				idcoluna = "id_bol";
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
