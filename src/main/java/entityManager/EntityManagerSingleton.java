package entityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class EntityManagerSingleton {
    private static EntityManager instance;

    public static EntityManager getInstance() {
        try {
            Class.forName("org.hibernate.jpa.HibernatePersistenceProvider");
            Class.forName("jakarta.persistence.Persistence");
        } catch (ClassNotFoundException e) {
            System.out.printf("suka");
            throw new RuntimeException(e);
        }
        if(instance == null){
            // Thread.currentThread().getContextClassLoader().getResource("META-INF/persistence.xml");
            // EntityManager.class.getClassLoader().getResource("META-INF/persistence.xml");
            instance = Persistence.createEntityManagerFactory("default").createEntityManager();
        }
        return instance;
    }
}
