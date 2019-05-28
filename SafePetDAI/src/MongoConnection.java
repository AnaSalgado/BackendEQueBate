import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnection {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		try {
			
			MongoClient mongoClient = new MongoClient ("localhost", 27017); //hostname and port
			DB db = mongoClient.getDatabase("TEST");
			DBCollection coll = db.getCollection("SafePet");
			System.out.println("Connected to Database");
		
		} catch (Exception e)
		{
	    System.out.println(e);
	   }
         System.out.println("Server is ready");
          }
              }