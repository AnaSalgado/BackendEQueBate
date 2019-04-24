
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

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class Veterinario
 */
@WebServlet(urlPatterns = {"/vets/*", "/owners/*", "/insurers/*"})
public class UtilizadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilizadorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		switch(route) {
		case "/SafePetDAI/vets": table = "Veterinarios";
			break;
		case "/SafePetDAI/owners": table = "Donos";
			break;
		case "/SafePetDAI/insurers": table = "Seguradoras";
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

	 public String hashPassword(String password_plaintext) {
	       String salt = BCrypt.gensalt(10);
		   String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
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
		switch(request.getRequestURI()) {
		
		case "/SafePetDAI/vets" :
//os que estao em "" sao para dar ao front
		String nome_vet = request.getParameter("nome_vet");
		String pass_vet = request.getParameter("pass_vet");
		String telemovel_vet = request.getParameter("telemovel_vet");
		String morada_vet = request.getParameter("morada_vet");
		String email_vet = request.getParameter("email_vet");
		String id_seg = request.getParameter("id_seg");
		String estado_vet = request.getParameter("estado");
		
		System.out.println(nome_vet + "\n" + pass_vet + "\n" + telemovel_vet + "\n" + morada_vet + "\n" + email_vet + "\n" + id_seg + "\n" + estado_vet);
		
		table = "Veterinarios";
		//nomes da BD
		String c[] = {"nome_vet", "pass_vet", "telemovel_vet", "morada_vet", "email_vet", "id_seg", "estado"};
		colunas = c;
		Object v[] = {nome_vet, hashPassword(pass_vet), telemovel_vet, morada_vet, email_vet, id_seg, estado_vet};
		valores = v;
		break;
		
		case "/SafePetDAI/owners":
		String nome_dono = request.getParameter("nome_dono");
		String morada_dono = request.getParameter("morada_dono");
		String telemovel_dono = request.getParameter("telemovel_dono");
		String email_dono = request.getParameter("email_dono");
		String password_dono = request.getParameter("password_dono");
		String estado_dono = request.getParameter("estado");
		
		table = "Donos";
		String a[] = {"nome_dono", "morada_dono", "telemovel_dono",  "email_dono", "password_dono", "estado"};
		colunas = a;
		Object b[] = {nome_dono, morada_dono, telemovel_dono,  email_dono, hashPassword(password_dono), estado_dono};
		valores = b;		
		break;
		
		case "/SafePetDAI/insurers":
		String nome_seg = request.getParameter("nome_seg");
		String morada_seg = request.getParameter("morada_seg");
		String telemovel_seg = request.getParameter("telemovel_seg");
		String email_seg = request.getParameter("email_seg");
		String pass_seg = request.getParameter("pass_seg");
		String estado_seg = request.getParameter("estado");
		
		table = "Seguradoras";
		String d[] = {"nome_seg", "morada_seg", "telefone_seg",  "email_seg", "pass_seg", "estado"};
		colunas = d;
		Object e[] = {nome_seg, morada_seg, telemovel_seg,  email_seg, hashPassword(pass_seg), estado_seg};
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
		
		//O url recebi contém parâmetros?
		if (URLHelper.UrlContainsValues(url))
		{
			Map<String, String> valores = URLHelper.UrlValues(url);
		    String route = valores.get("route");
		    
		    
		    switch(route){

			case "/SafePetDAI/vets" :
			String nome_vet = valores.get("nome_vet");
			String pass_vet = valores.get("pass_vet");
			String telemovel_vet = valores.get("telemovel_vet");
			String morada_vet = valores.get("morada_vet");
			String email_vet = valores.get("email_vet");
			String id_seg = valores.get("id_seg");
			String estado_vet = valores.get("estado");
			
			table = "Veterinarios";
			//nomes da BD

			String c[] = {"nome_vet","pass_vet", "telemovel_vet", "morada_vet", "email_vet", "id_seg", "estado"};
			campos = c;
			String v[] = {nome_vet, hashPassword(pass_vet), telemovel_vet, morada_vet, email_vet, id_seg, estado_vet};
			valores_campos = v;
			
			campo_id = "id_vet";
			id = valores.get("id_vet");
			break;
			
			case "/SafePetDAI/owners":
			String nome_dono = valores.get("nome_dono");
			String morada_dono = valores.get("morada_dono");
			String telemovel_dono = valores.get("telemovel_dono");
			String email_dono = valores.get("email_dono");
			String password_dono = valores.get("password_dono");
			String estado_dono = valores.get("estado");
			
			table = "Donos";
			System.out.println(password_dono);

			String a[] = {"nome_dono", "morada_dono", "telemovel_dono",  "email_dono", "estado"};
			campos = a;
			String b[] = {nome_dono, morada_dono, telemovel_dono, email_dono, estado_dono};
			valores_campos = b;	
			
			campo_id = "id_dono";
			id = valores.get("id_dono");
			break;
			
			case "/SafePetDAI/insurers":
			String nome_seg = valores.get("nome_seg");
			String morada_seg = valores.get("morada_seg");
			String telemovel_seg = valores.get("telemovel_seg");
			String email_seg = valores.get("email_seg");
			String pass_seg = valores.get("pass_seg");
			String estado_seg = valores.get("estado");
			
			table = "Seguradoras";
			
			String d[] = {"nome_seg", "morada_seg", "telefone_seg",  "email_seg", "pass_seg", "estado"};
			campos = d;
			String e[] = {nome_seg, morada_seg, telemovel_seg,  email_seg, hashPassword(pass_seg), estado_seg};
			valores_campos = e;
			
			campo_id = "id_seg";
			id = valores.get("id_seg");
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
	
	protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			case "/SafePetDAI/vets":
				table = "Veterinarios";
				idcoluna = "id_vet";
				id = valores.get(idcoluna);
				System.out.println(id);
				break;
				
			case "/SafePetDAI/owners":
				table = "Donos";
				idcoluna = "id_dono";
				id = valores.get(idcoluna);
				System.out.println(id);
				break;
				
			case "/SafePetDAI/insurers":
				table = "Seguradoras";
				idcoluna = "id_seg";
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