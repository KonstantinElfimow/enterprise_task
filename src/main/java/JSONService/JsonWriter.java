package JSONService;

import entity.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class JsonWriter {
    public void listOfPlayerToJson(List<Player> players) throws JsonProcessingException, FileNotFoundException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        PrintWriter printWriter = new PrintWriter("C:\\Users\\ПК\\IdeaProjects\\Enterprise\\task\\src\\main\\resources\\output\\output.json");
        printWriter.println(ow.writeValueAsString(players));
    }
}
