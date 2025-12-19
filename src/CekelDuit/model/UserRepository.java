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
            if (!dir.exists()) dir.mkdirs();

            File file = new File(dir, "user_" + id + ".json");

            // USER BARU (FILE BELUM ADA)
            if (!file.exists()) {
                User user = new User(id, name);
                save(user);
                return user;
            }

            // FILE ADA → BACA
            Reader reader = new FileReader(file);
            User savedUser = gson.fromJson(reader, User.class);
            reader.close();

            // ✅ ID + NAMA SAMA → RELOAD
            if (savedUser.getId().equals(id)
                    && savedUser.getName().equals(name)) {
                return savedUser;
            }

            // ❌ ID sama tapi nama beda → JANGAN TIMPA
            // anggap user baru TANPA save
            return new User(id, name);

        } catch (Exception e) {
            e.printStackTrace();
            return new User(id, name);
        }
    }
    public static void save(User user) {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) dir.mkdirs();

            File file = new File(dir, "user_" + user.getId() + ".json");

            Writer writer = new FileWriter(file);
            gson.toJson(user, writer);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
