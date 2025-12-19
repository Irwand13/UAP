package CekelDuit.ui;

import CekelDuit.model.Transaction;

import javax.swing.*;
import java.awt.*;

public class TransactionDialog extends JDialog {

    private JComboBox<String> cbType;
    private JTextField txtAmount;
    private JTextField txtCategory;
    private JTextField txtNote;
    private int editIndex ;

    public TransactionDialog(MainFrame mainFrame,Transaction tx, int index) {
        this.editIndex = index;
        setTitle("Tambah Transaksi");
        setSize(350, 300);
        setLocationRelativeTo(mainFrame);
        setModal(true);
        setLayout(new BorderLayout());

        add(buildForm(), BorderLayout.CENTER);
        add(buildButton(mainFrame), BorderLayout.SOUTH);

        cbType.setSelectedItem(tx.getType());
        txtAmount.setText(String.valueOf(tx.getAmount()));
        txtCategory.setText(tx.getCategory());
        txtNote.setText(tx.getNote());
    }

    public TransactionDialog(MainFrame mainFrame) {

    }

    private JPanel buildForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cbType = new JComboBox<>(new String[]{"Pemasukan", "Pengeluaran"});
        txtAmount = new JTextField();
        txtCategory = new JTextField();
        txtNote = new JTextField();

        panel.add(new JLabel("Tipe"));
        panel.add(cbType);

        panel.add(new JLabel("Nominal"));
        panel.add(txtAmount);

        panel.add(new JLabel("Kategori"));
        panel.add(txtCategory);

        panel.add(new JLabel("Catatan"));
        panel.add(txtNote); // 🔴 INI YANG KURANG

        return panel;
    }


    private JPanel buildButton(MainFrame mainFrame) {
        JPanel panel = new JPanel();

        JButton btnSave = new JButton("Simpan");
        btnSave.addActionListener(e -> save(mainFrame));


        panel.add(btnSave);
        return panel;
    }

    private void save(MainFrame mainFrame) {
        try {
            String type = cbType.getSelectedItem().toString();
            double amount = Double.parseDouble(txtAmount.getText());
            String category = txtCategory.getText();
            String note = txtNote.getText();

            if (amount <= 0 || category.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nominal dan kategori wajib diisi");
                return;
            }

            Transaction tx = new Transaction(type, amount, category, note);

            if (editIndex >= 0) {
                mainFrame.updateTransaction(editIndex, tx);
            } else {
                mainFrame.addTransaction(tx);
            }

            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Nominal harus angka");
        }
    }

}