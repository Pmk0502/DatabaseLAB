package jdbcMain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class JdbcFacade {

    DbSingleton instance = DbSingleton.getInstance();

    public ResultSet executeSelectStatement(String sqlQuery){

        Connection connection = instance.getConnection();
        Statement statement = null;
        ResultSet results = null;

        try {
            if (connection != null){
                statement = connection.createStatement();
                results = statement.executeQuery(sqlQuery);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return results;
    }

    public int executeUpdateStatement(String sqlQuery){
        int nbRowsAffected = 0;
        Connection connection = instance.getConnection();
        Statement statement = null;

        try{
            if (connection != null) {
                statement = connection.createStatement();
                nbRowsAffected = statement.executeUpdate(sqlQuery);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return nbRowsAffected;

    }

    public void resultsPrinter(ResultSet results, List<String> selectedColumns) {
        try {
            if (results != null){
                for (int i=0; i<selectedColumns.size();i++) {
                    System.out.print(" - " + selectedColumns.get(i));
                }
                System.out.println();
                System.out.println();
                while(results.next()){
                    for (int j = 0; j < selectedColumns.size(); j++) {
                        System.out.print( " - " + results.getString(selectedColumns.get(j))) ;
                    }
                    System.out.println();
                }
            }else{
                System.out.println("No results found !!!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
