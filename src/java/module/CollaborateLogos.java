package module;

import java.io.FileReader;
import com.google.gson.*;

public class CollaborateLogos {
    private JsonArray companies;
    private JsonArray universities;

    public CollaborateLogos() {
        try {
            // Read JSON file
            FileReader reader = new FileReader("./src/java/data_file/logos.json");

            // Parse JSON data using Gson
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // Get companies and universities arrays
            companies = jsonObject.getAsJsonArray("companies");
            universities = jsonObject.getAsJsonArray("universities");

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // return the companies list
    public JsonArray getCompanies() {
        return companies;
    }

    // return the universities list
    public JsonArray getUniversities() {
        return universities;
    }
    
    public static void main(String[] args) {
        // Create an instance of CollaborateLogos
        CollaborateLogos collaborateLogos = new CollaborateLogos();

        // Get the companies and universities arrays
        JsonArray companies = collaborateLogos.getCompanies();
        JsonArray universities = collaborateLogos.getUniversities();

        // Print the companies array
        System.out.println("Companies:");
        for (JsonElement company : companies) {
            System.out.println(company);
        }

        // Print the universities array
        System.out.println("\nUniversities:");
        for (JsonElement university : universities) {
            System.out.println(university);
        }
    }
}

