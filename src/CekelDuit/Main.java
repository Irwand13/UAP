package CekelDuit;

import CekelDuit.model.User;
import CekelDuit.model.UserRepository;
import CekelDuit.ui.HomePanel;
import CekelDuit.ui.LoginFrame;
import CekelDuit.ui.MainFrame;
import CekelDuit.model.Transaction;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

