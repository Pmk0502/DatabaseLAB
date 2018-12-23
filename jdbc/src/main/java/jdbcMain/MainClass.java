package jdbcMain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClass {

    public static void main(String [] args){

        JdbcFacade dbFacade = new JdbcFacade();

        if (dbFacade.instance.getConnection() != null)
            System.out.println("Authentication succedded !!");


        /* Executing queries*/

        System.out.println("*******************Requete 1***************************");
        String query1 = "SELECT * FROM Departement WHERE nomDpt='Informatique' ";
        List<String> selectedColumns1 = new ArrayList<String>();
        Collections.addAll(selectedColumns1, "nomDpt", "dateCreationDpt", "adresseDpt", "telephoneDpt" );
        dbFacade.resultsPrinter(dbFacade.executeSelectStatement(query1), selectedColumns1);


        System.out.println("******************** Requete 2 **************************");
        String query2 = "SELECT matriculecher, titreart FROM Chercheur, Article WHERE auteur=matriculecher" ;
        List<String> selectedColumns2 = new ArrayList<String>();
        Collections.addAll(selectedColumns2, "matriculecher", "titreart" );
        dbFacade.resultsPrinter(dbFacade.executeSelectStatement(query2), selectedColumns2);


        System.out.println("******************** Requete 3 **************************");
        String query3 = "SELECT prenomcher, nomcher, titreart FROM Article, Chercheur " +
                "WHERE auteur=matriculeCher AND matriculeCher='M22556'";
        List<String> selectedColumns3 = new ArrayList<String>();
        Collections.addAll(selectedColumns3, "prenomcher", "nomcher", "titreart");
        dbFacade.resultsPrinter(dbFacade.executeSelectStatement(query3), selectedColumns3);


        System.out.println("******************** Requete 4 **************************");
        String query4 = "DELETE FROM Article a WHERE auteur " +
                "IN (SELECT matriculeCher FROM Chercheur c, Equipe e, Departement d WHERE  a.auteur=c.matriculeCher " +
                "AND c.nomEq=e.nomEq  AND e.nomDpt=d.nomDpt AND d.nomDpt='Mathematiques')";
        System.out.println("Number of rows affected : " + dbFacade.executeUpdateStatement(query4));


        System.out.println("******************** Requete 5 **************************");
        String query5 = "DELETE FROM Chercheur WHERE matriculeCher " +
                "IN (SELECT auteur FROM Article WHERE matriculeCher=auteur AND dateSoumission='2007-05-16')";
        System.out.println("Number of rows affected : " + dbFacade.executeUpdateStatement(query5));


        System.out.println("******************** Requete 6 **************************");
        String query6 = "UPDATE Departement SET adresseDpt='Quebec' WHERE nomDpt='Physique' " ;
        System.out.println("Number of rows affected : " + dbFacade.executeUpdateStatement(query6));


        System.out.println("******************** Requete 7 **************************");
        String query7 = "UPDATE Chercheur SET positionCher='prof' WHERE positionCher='postdoc' " +
                "AND nomEq IN (SELECT nomEq FROM Equipe e, Departement d " +
                "WHERE e.nomDpt = d.nomDpt AND d.nomDpt='Mathematiques')";
        System.out.println("Number of rows affected : " + dbFacade.executeUpdateStatement(query7));


        System.out.println("******************** Requete 8 **************************");
        String query8_1 = "INSERT INTO Departement VALUES('Medecine','2018-03-01','Gaspesie')";
        String query8_2 = "INSERT INTO Equipe (nomEq,nomDpt) VALUES ('Pediatre','Medecine')";

        System.out.println("Number of rows affected : " + dbFacade.executeUpdateStatement(query8_1));
        System.out.println("Number of rows affected : " + dbFacade.executeUpdateStatement(query8_2));
    }
}