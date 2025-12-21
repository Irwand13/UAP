package CekelDuit.utill;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import CekelDuit.model.Transaction;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {

    private static final Gson gson = new Gson();
    private static final Type LIST_TYPE =
            new TypeToken<List<Transaction>>() {}.getType();
    public static void write(String path, List<Transaction> data) {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> read(String path) {
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, LIST_TYPE);
        } catch (Exception e) {
            return null;
        }
    }
}
