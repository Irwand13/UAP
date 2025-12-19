package CekelDuit.model;

import CekelDuit.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class UserRepository {

    private static final String DIR = "data";

    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static User load(String id, String name) {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) dir.mkdir();

            File file = new File(DIR + "/user_" + id + ".json");

            if (!file.exists()) {
                return new User(id, name);
            }

            Reader reader = new FileReader(file);
            User user = gson.fromJson(reader, User.class);
            reader.close();

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return new User(id, name);
        }
    }

    public static void save(User user) {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) dir.mkdir();

            File file = new File(DIR + "/user_" + user.getId() + ".json");

            Writer writer = new FileWriter(file);
            gson.toJson(user, writer);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
