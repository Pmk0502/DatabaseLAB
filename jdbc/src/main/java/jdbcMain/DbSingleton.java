package jdbcMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingleton {

    private static DbSingleton instance = null;
    private final String DB_URL = "jdbc:postgresql://127.0.0.1/TP5";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "passer";
    private final String DRIVER = "org.postgresql.Driver";


    private DbSingleton(){}

    public static DbSingleton getInstance(){
        if (instance == null)
            instance = new DbSingleton();
        return instance;
    }

    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(this.DRIVER);
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER,this.DB_PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
