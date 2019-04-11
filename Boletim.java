

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet (urlPatterns = {"/newsletters","/diseases","/vaccines","/observations"})


public class Boletim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Boletim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI())
		{
		case "/SafePetDAI/newsletters":
			
			try {
				response.getWriter().append((ConnectionDB.SelectQuery("Boletins")));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/SafePetDAI/diseases":
			
			try {
				response.getWriter().append((ConnectionDB.SelectQuery("Doencas")));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Post: " + request.getParameter("adotada"));
		System.out.println("FUNCIONA CARALHO");
		String id_bol = request.getParameter("id_bol");
		String id_animal = request.getParameter("id_animal");
		String id_vet = request.getParameter("id_vet");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(id_bol);
		out.close();
		System.out.println("FUNCIONA CARALHO");
		
		String s[] = {"adotada","isabel"};
		Object o[] = {"hello", 69};
		try {
			ConnectionDB.InsertQuery("ola", s, o);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
}
