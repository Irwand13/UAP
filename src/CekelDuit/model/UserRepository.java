package CekelDuit.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class UserRepository {

    private static final String DIR = "data";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Helper untuk membuat nama file yang konsisten
    private static String getFileName(String id, String name) {
        // Kita paksa lowercase dan hapus spasi agar ID "1" Nama "Budi"
        // selalu jadi file yang sama meskipun diketik "BUDI" atau "budi "
        String cleanName = name.trim().toLowerCase().replaceAll("\\s+", "_");
        return "user_" + id.trim() + "_" + cleanName + ".json";
    }

    public static User load(String id, String name) {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) dir.mkdirs();

            File file = new File(dir, getFileName(id, name));

            // 1. JIKA FILE ADA -> LOAD DATA LAMA (DATABASE)
            if (file.exists()) {
                try (Reader reader = new FileReader(file)) {
                    User savedUser = gson.fromJson(reader, User.class);
                    if (savedUser != null) {
                        System.out.println("Data ditemukan: Loading database untuk " + name);
                        return savedUser;
                    }
                }
            }

            // 2. JIKA FILE TIDAK ADA -> BUAT USER BARU
            System.out.println("Data tidak ditemukan: Membuat user baru " + name);
            User newUser = new User(id, name);
            save(newUser); // Simpan file pertamanya
            return newUser;

        } catch (Exception e) {
            e.printStackTrace();
            return new User(id, name);
        }
    }

    public static void save(User user) {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) dir.mkdirs();

            // Gunakan helper yang sama agar lokasi simpan dan load SAMA
            File file = new File(dir, getFileName(user.getId(), user.getName()));

            try (Writer writer = new FileWriter(file)) {
                gson.toJson(user, writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}