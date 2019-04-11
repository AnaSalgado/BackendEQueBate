

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Animal
 */
@WebServlet (urlPatterns = {"/animals", "/newsletters","/diseases","/vaccines","/observations"})

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
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcoluna = "";
		String id = "";
		String table = "";
		
		switch(request.getRequestURI()) {
		case "/SafePetDAI/animals":
			String id_ani = request.getParameter("id_animal");
			table = "Animais";
			idcoluna = "id_animal";
			id = id_ani;
			System.out.println(id);
		}
		try {
			ConnectionDB.DeleteQuery(idcoluna, id, table);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
