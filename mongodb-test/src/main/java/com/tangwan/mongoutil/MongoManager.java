package com.tangwan.mongoutil;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class MongoManager {
	public static Logger logger = Logger.getLogger(MongoManager.class);
    private Mongo mongo = null;
    private DBCollection collection = null;
	/** 读取日志mongo配置文件 */
	private static final ResourceBundle mg = ResourceBundle.getBundle("mongo");
	/** IP地址 */
	private static final String HOST = mg.getString("mongo.host");
	/** 端口 */
	private static final String PORT = mg.getString("mongo.port");
	/** 用户 */
	private static final String USERNAME = mg.getString("mongo.uname");
	/** 密码 */
	private static final String PASSWORD = mg.getString("mongo.upass");
	/** 数据库 */
	private static final String DATABASE = mg.getString("mongo.dbname");
	/** 数据库 */
	private static final String CONNECTIONNAME = mg.getString("mongo.collectionName");
	/**sockt超时时间 */
	private static final String SOTIMEOUT = mg.getString("mongo.socketTimeout");
	/**mongo连接数 */
	private static final String CONNECTIONPERHOST = mg.getString("mongo.connectionsPerHost");
	/**线程连接数 */
	private static final String THEADALLOW = mg.getString("mongo.threadsAllowedToBlockForConnectionMultiplier");
 
	public MongoManager() {
    	try{
    	 List<ServerAddress> addresses = getServerAddresses(HOST, PORT);
		 int soTimeOut = Integer.parseInt(SOTIMEOUT);
		 int connectionsPerHost = Integer.parseInt(CONNECTIONPERHOST);
		 int threadsAllowedToBlockForConnectionMultiplier = Integer.parseInt(THEADALLOW);
    	 
    	 mongo = getMongo(addresses, soTimeOut, connectionsPerHost, true, threadsAllowedToBlockForConnectionMultiplier);
    	 DB db = getDatabase(mongo, DATABASE);
    	 if (USERNAME != null && USERNAME.trim().length() > 0) {
             if (!db.authenticate(USERNAME, PASSWORD.toCharArray())) {
                 throw new RuntimeException("Unable to authenticate with MongoDB server.");
             }
          //   PASSWORD = null;
         }
         setCollection(db.getCollection(CONNECTIONNAME));
    	}catch(Exception e){
    		throw e;
    	}
    }
    public void save( DBObject dbObject) {
        this.collection.save(dbObject);
    }
    public void delete(DBObject query) {
    	 this.collection.remove(query);
    }
    public List<DBObject> find( DBObject query, DBObject fields, int limit) {
        List<DBObject> list = new LinkedList<>();
         DBCursor cursor =  this.collection.find(query, fields).limit(limit);
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list.size() > 0 ? list : null;
    }
 
    public List<DBObject> find(DBObject query, DBObject fields, DBObject orderBy, int pageNum, int pageSize) {
        List<DBObject> list = new ArrayList<>();
        DBCursor cursor =  this.collection.find(query, fields).skip((pageNum - 1) * pageSize).limit(pageSize).sort(orderBy);
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list.size() > 0 ? list : null;
    }
 
    public DBObject findOne(DBObject query, DBObject fields) {
        return this.collection.findOne(query, fields);
    }
 
    public void update( DBObject query, DBObject update, boolean upsert, boolean multi) {
    	this.collection.update(query, update, upsert, multi);
    }
 
    public long count(DBObject query) {
        return this.collection.count(query);
    }
    
    
    private List<ServerAddress> getServerAddresses(String hostname, String port) {
        List<ServerAddress> addresses = new ArrayList<ServerAddress>();

        String[] hosts = hostname.split(",");
        String[] ports = port.split(",");

        if (ports.length != 1 && ports.length != hosts.length) {
        	logger.error("配置posts与hosts不对等");
        } else {
            List<Integer> portNums = getPortNums(ports);
            // Validate number of ports again after parsing
            if (portNums.size() != 1 && portNums.size() != hosts.length) {
            	logger.error("配置posts与hosts不对等");
            } else {
                boolean onePort = (portNums.size() == 1);

                int i = 0;
                for (String host : hosts) {
                    int portNum = (onePort) ? portNums.get(0) : portNums.get(i);
                    try {
                        addresses.add(new ServerAddress(host.trim(), portNum));
                    } catch (UnknownHostException e) {
                    	logger.error("MongoDB appender hostname property contains unknown host");
                    }
                    i++;
                }
            }
        }
        return addresses;
    }
    private List<Integer> getPortNums(String[] ports) {
        List<Integer> portNums = new ArrayList<Integer>();

        for (String port : ports) {
            try {
                Integer portNum = Integer.valueOf(port.trim());
                if (portNum < 0) {
                    logger.error("MongoDB appender port property can't contain a negative integer");
                } else {
                    portNums.add(portNum);
                }
            } catch (NumberFormatException e) {
            	 logger.error("MongoDB appender can't parse a port property value into an integer");
            }
        }
        return portNums;
    }
    
    private Mongo getMongo(List<ServerAddress> addresses,int timeoutMS,int connections,boolean keepAlive,int threads) {
    	MongoOptions options=new MongoOptions();
    	options.setSocketTimeout(timeoutMS);
    	options.setConnectionsPerHost(connections);
    	options.setSocketKeepAlive(keepAlive);
    	options.setThreadsAllowedToBlockForConnectionMultiplier(threads);
    	
    	if (addresses.size() < 2) {
            return new Mongo(addresses.get(0),options);
        } else {
            // Replica set
            return new Mongo(addresses,options);
        }
    }
    private DB getDatabase(Mongo mongo, String databaseName) {
        return mongo.getDB(databaseName);
    }
    /**
     * @see org.apache.log4j.Appender#close()
     */
    private void setCollection(final DBCollection collection) {
        assert collection != null : "collection must not be null";
        this.collection = collection;
    }
    
    public void close() {
        if (mongo != null) {
            collection = null;
            mongo.close();
        }
    }
}