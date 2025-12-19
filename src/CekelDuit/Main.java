package CekelDuit;

import CekelDuit.ui.HomePanel;
import CekelDuit.ui.LoginFrame;
import CekelDuit.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

