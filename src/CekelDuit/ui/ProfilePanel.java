package CekelDuit.ui;

import CekelDuit.model.User;
import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ProfilePanel extends JPanel {

    private final MainFrame mainFrame;

    private JLabel lblNameHeader;
    private JTextField txtNameField;
    private JLabel lblSaldoValue;
    private JLabel lblTxValue;
    private JLabel lblTargetValue;

    // Warna sesuai gambar
    private final Color PURPLE_THEME = new Color(3, 166, 3);
    private final Color BG_COLOR = new Color(248, 249, 253);
    private final Color TEXT_GRAY = new Color(150, 150, 150);

    public ProfilePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);

        // Parent panel untuk menumpuk header dan konten
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(null); // Menggunakan null layout untuk efek overlay kartu
        mainContainer.setBackground(BG_COLOR);

        // 1. Header Ungu
        JPanel headerPurple = new JPanel();
        headerPurple.setBackground(PURPLE_THEME);
        headerPurple.setBounds(0, 0, 500, 200); // Sesuaikan lebar
        headerPurple.setLayout(null);

        JLabel lblTitle = new JLabel("Profil & Pengaturan");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setBounds(60, 20, 200, 30);
        headerPurple.add(lblTitle);

        JLabel lblAvatar = new JLabel("👤", SwingConstants.CENTER);
        lblAvatar.setFont(new Font("SansSerif", Font.PLAIN, 40));
        lblAvatar.setOpaque(true);
        lblAvatar.setBackground(new Color(255, 255, 255, 80));
        lblAvatar.setBounds(30, 70, 70, 70);
        // Membuat avatar bulat sederhana (limitasi Swing standar)
        headerPurple.add(lblAvatar);

        lblNameHeader = new JLabel("Arek Malang");
        lblNameHeader.setForeground(Color.WHITE);
        lblNameHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNameHeader.setBounds(115, 85, 200, 25);
        headerPurple.add(lblNameHeader);

        JLabel lblSub = new JLabel("Universitas Muhammadiyah Malang");
        lblSub.setForeground(new Color(230, 230, 230));
        lblSub.setBounds(115, 105, 200, 25);
        headerPurple.add(lblSub);

        // 2. Kartu Statistik (Floating Card)
        JPanel statsCard = createRoundedPanel(15);
        statsCard.setBackground(Color.WHITE);
        statsCard.setBounds(30, 160, 340, 80); // Posisi menimpa header
        statsCard.setLayout(new GridLayout(1, 3));

        statsCard.add(createStatItem("Saldo", lblSaldoValue = new JLabel("Rp 0"), new Color(46, 204, 113)));
        statsCard.add(createStatItem("Transaksi", lblTxValue = new JLabel("0"), Color.BLACK));
        statsCard.add(createStatItem("Target", lblTargetValue = new JLabel("0"), Color.BLACK));

        // 3. Form Informasi Pribadi
        JPanel infoPanel = createRoundedPanel(15);
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBounds(30, 260, 340, 230);
        infoPanel.setLayout(null);

        JLabel lblInfoTitle = new JLabel("Informasi Pribadi");
        lblInfoTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblInfoTitle.setBounds(20, 15, 150, 20);
        infoPanel.add(lblInfoTitle);

        JLabel lblNameLabel = new JLabel("Nama");
        lblNameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblNameLabel.setBounds(20, 60, 100, 20);
        infoPanel.add(lblNameLabel);

        txtNameField = new JTextField();
        txtNameField.setBounds(20, 85, 300, 35);
        txtNameField.setBackground(new Color(245, 245, 245));
        txtNameField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        infoPanel.add(txtNameField);

        JButton btnSimpan = new JButton("Simpan Perubahan");
        btnSimpan.setBounds(20, 170, 300, 40);
        btnSimpan.setBackground(PURPLE_THEME);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBorderPainted(false);
        btnSimpan.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSimpan.addActionListener(e -> editName());
        infoPanel.add(btnSimpan);

        // Tambahkan semua ke container
        mainContainer.add(statsCard);
        mainContainer.add(infoPanel);
        mainContainer.add(headerPurple);

        add(mainContainer, BorderLayout.CENTER);
    }

    private JPanel createStatItem(String title, JLabel valueLabel, Color valueColor) {
        JPanel p = new JPanel(new GridLayout(2, 1));
        p.setOpaque(false);
        JLabel t = new JLabel(title, SwingConstants.CENTER);
        t.setForeground(TEXT_GRAY);
        t.setFont(new Font("SansSerif", Font.PLAIN, 12));

        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        valueLabel.setForeground(valueColor);

        p.add(t);
        p.add(valueLabel);
        return p;
    }

    // Helper untuk membuat panel dengan sudut melengkung
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

    private void editName() {
        String name = txtNameField.getText();
        if (name != null && !name.trim().isEmpty()) {
            mainFrame.setUsername(name);
            JOptionPane.showMessageDialog(this, "Nama berhasil diperbarui!");
            refresh();
        }
    }

    public void refresh() {
        User user = mainFrame.getUser();
        String name = user.getName();

        lblNameHeader.setText(name);
        txtNameField.setText(name);
        lblSaldoValue.setText(CurencyUtill.format(user.getBalance()));
        lblTxValue.setText(String.valueOf(user.getTransactions().size()));
        // Target bisa disesuaikan jika ada datanya di model User
        lblTargetValue.setText("0");
    }
}