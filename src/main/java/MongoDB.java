import com.fazecast.jSerialComm.SerialPort;
import com.mongodb.*;

public class MongoDB {


    public static MongoClient mongoClient;
    public static DB database;
    public static DBCollection dbCollection;

    public static void main(String[] args) {

        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("dataRateDB");
        dbCollection= database.getCollection("collDB");

        // SET OBJEK
        obj obj = new obj();
        obj.setTimer(1);
        obj.setMemberID("abc");
        obj.setXp(1212);

        // INSERT KE DATABASE
        dbCollection.insert(convert(obj));

        // QUERYING
        DBObject query = new BasicDBObject();
        DBCursor cursor = dbCollection.find(query);
        System.out.println(cursor.next());

    }

    public static DBObject convert(obj obj){
        return new BasicDBObject("xp",obj.getXp()).append("timer:",obj.getTimer()).append("memberID:",obj.getMemberID());
    }
}
