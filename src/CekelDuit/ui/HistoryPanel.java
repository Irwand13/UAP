package CekelDuit.ui;

import CekelDuit.model.Transaction;
import CekelDuit.model.User;
import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoryPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JButton btnEdit, btnDelete;
    private MainFrame mainFrame;

    public HistoryPanel() {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(Color.WHITE);

        add(buildTable(), BorderLayout.CENTER);
        add(buildActions(), BorderLayout.SOUTH);
    }

    private JScrollPane buildTable() {
        model = new DefaultTableModel(
                new Object[]{"Tipe", "Kategori", "Nominal", "Catatan"},
                0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        return new JScrollPane(table);
    }

    private JPanel buildActions() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnEdit = new JButton("✏ Edit");
        btnDelete = new JButton("🗑 Hapus");

        btnEdit.addActionListener(e -> editSelected());
        btnDelete.addActionListener(e -> deleteSelected());

        panel.add(btnEdit);
        panel.add(btnDelete);

        return panel;
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi dulu");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin hapus transaksi ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        mainFrame.deleteTransaction(row);
    }

    private void editSelected() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi dulu");
            return;
        }

        mainFrame.editTransaction(row);
    }

    // 🔴 METHOD BARU (GANTI refresh())
    public void update(User user) {
        model.setRowCount(0);

        if (user.getTransactions().isEmpty()) {
            return;
        }

        for (Transaction tx : user.getTransactions()) {
            model.addRow(new Object[]{
                    tx.isIncome() ? "Pemasukan" : "Pengeluaran",
                    tx.getCategory(),
                    CurencyUtill.format(tx.getAmount()),
                    tx.getNote()
            });
        }
    }
}
