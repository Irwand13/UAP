package main.java.org.CekelDuit.main;

import main.java.org.CekelDuit.ui.HomeFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new HomeFrame().setVisible(true);
        });
    }
}

