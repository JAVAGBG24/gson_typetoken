package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

public class UserManager {
    private Map<String, User> userlist;
    private  String fileName = "src/main/user.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UserManager() {
    }

    public void readFile() throws IOException {
        Type type = new TypeToken<Map<String, User>>() {}.getType();
        //här används TypeToken från gson för att få information om den typ som Gson
        // ska avkoda från JSON-filen. Eftersom Map<String, User> är en generisk typ och
        // Java inte direkt hanterar generics vid körning, behövs TypeToken för att
        // Gson ska veta vilken typ som ska användas vid deserialisering.
        Reader reader = new FileReader(new File(fileName));
        // en FileReader används här för att läsa från filen som lagras i variabeln fileName.
        // FileReader öppnar en ström för att läsa filens innehåll tecken för tecken.
        userlist = gson.fromJson(reader, type);
        // här används Gson för att konvertera JSON-innehållet i filen till ett Java-objekt.
        // i detta fall kommer den att konvertera JSON-innehållet till en Map<String, User>-struktur.
        // fromJson() tar två argument: en läsare (reader) och typen (type).
        System.out.println("User List:");
        for(String name : userlist.keySet()) {
            System.out.println("Name: " + name);
            // här itererar koden genom alla nycklar (namnen) i userlist-mappen och skriver ut
            // varje namn i konsolen. keySet() returnerar en uppsättning av alla nycklar
            // (användarnamn) i mappen.
        }
    }

    public void saveToFile(User user) throws IOException {
        userlist.put(user.getUsername(), user);
        // här läggs användaren till i den befintliga userlist, som är en Map<String, User>.
        // nyckeln är användarnamnet (user.getUsername()), och värdet är användarobjektet (user).
        // om en användare med samma användarnamn redan finns, skrivs den över.
        FileWriter fw = new FileWriter(new File(fileName));
        // en FileWriter öppnas för att skriva till den fil som specificeras av fileName.
        // om filen inte finns, skapar FileWriter den. Om filen redan finns, skrivs dess innehåll över.
        gson.toJson(userlist, fw);
        // här används Gson för att konvertera userlist (som är en Map<String, User>) till
        // JSON-format och skriva den till filen med hjälp av FileWriter (fw). toJson() tar
        // två argument: objektet som ska konverteras (här userlist) och den Writer som ska användas (här fw).
        fw.close();
        // strömmen stängs, vilket säkerställer att allt skrivs till filen och att filresurserna frigörs.
        System.out.println("User saved");
    }
}
