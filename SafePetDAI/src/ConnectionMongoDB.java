

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.mongodb.MongoClient;

/**
 * Servlet implementation class ConnectionMongoDB
 */
@WebServlet("/ConnectionMongoDB")
public class ConnectionMongoDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoClient mongoClient = new MongoClient( /*"host1" , 27017*/ );
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionMongoDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/ 

}
