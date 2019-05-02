

import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.mindrot.jbcrypt.BCrypt;

//import DataObjects.User;

/**
 * Servlet implementation class Login
 */
@WebServlet (urlPatterns = {"/loginowner/*", "/logout", "/loginvet/*", "/loginins/*"})
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
			response.setHeader("Access-Control-Allow-Origin", "https://preview.c9users.io");
			response.setHeader("Access-Control-Allow-Methods", "POST");
		
		    String email = "email";
		    String pass = "pass";
		    String table = "";
		    String wheremail = "";
		    String wherepass = "";
		    System.out.println(email + "\n" + pass);

		    switch(request.getRequestURI()) {
		    case "/SafePetDAI/loginowner":
		    	 String emaild = request.getParameter("email");
		    	 String passd = request.getParameter("pass");
		    	 email=emaild;
		    	 pass=passd;
		    	 table = "Donos";
		    	 wheremail = "email_dono";
		    	 wherepass = "password_dono";
		    	 System.out.println(emaild + "\n" + passd);
		   break;
		    
		    case "/SafePetDAI/loginvet":
		    	String emailv = request.getParameter("email");
		    	String passv = request.getParameter("pass");
		    	email=emailv;
		    	pass=passv;
		    	table = "Veterinarios";
		    	wheremail = "email_vet";
		    	wherepass = "pass_vet";
		    	System.out.println(emailv + "\n" + passv);
		    	break;
		    	
		    case "/SafePetDAI/loginins":
		    	String emaili = request.getParameter("email");
		    	String passi = request.getParameter("pass");
		    	email=emaili;
		    	pass=passi;
		    	table = "Seguradoras";
		    	wheremail = "email_seg";
		    	wherepass = "pass_seg";
		    	System.out.println(emaili + "\n" + passi);
		    	break;
		    }
		    
		    try {
				ConnectionDB.checkUser(email, pass, table, wheremail, wherepass);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}		   
		    }
	}
