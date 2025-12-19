package CekelDuit.ui;

import CekelDuit.model.Transaction;
import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoryPanel extends JPanel {

    private final MainFrame mainFrame;
    private JTable table;
    private DefaultTableModel model;

    public HistoryPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Riwayat Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Tipe", "Nominal", "Kategori", "Catatan"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model);
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(sp, BorderLayout.CENTER);
    }

    public void refresh() {
        model.setRowCount(0);

        if (mainFrame.getTransactions().isEmpty()) {
            model.addRow(new Object[]{"-", "-", "Belum ada transaksi", "-"});
            return;
        }

        for (Transaction tx : mainFrame.getTransactions()) {
            model.addRow(new Object[]{
                    tx.getType(),
                    CurencyUtill.format(tx.getAmount()),
                    tx.getCategory(),
                    tx.getNote()
            });
        }
    }
}
