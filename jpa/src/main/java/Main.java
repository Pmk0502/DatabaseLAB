import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String [] args){

        EntityManagerFactory emF = null;
        EntityManager em = null;

        if (emF == null )
            emF = Persistence.createEntityManagerFactory("tp5");
        if (em == null){
            em = emF.createEntityManager();
            System.out.println("Authentication successfull !!! ");
        }


    }
}
