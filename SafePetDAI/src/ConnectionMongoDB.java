
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class ConnectionMongoDB {
	static MongoClient mongoClient = new MongoClient( new MongoClientURI("mongodb://nuno123:jer0nim0@cluster1-isqt6.mongodb.net/test?retryWrites=true"));
	static MongoDatabase db = mongoClient.getDatabase("nuno123");
	
	public static void testConnection(){
		System.out.println(db.toString());
	}
}
