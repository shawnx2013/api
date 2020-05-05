package Core;

import java.sql.*;

public class Database {
    public Connection conn = null;
    private final String DRIVER;
    private final String URI;
    public Database(){
        DRIVER = "org.sqlite.JDBC";
        URI = "jdbc:sqlite:chinook_simplified.sqlite";
        //URI = "jdbc:sqlite::memory:";
    }

    public boolean connect(){
        try {
            Class.forName(DRIVER);
        }
        catch(ClassNotFoundException nfe) {
            nfe.printStackTrace();
            return false;
        }
        try{
            conn = DriverManager.getConnection(URI);
            //System.out.println("Database Connected");
            return true;
        }catch(SQLException sqle){
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(sqle.getMessage());
            return false;
        }
    }

    public boolean close(){
        try{
            if(!conn.isClosed()){
                conn.close();
                //System.out.println("Database Disconnected");
                return true;
            }
            else{
                System.out.println("Database connection was not opened");
                return false;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

}
