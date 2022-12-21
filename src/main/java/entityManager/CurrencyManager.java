package entityManager;

import entity.Currency;
import jakarta.persistence.*;

import java.util.List;

public class CurrencyManager {
    EntityManager entityManager = EntityManagerSingleton.getInstance();
    private static CurrencyManager instance;

    private CurrencyManager(){

    }

    public static CurrencyManager getInstance(){
        if(instance == null){
            instance = new CurrencyManager();
        }
        return instance;
    }

    public void save(Currency currency){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.merge(currency);
            transaction.commit();
        } finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public List<Currency> getAll(){
        return entityManager.createQuery("SELECT c from Currency c", Currency.class).getResultList();
    }

    public Currency findById(Long id){
        return entityManager.getReference(Currency.class, id);
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
