

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Alerta
 */
@WebServlet (urlPatterns = {"/alerts", "/alertsituations", "/usercriteria"})
public class Alerta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Alerta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String table = "";
		
	   switch(request.getRequestURI()){
		
	   case "/SafePetDAI/alerts": 
			table = "Alertas";
			break;
			
	   case "/SafePetDAI/alertsituations": 
			table = "SituacoesAlerta";
			break;
		
	   case "/SafePetDAI/usercriteria":
			table = "CriteriosUtilizador";
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
		 
		 case "/SafePetDAI/alerts":
			 String id_alerta = request.getParameter("id_alerta");
			 String titulo_alerta = request.getParameter("titulo_alerta");
			 String descricao_alerta = request.getParameter("descricao_alerta");
			 String data_alerta = request.getParameter("data_alerta");
			 String hora_alerta = request.getParameter("hora_alert");
			 
	    table = "Alertas";
	    String c[] = {"id_alerta", "titulo_alerta", "descricao_alerta", "data_alerta", "hora_alerta"};
	    colunas = c;
	    Object v[] = {id_alerta, titulo_alerta, descricao_alerta, data_alerta, hora_alerta};
	    valores = v;
	    break;
	    
	    
			 
		 case "/SafePetDAI/alertsituations":
			 String id_sit = request.getParameter("id_sit");
			 String titulo_sit = request.getParameter("titulo_sit");
			 String descricao_sit= request.getParameter("descricao_sit");
			 String id_seg = request.getParameter("id_seg");
			 
		table = "SituacoesAlerta";
		String y[] = {"id_sit","titulo_sit", "descricao_sit", "id_seg"};
		colunas = y;
		Object z[] = {id_sit, titulo_sit, descricao_sit, id_seg};
		valores = z;
		break;
		
		 
		 
		 case "/SafePetDAI/usercriteria":
			 String id_animal = request.getParameter("id_animal");
			 String ritmo_min = request.getParameter("ritmo_min");
			 String ritmo_max = request.getParameter("ritmo_max");
			 String raio = request.getParameter("raio");
			 String latitude = request.getParameter("latitude");
			 String longitude = request.getParameter("longitude");
		 
			 table = "CriteriosUtilizador";
			 String u[] = {"id_animal","ritmo_min","ritmo_max", "raio", "latitude", "longitude"};
			 colunas = u;
			 Object h[]= {id_animal, ritmo_min, ritmo_max, raio, latitude, longitude};
			 valores = h;
			 break;
			 
		 }
	
		 
		 try {
			 ConnectionDB.InsertQuery(table, colunas, valores);
		 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		 
		 e.printStackTrace();	 
	}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
