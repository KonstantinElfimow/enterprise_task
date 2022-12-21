package entityManager;

import entity.Currency;
import entity.Progress;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ProgressManager {

    EntityManager entityManager = EntityManagerSingleton.getInstance();
    private static ProgressManager instance;

    private ProgressManager(){

    }

    public static ProgressManager getInstance(){
        if(instance == null){
            instance = new ProgressManager();
        }
        return instance;
    }

    public void save(Progress progress){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.merge(progress);
            transaction.commit();
        } finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public List<Progress> getAll(){
        return entityManager.createQuery("SELECT p from Progress p", Progress.class).getResultList();
    }

    public Progress findById(Long id){
        return entityManager.getReference(Progress.class, id);
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
