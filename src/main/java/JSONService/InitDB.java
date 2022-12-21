package JSONService;

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Player;
import entityManager.PlayerManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class InitDB {

    public static void initDBFromJson(){
        PlayerManager playerManager = PlayerManager.getInstance();
        JsonWriter jsonWriter = new JsonWriter();
        JsonReader jsonReader = new JsonReader();
        List<Player> players = null;
        try {
            players = jsonReader.getPlayerFromJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Player player: players){
            playerManager.save(player);
        }
        List<Player> playerList = playerManager.getAll();

        try {
            jsonWriter.listOfPlayerToJson(playerList);
        } catch (JsonProcessingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
