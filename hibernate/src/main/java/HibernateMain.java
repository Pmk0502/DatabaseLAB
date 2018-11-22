import main.MainClass;
import model.Article;
import model.Chercheur;
import model.Departement;
import model.Equipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HibernateMain {

    public static void main(final String[] args) throws Exception {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        Session currentSession = sessionFactory.openSession();
        if (currentSession != null) {
            System.out.println("Authentication successfull !!");
        } else {
            System.out.println("Authentication failed !!");
        }

        // requetes a ecrire

        System.out.println("************************** Requete 1 ***************************");

        Departement dept = currentSession.get(Departement.class, "Informatique");
        if (dept != null) {
            System.out.println("NomDPT -> " + dept.getNomdpt());
            System.out.println("adresseDPT -> " + dept.getAdressedpt());
            System.out.println("date creation -> " + dept.getDatecreationdpt());
            System.out.println("Telephone -> " + dept.getTelephonedpt());
        } else {
            System.out.println("No department named **Informatique** found !");
        }
        System.out.println("************************Requete 2 ***************************");

        String query2 = "select c from Chercheur c, Article a where c.matriculecher=a.chercheurByAuteur";
        TypedQuery<Chercheur> q2 = currentSession.createQuery(query2);
        List<Chercheur> resultsOfQuery2 = ((Query<Chercheur>) q2).list();
        resultsOfQuery2.stream().forEach((record) -> {
            System.out.println(" Prenom chercheur -> " + record.getPrenomcher());
            System.out.println(" Nom chercheur -> " + record.getNomcher());
            record.getArticlesByMatriculecher().stream().forEach((art) -> {
                System.out.println("Titre article -> " + art.getTitreart());
            });
            System.out.println("#############################################");
        });

        System.out.println("*********************** Requete 3 **************************");

        String query3 = "select c from  Chercheur c, Article art WHERE c.matriculecher=art.chercheurByAuteur and c.matriculecher='M22556'";
        List<Chercheur> resultsOfQuery3 = currentSession.createQuery(query3).list();

        resultsOfQuery3.stream().forEach((chercheur) -> {
            System.out.println("Prenom  -> " + chercheur.getPrenomcher());
            System.out.println("Nom  -> " + chercheur.getNomcher());
            chercheur.getArticlesByMatriculecher().stream().forEach((article) ->
                    System.out.println("Titre article -> " + article.getTitreart()));
        });

        System.out.println("*********************** Requete 4 **************************");

        Transaction t4 = currentSession.beginTransaction();
        try {
            String hql4 = "delete from Article a where a.chercheurByAuteur in (select c.matriculecher from Chercheur c where  a.chercheurByAuteur.equipeByNomeq.departementByNomdpt='Mathematiques')";

            Query q4 = currentSession.createQuery(hql4);
            System.out.println(q4.executeUpdate());
            t4.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t4.rollback();
        }


        System.out.println("*********************** Requete 5 **************************");

        Transaction t5 = currentSession.beginTransaction();
        try {
            String hql5 ="delete from Chercheur c WHERE c.matriculecher in (select a.chercheurByAuteur from Article a where c.matriculecher=a.chercheurByAuteur and a.datesoumission='2007-05-16')";
            Query q5 = currentSession.createQuery(hql5);
            System.out.println("Nb rows concerned = #" + q5.executeUpdate());
            t5.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t5.rollback();
        }

        System.out.println("*********************** Requete 6 **************************");

        Departement deptPhysique = currentSession.get(Departement.class, "Physique");
        if (deptPhysique != null) {
            deptPhysique.setAdressedpt("Quebec");
            Transaction t6 = currentSession.beginTransaction();
            try {
                currentSession.update(deptPhysique);
                t6.commit();
            }catch (Exception e ){
                t6.rollback();
            }
        }

        System.out.println("*********************** Requete 8 **************************");

        Departement deptMedecine = new Departement();
        Equipe pediatreTeam  = new Equipe();

        pediatreTeam.setNomeq("Pediatre");


        deptMedecine.setNomdpt("Medecine");
        deptMedecine.setAdressedpt("Gaspesie");
        deptMedecine.setDatecreationdpt(Date.valueOf("2018-03-01"));
        pediatreTeam.setDepartementByNomdpt(deptMedecine);
        deptMedecine.getEquipesByNomdpt().add(pediatreTeam);

        Transaction t8 = currentSession.beginTransaction();
        try {
            currentSession.persist(deptMedecine);
            t8.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t8.rollback();
        }

        currentSession.close();
        sessionFactory.close();


    }
}