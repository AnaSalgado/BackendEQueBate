

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet (urlPatterns = {"/login/*"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			//CORS
		response.setHeader("Access-Control-Allow-Origin", "https://preview.c9users.io");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String wheremail = "";
		String wherepass = "";
		String table = request.getParameter("table");
		switch (table){
		case "Donos":
			wheremail = "email_dono";
			wherepass = "password_dono";
			break;
		case "Veterinarios":
			wheremail = "email_vet";
			wherepass = "pass_vet";
			break;
		case "Seguradoras":
			wheremail = "email_seg";
			wherepass = "pass_seg";
			break;
		}
		try {
			response.setContentType("application/json");
			ConnectionDB.checkUser(email, pass, wheremail, wherepass, table);
			if(ConnectionDB.checkUser(email, pass, wheremail, wherepass, table)) {
				HttpSession oldSession = request.getSession(false);
				if(oldSession != null) {
					oldSession.invalidate();
				}
				HttpSession newSession = request.getSession(true);
				newSession.setMaxInactiveInterval(30*60);
				Cookie message = new Cookie("message", "Welcome");
				response.addCookie(message);
				message.setSecure(true);
			}
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	}
