package entityManager;

import entity.Currency;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ItemManager {

    private EntityManager entityManager = EntityManagerSingleton.getInstance();
    private static ItemManager instance;

    private ItemManager(){

    }

    public static ItemManager getInstance(){
        if(instance == null){
            instance = new ItemManager();
        }
        return instance;
    }

    public void save(Item item){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.merge(item);
            transaction.commit();
        } finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public List<Item> getAll(){
        return entityManager.createQuery("SELECT t from Item t", Item.class).getResultList();
    }

    public Item findById(Long id){
        return entityManager.getReference(Item.class, id);
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
