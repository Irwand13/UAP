package CekelDuit.ui;

import CekelDuit.ui.components.RoundedButton;
import CekelDuit.ui.components.ColorPalette;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TransactionDialog extends JDialog {

    private JComboBox<String> cbType;
    private JTextField txtAmount;
    private JComboBox<String> cbCategory;
    private JTextArea txtNote;
    private JSpinner dateSpinner;

    public TransactionDialog(JFrame parent) {
        super(parent, "Catat Transaksi Baru", true);
        setSize(360, 520);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        add(buildForm(), BorderLayout.CENTER);
        add(buildAction(), BorderLayout.SOUTH);
    }

    private JPanel buildForm() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        panel.setBackground(Color.WHITE);

        panel.add(label("Jenis Transaksi"));
        cbType = new JComboBox<>(new String[]{"Pemasukan", "Pengeluaran"});
        panel.add(cbType);

        panel.add(Box.createVerticalStrut(10));
        panel.add(label("Jumlah (Rp)"));
        txtAmount = new JTextField();
        panel.add(txtAmount);

        panel.add(Box.createVerticalStrut(10));
        panel.add(label("Kategori"));
        cbCategory = new JComboBox<>(new String[]{
                "Makanan", "Transportasi", "Gaji", "Bonus", "Lainnya"
        });
        panel.add(cbCategory);

        panel.add(Box.createVerticalStrut(10));
        panel.add(label("Catatan"));
        txtNote = new JTextArea(3, 20);
        txtNote.setLineWrap(true);
        panel.add(new JScrollPane(txtNote));

        panel.add(Box.createVerticalStrut(10));
        panel.add(label("Tanggal"));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        panel.add(dateSpinner);

        return panel;
    }

    private JPanel buildAction() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 16, 16, 16));
        panel.setBackground(Color.WHITE);

        RoundedButton btnSave =
                new RoundedButton("Simpan Transaksi", ColorPalette.PRIMARY);

        panel.add(btnSave);

        // NOTE: logic akan dipasang di STEP 3
        btnSave.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Transaksi siap diproses (logic menyusul)",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        return panel;
    }

    private JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(ColorPalette.TEXT_GRAY);
        return lbl;
    }

    // ===== Getter (dipakai Controller nanti) =====
    public String getTransactionType() {
        return cbType.getSelectedItem().toString();
    }

    public double getAmount() {
        return Double.parseDouble(txtAmount.getText());
    }

    public String getCategory() {
        return cbCategory.getSelectedItem().toString();
    }

    public String getNote() {
        return txtNote.getText();
    }

    public LocalDate getDate() {
        return LocalDate.now(); // sementara
    }
}
