import java.io.FileReader;
import com.google.gson.*;

public class TestGetLogos {
    public static void main(String[] args) {
        try {
            // Read JSON file
            FileReader reader = new FileReader("./test/logos.json");
            
            // Parse JSON data using Gson
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            
            // Get companies and universities arrays
            JsonArray companies = jsonObject.getAsJsonArray("companies");
            JsonArray universities = jsonObject.getAsJsonArray("universities");
            
            // Print companies
            System.out.println("Companies:");
            for (JsonElement element : companies) {
                JsonObject company = element.getAsJsonObject();
                String name = company.get("name").getAsString();
                String website = company.get("website").getAsString();
                String imagePath = company.get("imagePath").getAsString();
                System.out.println("Name: " + name + ", Website: " + website + ", Image Path: " + imagePath);
            }
            
            // Print universities
            System.out.println("\nUniversities:");
            for (JsonElement element : universities) {
                JsonObject university = element.getAsJsonObject();
                String name = university.get("name").getAsString();
                String website = university.get("website").getAsString();
                String imagePath = university.get("imagePath").getAsString();
                System.out.println("Name: " + name + ", Website: " + website + ", Image Path: " + imagePath);
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
