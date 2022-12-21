import JSONService.InitDB;
import entity.Currency;
import entityManager.CurrencyManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(("default"));
//        CurrencyManager currencyManager = new CurrencyManager();
//        Currency currency = new Currency();
//        currency.setCount(1L);
//        currency.setName("test");
//        currency.setId(1L);
//        currency.setCount(1L);
//        currencyManager.save(currency);
//        currencyManager.deleteById(1L);
//        System.out.println(currencyManager.getAll());
        InitDB.initDBFromJson();
        CLI cli = new CLI();
        cli.start();
    }
}
