import model.Departement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emF = null;
        EntityManager em = null;

        if (emF == null) {
            emF = Persistence.createEntityManagerFactory("tp5");
            em = emF.createEntityManager();
            System.out.println("Authentication successfull !!! ");
        }

        /* Requetes */
        Departement departement = em.find(Departement.class, "Informatique");
        System.out.println("Nom -> " + departement.getNomdpt());
        System.out.println("Adresse -> " + departement.getAdressedpt());
        System.out.println("Date de creation -> " + departement.getDatecreationdpt());
        System.out.println("Telephone -> " + departement.getTelephonedpt());


    }
}
