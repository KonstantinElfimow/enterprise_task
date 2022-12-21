
import entity.*;
import entityManager.CurrencyManager;
import entityManager.ItemManager;
import entityManager.PlayerManager;
import entityManager.ProgressManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static final String chooseTable = "Choose table: \n1 - currency; \n2 - item; \n3 - player; \n4 - progress; \n";
    private static final String chooseAction = "Choose action: \n 1 - create; \n 2 - read; \n 3 - update\n 4- delete; \n";
    private static final String createText = "Enter element's attributes: \n";
    private static final String chooseRead = "Choose action: \n1 - find by id; \n2 - read whole table; \n";
    private static final String byIdText = "Enter id: \n";
    private static final String tryAgain = "invalid args. Try again \n";
    private static final String tryAgainDefault = "enter number between 1 and 4\n";
    private static final String doesNotExist = "entity does not exist\n";
    private static final String exceptionText = "Something went wrong. try again \n";

    private final CurrencyManager currencyManager = CurrencyManager.getInstance();
    private final ItemManager itemManager = ItemManager.getInstance();
    private final PlayerManager playerManager = PlayerManager.getInstance();
    private final ProgressManager progressManager = ProgressManager.getInstance();


    public void start() {
        Scanner in = new Scanner(System.in);
            while (true) {
                print(chooseTable);
                int chosenTable = in.nextInt();
                print(chooseAction);
                int input = in.nextInt();

                switch (input) {
                    case 1:
                        create(in, chosenTable);
                        break;
                    case 2:
                        read(in, chosenTable);
                        break;
                    case 3:
                        break;
                    case 4:
                        delete(in, chosenTable);
                        break;
                    default:
                        System.exit(0);

                }

            }
    }

    private void delete(Scanner in, int chosenTable) {
        print(byIdText);
        Long input = in.nextLong();
        switch (chosenTable) {
            case 1:
                currencyManager.deleteById(input);
                break;
            case 2:
                itemManager.deleteById(input);
                break;
            case 3:
                playerManager.deleteById(input);
                break;
            case 4:
                progressManager.deleteById(input);
                break;
        }
    }

    private static void update(Connection con, Scanner in, int chosenTable) {

    }

    private void read(Scanner in, int chosenTable) {
        print(chooseRead);
        int inputSwitch = in.nextInt();
        switch (inputSwitch) {
            case 1:
                print(byIdText);
                Long input = in.nextLong();
                switch (chosenTable) {
                    case 1:
                        Currency currency = currencyManager.findById(input);
                        print(currency.toString());
                        break;
                    case 2:
                        Item item = itemManager.findById(input);
                        print(item.toString());
                        break;
                    case 3:
                        Player player = playerManager.findById(input);
                        print(player.toString());
                        break;
                    case 4:
                        Progress progress = progressManager.findById(input);
                        print(progress.toString());
                        break;
                    default:
                        print(tryAgain);
                }
                break;
            case 2:
                switch (chosenTable) {
                    case 1:
                        List<Currency> currencies = currencyManager.getAll();
                        for (Currency c: currencies) {
                            print(c.toString());
                        }
                        break;
                    case 2:
                        List<Item> items = itemManager.getAll();
                        for (Item item : items) {
                            print(item.toString());
                        }
                        break;
                    case 3:
                        List<Player> players = playerManager.getAll();
                        for (Player player : players) {
                            print(player.toString());
                        }
                        break;
                    case 4:
                        List<Progress> progresses = progressManager.getAll();
                        for (Progress progress: progresses) {
                            print(progress.toString());
                        }
                        break;
                    default:
                        print(tryAgain);
                }
                break;
        }
    }

    private void create(Scanner in, int chosenTable) {
        print(createText);
        switch (chosenTable) {
            case 1:
                print("id, count, name, resourceid \n");
                Long id = in.nextLong();
                Long count = in.nextLong();
                in.nextLine(); //Scanner не читает \n после nextint(). строка нужна чтобы убрать оставшийся \n
                String name = in.nextLine();
                Long resourceid = in.nextLong();
                Currency currency = new Currency();
                currency.setId(id);
                currency.setCount(count);
                currency.setName(name);
                currency.setResourceId(resourceid);
                currencyManager.save(currency);
                break;
            case 2:
                print("id, count, level, resourceid \n");

                id = in.nextLong();
                count = in.nextLong();
                Long level = in.nextLong();
                resourceid = in.nextLong();
                Item item = new Item();
                item.setId(id);
                item.setCount(count);
                item.setLevel(level);
                item.setResourceId(resourceid);
                itemManager.save(item);
                break;
            case 3:
                id = in.nextLong();
                String nickname = in.nextLine();
                print("Enter list of items' id. Press enter to exit \n");
                List<Item> items = new ArrayList<>();
                while(in.hasNext()){
                    Long foreign_key = in.nextLong();
                    items.add(itemManager.findById(foreign_key));
                }
                List<Currency> currencies= new ArrayList<>();
                while(in.hasNext()){
                    Long key = in.nextLong();
                    currencies.add(currencyManager.findById(key));
                }
                Player player = new Player();
                player.setId(id);
                player.setNickname(nickname);
                player.setItems(items);
                player.setCurrencies(currencies);
                break;
            case 4:
                //dopishesh po analogii
                break;
        }
    }


    private static void print(String s) {
        System.out.println(s);
    }
}
