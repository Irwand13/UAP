package CekelDuit.ui.components;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text, Color bg) {
        super(text);
        setBackground(bg);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
}
