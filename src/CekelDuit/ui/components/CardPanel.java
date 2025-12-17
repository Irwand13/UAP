package CekelDuit.ui.components;

import javax.swing.*;

public class CardPanel extends JPanel {

    public CardPanel() {
        setBackground(ColorPalette.CARD);
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
