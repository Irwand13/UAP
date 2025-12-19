package CekelDuit.ui;

import javax.swing.*;
import java.awt.*;

public class TransactionPanel extends JPanel {
    private TransactionPanel transactionPanel;
    private MainFrame mainFrame;

    public TransactionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel title = new JLabel("Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton btnAdd = new JButton("➕ Tambah Transaksi");
        btnAdd.setPreferredSize(new Dimension(200,45));

        btnAdd.addActionListener(e -> {
            new TransactionDialog(mainFrame).setVisible(true);
        });

        add(title, BorderLayout.NORTH);
        add(btnAdd, BorderLayout.CENTER);
    }
}

