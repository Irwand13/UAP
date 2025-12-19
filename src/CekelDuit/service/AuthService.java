package CekelDuit.service;

import CekelDuit.model.User;

public class AuthService {

    private static User currentUser;

    public static User login(String id, String name) {
        // STEP 2 nanti: cek JSON
        // sekarang buat user langsung

        currentUser = new User(id, name);
        return currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
