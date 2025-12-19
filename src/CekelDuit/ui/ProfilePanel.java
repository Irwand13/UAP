package CekelDuit.ui;

import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    private final MainFrame mainFrame;

    private JLabel lblName;
    private JLabel lblSaldo;
    private JLabel lblTotalTx;

    public ProfilePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildContent(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));
        card.setBackground(Color.WHITE);

        lblName = new JLabel();
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));

        card.add(lblName);
        return card;
    }

    private JPanel buildContent() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        lblSaldo = new JLabel();
        lblTotalTx = new JLabel();

        panel.add(new JLabel("Total Saldo"));
        panel.add(lblSaldo);

        panel.add(new JLabel("Jumlah Transaksi"));
        panel.add(lblTotalTx);

        JButton btnEdit = new JButton("✏ Ganti Nama");
        btnEdit.addActionListener(e -> editName());

        panel.add(new JLabel());
        panel.add(btnEdit);

        return panel;
    }

    private void editName() {
        String name = JOptionPane.showInputDialog(
                this,
                "Masukkan nama baru:",
                mainFrame.getUsername()
        );

        if (name != null && !name.trim().isEmpty()) {
            mainFrame.setUsername(name);
        }
    }

    public void refresh() {
        lblName.setText(mainFrame.getUsername());
        lblSaldo.setText(CurencyUtill.format(mainFrame.getSaldo()));
        lblTotalTx.setText(String.valueOf(mainFrame.getTransactions().size()));
    }
}
