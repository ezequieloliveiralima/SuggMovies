/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlebooks.ws;

import googlebooks.ws.entities.Book;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author ezequieloliveira
 * private String ;
    private String , , , , , ;
    private List<String> , ;
    private Integer pageCount;
 */
public class BookParser {
    public static List<Book> read(String q) {
        List<Book> list = new ArrayList<>();
        
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + q;
        
        String content = ConnectionManager.readContent(url);
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        JsonArray items = root.getJsonArray("items");
        Iterator<JsonValue> iterator = items.iterator();
        
        while (iterator.hasNext()) {
            JsonObject item = (JsonObject) iterator.next();
            JsonObject vI = item.getJsonObject("volumeInfo");
            if (vI.containsKey("description") &&
                    vI.containsKey("publisher") &&
                    vI.containsKey("authors") &&
                    vI.containsKey("categories") &&
                    vI.containsKey("pageCount")) {
                String id = item.getString("id");
                String title = vI.getString("title");
                JsonArray authors = vI.getJsonArray("authors");
                List<String> authorsArray = new ArrayList<>();
                authors.forEach(author -> authorsArray.add(author.toString()));
                String publisher = vI.getString("publisher");
                String description = vI.getString("description");
                String thumbnail = vI.getJsonObject("imageLinks").getString("thumbnail");
                String language = vI.getString("language");
                String buyLink = "";
                if (item.getJsonObject("saleInfo").containsKey("buyLink")) {
                    buyLink = item.getJsonObject("saleInfo").getString("buyLink");
                }
                JsonArray categories = vI.getJsonArray("categories");
                List<String> categoriesArray = new ArrayList<>();
                categories.forEach(category -> categoriesArray.add(category.toString()));
                Integer pageCount = vI.getInt("pageCount");
                Book b = new Book(id, title, publisher, description, thumbnail, buyLink, language, authorsArray, categoriesArray, pageCount);
                list.add(b);
            }
        }
        
        return list;
    }
}
