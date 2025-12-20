package CekelDuit.ui;

import CekelDuit.model.Transaction;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TransactionPanel extends JPanel {

    private final MainFrame mainFrame;
    private JTextField txtAmount;
    private JTextField txtNote; // Menambahkan kembali variabel Catatan
    private JComboBox<String> comboCategory;
    private JButton btnIncomeToggle, btnExpenseToggle;
    private boolean isIncome = true;

    private final Color GREEN_THEME = new Color(0, 184, 76);
    private final Color BG_LIGHT = new Color(248, 249, 253);
    private final Color INPUT_BG = new Color(243, 244, 246);

    public TransactionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(BG_LIGHT);

        add(buildHeader(), BorderLayout.NORTH);
        add(buildContent(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GREEN_THEME);
        header.setPreferredSize(new Dimension(0, 80));
        header.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblTitle = new JLabel("Daftar Transaksi");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));

        JButton btnAdd = new JButton("+ Tambah");
        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1));
        btnAdd.setFocusPainted(false);

        header.add(lblTitle, BorderLayout.WEST);
        header.add(btnAdd, BorderLayout.EAST);
        return header;
    }

    private JPanel buildContent() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(BG_LIGHT);
        container.setOpaque(true);

        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
                g2.dispose();
            }
        };
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(380, 560)); // Tinggi ditambah untuk Catatan
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel lblFormTitle = new JLabel("Catat Transaksi Baru");
        lblFormTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblFormTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Toggle Buttons
        JPanel toggleWrapper = new JPanel(new GridLayout(1, 2));
        toggleWrapper.setMaximumSize(new Dimension(320, 45));
        toggleWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnIncomeToggle = createStepButton("Pemasukan", true);
        btnExpenseToggle = createStepButton("Pengeluaran", false);
        toggleWrapper.add(btnIncomeToggle);
        toggleWrapper.add(btnExpenseToggle);

        // Inputs
        txtAmount = new JTextField("0");
        styleInput(txtAmount);

        String[] cats = {"💰 Gaji", "🎁 Bonus", "💼 Usaha", "📈 Investasi", "🍱 Lainnya"};
        comboCategory = new JComboBox<>(cats);
        styleInput(comboCategory);

        txtNote = new JTextField(); // Inisialisasi input Catatan
        styleInput(txtNote);
        txtNote.setToolTipText("Masukkan deskripsi singkat...");

        // Save Button
        JButton btnSave = new JButton("Simpan Transaksi");
        btnSave.setMaximumSize(new Dimension(320, 50));
        btnSave.setBackground(GREEN_THEME);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSave.addActionListener(e -> save());

        // Susun ke Card
        card.add(lblFormTitle);
        card.add(Box.createRigidArea(new Dimension(0, 25)));
        card.add(toggleWrapper);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(createLeftAlignedLabel("Jumlah (Rp)"));
        card.add(txtAmount);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(createLeftAlignedLabel("Kategori"));
        card.add(comboCategory);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(createLeftAlignedLabel("Catatan (Opsional)"));
        card.add(txtNote); // Menambahkan Catatan ke UI
        card.add(Box.createRigidArea(new Dimension(0, 30)));
        card.add(btnSave);

        container.add(card);
        setMode(true);
        return container;
    }

    private JButton createStepButton(String text, boolean left) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.addActionListener(e -> setMode(left));
        return btn;
    }

    private void styleInput(JComponent comp) {
        comp.setMaximumSize(new Dimension(320, 45));
        comp.setBackground(INPUT_BG);
        comp.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comp.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        if (comp instanceof JComboBox) {
            ((JComboBox<?>) comp).setFocusable(false);
        }
    }

    private JLabel createLeftAlignedLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(320, 25));
        p.add(l);
        return l;
    }

    public void setMode(boolean isIncome) {
        this.isIncome = isIncome;
        btnIncomeToggle.setBackground(isIncome ? GREEN_THEME : INPUT_BG);
        btnIncomeToggle.setForeground(isIncome ? Color.WHITE : Color.GRAY);
        btnExpenseToggle.setBackground(!isIncome ? GREEN_THEME : INPUT_BG);
        btnExpenseToggle.setForeground(!isIncome ? Color.WHITE : Color.GRAY);
    }

    private void save() {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            String category = (String) comboCategory.getSelectedItem();
            String note = txtNote.getText(); // Mengambil nilai Catatan

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Nominal harus lebih dari 0");
                return;
            }

            // Memasukkan variabel note ke konstruktor Transaction
            Transaction tx = new Transaction(
                    isIncome ? "Pemasukan" : "Pengeluaran",
                    amount,
                    category,
                    note
            );

            mainFrame.addTransaction(tx);

            // Reset form
            txtAmount.setText("0");
            txtNote.setText("");
            JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input nominal tidak valid!");
        }
    }
}