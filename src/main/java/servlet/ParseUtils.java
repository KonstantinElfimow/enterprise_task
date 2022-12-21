package servlet;

import entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

    public static List<Long> parseIdsFromForm(String s){
        if(s.equals("")){
            return new ArrayList<Long>();
        }
        String[] strings = s.split(" ");
        List<Long> ids = new ArrayList<>();
        List<Item> result = new ArrayList<>();
        for(String id: strings){
            ids.add(Long.parseLong(id));
        }
        return ids;
    }
}
