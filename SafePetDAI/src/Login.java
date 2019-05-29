

import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.mindrot.jbcrypt.BCrypt;

//import DataObjects.User;

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
		//doGet(request, response);
			//CORS
		/*	response.setHeader("Access-Control-Allow-Origin", "https://preview.c9users.io");
			response.setHeader("Access-Control-Allow-Methods", "POST");
		
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String email = "teste@teste.com";
			String pass = "1234";
		    System.out.println(email +"\n" + pass);
		    String table = "Donos";
		    String wheremail = "email_dono";
		    String wherepass = "password_dono";
		    try {
				ConnectionDB.checkUser(email, pass);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}	*/
		
		//setAccessControlHeaders(response);
		String email = "teste@teste.com";//request.getParameter("email");
		String pass = "1234";//request.getParameter("pass");
		try {
			response.setContentType("application/json");
			ConnectionDB.checkUser(email, pass);
			if(ConnectionDB.checkUser(email, pass)) {
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
