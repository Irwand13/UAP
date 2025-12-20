package CekelDuit.ui;

import CekelDuit.model.Transaction;
import CekelDuit.model.User;
import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class HomePanel extends JPanel {

    private final MainFrame mainFrame;
    private JLabel lblName, lblSaldo;
    private JPanel historyPanel;

    // Warna sesuai referensi gambar
    private final Color GREEN_THEME = new Color(0, 184, 76);
    private final Color BG_LIGHT = new Color(248, 249, 253);
    private final Color TEXT_GRAY = new Color(120, 120, 120);

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null); // Null layout untuk efek floating card
        setBackground(BG_LIGHT);

        initComponents();
    }

    private void initComponents() {
        // 1. Header Hijau Melengkung
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(GREEN_THEME);
                // Membuat header dengan lengkungan di bawah saja
                g2.fill(new RoundRectangle2D.Float(0, -20, getWidth(), getHeight() + 20, 40, 40));
                g2.dispose();
            }
        };
        header.setLayout(null);
        header.setBounds(0, 0, 400, 200);
        header.setOpaque(false);

        lblName = new JLabel("Halo, Arek Malang 👋");
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblName.setBounds(25, 30, 250, 30);
        header.add(lblName);

        // 2. Kartu Saldo (Floating Card)
        JPanel saldoCard = createRoundedPanel(25);
        saldoCard.setBackground(Color.WHITE);
        saldoCard.setBounds(25, 80, 350, 180);
        saldoCard.setLayout(null);

        JLabel lblSaldoTitle = new JLabel("Saldo Cekel Wallet");
        lblSaldoTitle.setForeground(TEXT_GRAY);
        lblSaldoTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSaldoTitle.setBounds(20, 20, 150, 20);
        saldoCard.add(lblSaldoTitle);

        lblSaldo = new JLabel("Rp 0");
        lblSaldo.setForeground(GREEN_THEME);
        lblSaldo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblSaldo.setBounds(20, 45, 300, 40);
        saldoCard.add(lblSaldo);

        // Tombol Catat Masuk & Keluar di dalam Card
        JButton btnIn = createActionButton("Catat Masuk", new Color(235, 250, 240), GREEN_THEME, true);
        btnIn.setBounds(20, 110, 145, 45);

        JButton btnOut = createActionButton("Catat Keluar", new Color(255, 245, 245), new Color(244, 67, 54), false);
        btnOut.setBounds(185, 110, 145, 45);

        saldoCard.add(btnIn);
        saldoCard.add(btnOut);

        // 3. Section Riwayat
        JLabel lblHistoryTitle = new JLabel("Transaksi Terakhir");
        lblHistoryTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblHistoryTitle.setBounds(25, 280, 200, 25);
        add(lblHistoryTitle);

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBackground(BG_LIGHT);

        JScrollPane scroll = new JScrollPane(historyPanel);
        scroll.setBounds(25, 315, 350, 300);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        add(saldoCard);
        add(header);
        add(scroll);
    }

    /* ================= UI HELPERS ================= */

    private JButton createActionButton(String text, Color bg, Color fg, boolean isIncome) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(fg, 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> mainFrame.showTransactionPanel(isIncome));
        return btn;
    }

    private JPanel createRoundedPanel(int radius) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
                g2.dispose();
            }
        };
    }

    /* ================= UPDATE DATA ================= */

    public void update(User user) {
        lblName.setText("Halo, " + user.getName() + " 👋");
        lblSaldo.setText(CurencyUtill.format(user.getBalance()));

        historyPanel.removeAll();
        List<Transaction> txs = user.getTransactions();

        if (txs.isEmpty()) {
            JLabel empty = new JLabel("Belum ada transaksi");
            empty.setForeground(TEXT_GRAY);
            historyPanel.add(empty);
        } else {
            int limit = Math.max(0, txs.size() - 10);
            for (int i = txs.size() - 1; i >= limit; i--) {
                historyPanel.add(buildTransactionItem(txs.get(i)));
                historyPanel.add(Box.createVerticalStrut(10));
            }
        }
        historyPanel.revalidate();
        historyPanel.repaint();
    }

    private JPanel buildTransactionItem(Transaction tx) {
        JPanel item = createRoundedPanel(15);
        item.setBackground(Color.WHITE);
        item.setLayout(new BorderLayout(15, 0));
        item.setBorder(new EmptyBorder(10, 15, 10, 15));
        item.setMaximumSize(new Dimension(350, 60));

        // Info Kiri (Kategori)
        JLabel lblCat = new JLabel(tx.getCategory());
        lblCat.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Info Kanan (Amount)
        String prefix = tx.isIncome() ? "+ " : "- ";
        JLabel lblAmt = new JLabel(prefix + CurencyUtill.format(tx.getAmount()));
        lblAmt.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblAmt.setForeground(tx.isIncome() ? GREEN_THEME : new Color(244, 67, 54));

        item.add(lblCat, BorderLayout.WEST);
        item.add(lblAmt, BorderLayout.EAST);

        return item;
    }
}