package entityManager;

import entity.Currency;
import entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class PlayerManager {

    EntityManager entityManager;
    private static PlayerManager instance;

    private PlayerManager() {
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        this.entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    public static PlayerManager getInstance(){
        if(instance == null){
            instance = new PlayerManager();
        }
        return instance;
    }
    
    public void save(Player player){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.merge(player);
            transaction.commit();
        } finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public List<Player> getAll(){
        return entityManager.createQuery("SELECT p from Player p", Player.class).getResultList();
    }

    public Player findById(Long id){
        return entityManager.getReference(Player.class, id);
    }

    public void deleteById(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(findById(id));
            transaction.commit();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
    }
}
