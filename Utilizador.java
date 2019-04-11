

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Veterinario
 */
@WebServlet(urlPatterns = {"/vets", "/owners", "/insurers"})
public class Utilizador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Utilizador() {
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
		case "/SafePetDAI/vets": table = "Veterinarios";
			break;
		case "/SafePetDAI/owners": table = "Donos";
			break;
		case "/SafePetDAI/insurers": table = "Seguradoras";
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
		switch(request.getRequestURI()) {
		
		case "/SafePetDAI/vets" :
		System.out.println("FUNCIONA CARALHO");
		String id_vet = request.getParameter("id_vet");
		String nome_vet = request.getParameter("nome_vet");
		String data_nasc_vet = request.getParameter("data_nasc_vet");
		String pass_vet = request.getParameter("pass_vet");
		String telemovel_vet = request.getParameter("telemovel_vet");
		String morada_vet = request.getParameter("morada_vet");
		String email_vet = request.getParameter("email_vet");
		String id_seg = request.getParameter("id_seg");
		
		table = "Veterinarios";
		String c[] = {"id_vet", "nome_vet", "data_nasc_vet", "pass_vet", "telemovel_vet", "morada_vet", "email_vet", "id_seg"};
		colunas = c;
		Object v[] = {id_vet, nome_vet, data_nasc_vet, pass_vet, telemovel_vet, morada_vet, email_vet, id_seg};
		valores = v;
		break;
		
		case "/SafePetDAI/owners":
		String id_dono = request.getParameter("id_dono");
		String nome_dono = request.getParameter("nome_dono");
		String morada_dono = request.getParameter("morada_dono");
		String telemovel_dono = request.getParameter("telemovel_dono");
		String email_dono = request.getParameter("email_dono");
		String password_dono = request.getParameter("password_dono");
		
		table = "Donos";
		String a[] = {"id_dono", "nome_dono", "morada_vet", "telemovel_vet",  "email_vet", "pass_vet"};
		colunas = a;
		Object b[] = {id_dono, nome_dono, morada_dono, telemovel_dono,  email_dono, password_dono};
		valores = b;		
		
		case "/SafePetDAI/insurers":
		String id_segu = request.getParameter("id_seg");
		String nome_seg = request.getParameter("nome_seg");
		String morada_seg = request.getParameter("morada_seg");
		String telemovel_seg = request.getParameter("telemovel_seg");
		String email_seg = request.getParameter("email_seg");
		String pass_seg = request.getParameter("pass_seg");
		
		table = "Seguradoras";
		String d[] = {"id_seg", "nome_seg", "morada_seg", "telemovel_seg",  "email_seg", "pass_seg"};
		colunas = d;
		Object e[] = {id_segu, nome_seg, morada_seg, telemovel_seg,  email_seg, pass_seg};
		valores = e;
		break;
		
		
		}
		try {
			ConnectionDB.InsertQuery(table, colunas, valores);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
