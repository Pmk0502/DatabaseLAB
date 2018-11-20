package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClass {

    public static void main(String [] args){
        String server = "jdbc:postgresql://127.0.0.1/TP5";
        String user = "postgres";
        String password = "passer";
        String driver = "org.postgresql.Driver";

        Connexion connexion = new Connexion(driver, server, user, password);

        /* Get connection*/
        if (connect(connexion) != null){
            System.out.println("Authentication successfull !! ");
        }


        /* Executing queries*/

        System.out.println("*******************Requete 1***************************");
            String query1 = "SELECT * FROM Departement WHERE nomDpt='Informatique' ";
        List<String> columnToRetrieve1 = new ArrayList<String>();
        Collections.addAll(columnToRetrieve1, "nomDpt", "dateCreationDpt", "adresseDpt", "telephoneDpt" );
        resultsPrinter(executeQuery(connexion, query1), columnToRetrieve1);

        System.out.println("******************** Requete 2 **************************");
        String query2 = "SELECT matriculecher, titreart FROM Chercheur, Article WHERE auteur=matriculecher" ;
        List<String> columnToRetrieve2 = new ArrayList<String>();
        Collections.addAll(columnToRetrieve2, "matriculecher", "titreart" );
        resultsPrinter(executeQuery(connexion, query2), columnToRetrieve2);

        System.out.println("******************** Requete 3 **************************");
        String query3 = "SELECT prenomcher, nomcher, titreart FROM Article, Chercheur " +
                "WHERE auteur=matriculeCher AND matriculeCher='M22556'";
        List<String> columnToRetrieve3 = new ArrayList<String>();
        Collections.addAll(columnToRetrieve3, "prenomcher", "nomcher", "titreart");
        resultsPrinter(executeQuery(connexion, query3), columnToRetrieve3 );

        System.out.println("******************** Requete 4 **************************");
        String query4 = "DELETE FROM Article a WHERE auteur " +
                "IN (SELECT matriculeCher FROM Chercheur c, Equipe e, Departement d WHERE  a.auteur=c.matriculeCher " +
                "AND c.nomEq=e.nomEq  AND e.nomDpt=d.nomDpt AND d.nomDpt='Mathematiques')";
        executeUpdate(connexion, query4);

        System.out.println("******************** Requete 5 **************************");
        String query5 = "DELETE FROM Chercheur WHERE matriculeCher " +
                "IN (SELECT auteur FROM Article WHERE matriculeCher=auteur AND dateSoumission='2007-05-16')";
        executeUpdate(connexion, query5);

        System.out.println("******************** Requete 6 **************************");
        String query6 = "UPDATE Departement SET adresseDpt='Quebec' WHERE nomDpt='Physique' " ;
        executeUpdate(connexion, query6);

        System.out.println("******************** Requete 7 **************************");
        String query7 = "UPDATE Chercheur SET positionCher='prof' WHERE positionCher='postdoc' " +
                "AND nomEq IN (SELECT nomEq FROM Equipe e, Departement d " +
                "WHERE e.nomDpt = d.nomDpt AND d.nomDpt='Mathematiques')";
        executeUpdate(connexion, query7);

        System.out.println("******************** Requete 8 **************************");
        String query8_1 = "INSERT INTO Departement VALUES('Medecine','2018-03-01','Gaspesie')";
        String query8_2 = "INSERT INTO Equipe (nomEq,nomDpt) VALUES ('Pediatre','Medecine')";

        executeUpdate(connexion, query8_1);
        executeUpdate(connexion, query8_2);
    }

    static Connection connect(Connexion c ){
        Connection connection = null;
        try {
            Class.forName(c.getDriver());
            connection = DriverManager.getConnection(c.getServer(), c.getUser(), c.getPassword());
        }catch (SQLException  e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    static int update(Connexion c, String query){
        Connection conn = connect(c);
        Statement statement = null;
        ResultSet result = null;
        int nbRow = 0;
        try{
            if (conn != null) {
                statement = conn.createStatement();
                nbRow = statement.executeUpdate(query);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return nbRow;
    }


    static ResultSet executeQuery(Connexion c, String query){

        Connection conn = connect(c);
        Statement statement = null;
        ResultSet result = null;

        try {
            if (conn != null){
                statement = conn.createStatement();
                result = statement.executeQuery(query);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }


    public static void resultsPrinter(ResultSet results, List<String> columns){
        try {
            if (results != null){
                for (int i=0; i<columns.size();i++) {
                    System.out.print(" - " + columns.get(i));
                }
                System.out.println();
                System.out.println();
                while(results.next()){
                    for (int j = 0; j < columns.size(); j++) {
                        System.out.print( " - " + results.getString(columns.get(j))) ;
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

    public static void executeUpdate(Connexion connexion, String query3){
       int nbRows = update(connexion, query3);
       System.out.println("Updated successfully #" +nbRows+  " row(s)");
    }


}
