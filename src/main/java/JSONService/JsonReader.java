package JSONService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Currency;
import entity.Item;
import entity.Player;
import entity.Progress;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonReader {
     public List<Player> getPlayerFromJson() throws IOException {
         ObjectMapper objectMapper = new ObjectMapper();
         String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\ПК\\IdeaProjects\\Enterprise\\task\\src\\main\\resources\\players.json")));
         List<Player> playerList = new ArrayList<>();
         JsonNode jsonNode = objectMapper.readTree(json);
         Iterator<JsonNode> fields = jsonNode.elements();
         while (fields.hasNext()) {
             JsonNode node = fields.next();
             Player player = new Player();
             player.setId(Long.valueOf(node.get("playerId").asText()));
             player.setNickname(node.get("nickname").asText());

             // Парсим прогресс
             JsonNode progressNode = node.get("progresses");
             Iterator<JsonNode> progressesIterator = progressNode.elements();
             while (progressesIterator.hasNext()) {
                 JsonNode currNode = progressesIterator.next();
                 Progress progress = new Progress();
                 progress.setId(Long.valueOf(currNode.get("id").asText()));
                 progress.setResourceId(Long.valueOf(currNode.get("resourceId").asText()));
                 progress.setScore(Long.valueOf(currNode.get("score").asText()));
                 progress.setMaxScore(Long.valueOf(currNode.get("maxScore").asText()));
                 progress.setPlayer(player);

             }


             // Парсим currency
             JsonNode currenciesNode = node.get("currencies");
             Iterator<JsonNode> currenciesIterator = currenciesNode.elements();
             List<Currency> playerCurrencies = new ArrayList<>();
             while (currenciesIterator.hasNext()) {
                 JsonNode currNode = currenciesIterator.next();
                 Currency currency = new Currency();
                 currency.setId(Long.valueOf(currNode.get("id").asText()));
                 currency.setCount(Long.valueOf(currNode.get("count").asText()));
                 currency.setName(currNode.get("name").asText());
                 currency.setResourceId(Long.valueOf(currNode.get("resourceId").asText()));
                 playerCurrencies.add(currency);
             }
             player.setCurrencies(playerCurrencies);


             // Парсим item
             JsonNode itemNode = node.get("items");
             Iterator<JsonNode> itemIterator = itemNode.elements();
             List<Item> playerItems = new ArrayList<>();
             while (itemIterator.hasNext()) {
                 JsonNode currNode = itemIterator.next();
                 Item item = new Item();
                 item.setId(Long.valueOf(currNode.get("id").asText()));
                 item.setCount(Long.valueOf(currNode.get("count").asText()));
                 item.setLevel(Long.valueOf(currNode.get("level").asText()));
                 item.setResourceId(Long.valueOf(currNode.get("resourceId").asText()));
                 playerItems.add(item);
             }
             player.setItems(playerItems);
             playerList.add(player);
         }
         return playerList;
    }
}
