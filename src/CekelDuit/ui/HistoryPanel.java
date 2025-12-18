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

        model = new DefaultTableModel(
                new String[]{"Tipe", "Nominal", "Kategori", "Catatan"}, 0
        );

        table = new JTable(model);
        table.setRowHeight(28);
        table.setEnabled(false);

        JButton btnBack = new JButton("⬅ Kembali");
        btnBack.addActionListener(e -> mainFrame.showHome());
        add(btnBack, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void refresh() {
        model.setRowCount(0);

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
