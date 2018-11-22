import model.Chercheur;
import model.Departement;
import model.Equipe;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) {


        //INIT SESSION

        EntityManagerFactory emF = null;
        EntityManager em = null;

        if (emF == null) {
            emF = Persistence.createEntityManagerFactory("tp5");
            em = emF.createEntityManager();
            System.out.println("Authentication successfull !!! ");
        }

        /* REQUETES */

        System.out.println("********************** Requete 1 **************************");
        Departement departement = em.find(Departement.class, "Informatique");
        System.out.println("Nom -> " + departement.getNomdpt());
        System.out.println("Adresse -> " + departement.getAdressedpt());
        System.out.println("Date de creation -> " + departement.getDatecreationdpt());
        System.out.println("Telephone -> " + departement.getTelephonedpt());

        System.out.println("********************** Requete 2 **************************");
        Query q2 = em.createQuery("select c from  Chercheur c where size(c.articlesByMatriculecher)>0");
        List<Chercheur> chercheurs = q2.getResultList();
        chercheurs.stream().forEach((chercheur) -> {
            System.out.println("Matricule -> " + chercheur.getMatriculecher());
            chercheur.getArticlesByMatriculecher().stream().forEach((art) -> {
                System.out.println("Titre article  -> " + art.getTitreart());
            });
        });

        System.out.println("********************** Requete 3 **************************");
        Chercheur auteur = em.find(Chercheur.class, "M22556");
        System.out.println("Prenom -> " + auteur.getPrenomcher());
        System.out.println("Nom -> " + auteur.getNomcher());
        auteur.getArticlesByMatriculecher().stream().forEach(article -> System.out.println("Titre -> " + article.getTitreart()));

        System.out.println("********************** Requete 4 **************************");
        EntityTransaction t = em.getTransaction();
        String query = "delete from Article as a where chercheurByAuteur in" +
                "(select  c.matriculecher from Chercheur as c " +
                "where c.equipeByNomeq.departementByNomdpt='Mathematiques')";
        Query q4 = em.createQuery(query);
        executeQuery(q4,t, "Nombre de lignes concernées par la mis a jour : #");

        System.out.println("********************** Requete 5 **************************");
        EntityTransaction t5 = em.getTransaction();
        String query5 = "Delete from Chercheur as c where c.matriculecher in (select a.chercheurByAuteur from Article as a where a.chercheurByAuteur=c.matriculecher and a.datesoumission ='2007-05-16')";
        Query q5 = em.createQuery(query5);

        t5.begin();
        executeQuery(q5, t5, "Nombre de lignes concernées : #");

        System.out.println("********************** Requete 6 **************************");
        EntityTransaction t6 = em.getTransaction();
        String query6 = "Update Departement as d set d.adressedpt='Quebec' where d.nomdpt='Physique'";
        Query q6 = em.createQuery(query6);
        executeTransaction(t6, q6);

        System.out.println("********************** Requete 7 **************************");
        String query7 = "UPDATE Chercheur c set c.positioncher='postdoc' where c.positioncher='doctorat' and c.equipeByNomeq='Mathematiques'";
        Query q7 = em.createQuery(query7);
        EntityTransaction t7 = em.getTransaction();
        executeQuery(q7, t7, "Nb rows modified : #");

        System.out.println("********************** Requete 8 **************************");
        EntityTransaction t8 = em.getTransaction();

        Equipe ePediatre = new Equipe();
        Departement dMedecine = new Departement();

        ePediatre.setNomeq("Pediatre");
        ePediatre.setDepartementByNomdpt(dMedecine);

        dMedecine.setNomdpt("Medecine");
        dMedecine.setAdressedpt("Gaspesie");
        dMedecine.setDatecreationdpt(Date.valueOf("2018-03-01"));
        dMedecine.getEquipesByNomdpt().add(ePediatre);

        t8.begin();
        try {
            em.persist(dMedecine);
            t8.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t8.rollback();
        }

        em.clear();
        emF.close();
    }


    private static void executeQuery(Query q7, EntityTransaction t7, String s) {
        t7.begin();
        try {
            System.out.println(s + q7.executeUpdate());
            t7.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t7.rollback();
        }
    }

    private static void executeTransaction(EntityTransaction t, Query q) {
        t.begin();
        try {
            q.executeUpdate();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        }
    }
}
