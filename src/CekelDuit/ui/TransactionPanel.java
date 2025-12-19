package CekelDuit.ui;

import CekelDuit.model.Transaction;

import javax.swing.*;
import java.awt.*;

public class TransactionPanel extends JPanel {

    private MainFrame mainFrame;

    private JPanel formPanel;
    private JTextField txtAmount;
    private JTextField txtCategory;
    private JTextField txtNote;

    private boolean isIncome = true;

    public TransactionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildForm(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Tambah Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel buttons = new JPanel(new GridLayout(1, 2, 10, 0));

        JButton btnIncome = new JButton("➕ Pemasukan");
        JButton btnExpense = new JButton("➖ Pengeluaran");

        btnIncome.addActionListener(e -> showForm(true));
        btnExpense.addActionListener(e -> showForm(false));

        buttons.add(btnIncome);
        buttons.add(btnExpense);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildForm() {
        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        txtAmount = new JTextField();
        txtCategory = new JTextField();
        txtNote = new JTextField();

        formPanel.add(new JLabel("Nominal"));
        formPanel.add(txtAmount);

        formPanel.add(new JLabel("Kategori"));
        formPanel.add(txtCategory);

        formPanel.add(new JLabel("Catatan"));
        formPanel.add(txtNote);

        JButton btnSave = new JButton("Simpan");
        btnSave.addActionListener(e -> save());

        formPanel.add(new JLabel());
        formPanel.add(btnSave);

        formPanel.setVisible(false); // 🔥 AWALNYA DISYEMBUNYIKAN

        return formPanel;
    }

    private void showForm(boolean income) {
        this.isIncome = income;
        formPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void save() {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            String category = txtCategory.getText();
            String note = txtNote.getText();

            if (amount <= 0 || category.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nominal & kategori wajib diisi");
                return;
            }

            Transaction tx = new Transaction(
                    isIncome ? "Pemasukan" : "Pengeluaran",
                    amount,
                    category,
                    note
            );

            mainFrame.addTransaction(tx);

            txtAmount.setText("");
            txtCategory.setText("");
            txtNote.setText("");
            formPanel.setVisible(false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Nominal harus angka");
        }
    }
}