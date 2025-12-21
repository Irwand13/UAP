package CekelDuit.ui;

import CekelDuit.model.User;
import CekelDuit.model.UserRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;

    // Warna Modern & Sangar
    private final Color GRADIENT_START = new Color(0, 184, 76);
    private final Color GRADIENT_END = new Color(0, 80, 30);
    private final Color BG_DARK = new Color(15, 15, 15);
    private final Color FIELD_BG = new Color(30, 30, 30);
    private final Color TEXT_WHITE = new Color(245, 245, 245);

    public LoginFrame() {
        setTitle("CekelDuit - Login");
        setSize(420, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panel Utama dengan Gradient
        setContentPane(new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, GRADIENT_START, 0, getHeight(), BG_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        initComponents();
    }

    private void initComponents() {
        // Panel Kartu dengan Efek Bayangan & Sudut Melengkung
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Shadow sederhana
                g2.setColor(new Color(0, 0, 0, 80));
                g2.fillRoundRect(5, 5, getWidth()-5, getHeight()-5, 30, 30);

                // Background Kartu
                g2.setColor(new Color(25, 25, 25));
                g2.fillRoundRect(0, 0, getWidth()-5, getHeight()-5, 30, 30);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(340, 500));
        card.setBorder(new EmptyBorder(30, 25, 30, 25));

        // 1. Header Section
        JLabel lblLogo = new JLabel("");
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitle = new JLabel("CekelDuit");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitle.setForeground(GRADIENT_START);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. Input ID (Hanya Angka)
        txtId = createStyledField("ID");
        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Tolak jika bukan angka
                }
            }
        });

        // 3. Input Nama
        txtName = createStyledField("Nama");

        // 4. Tombol Masuk
        JButton btnLogin = new JButton("MASUK");
        styleMainButton(btnLogin);
        btnLogin.addActionListener(e -> login());

        // Susun komponen ke dalam kartu
        card.add(lblLogo);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblTitle);
        card.add(Box.createRigidArea(new Dimension(0, 40)));

        card.add(createLabel("ID PENGGUNA (ANGKA)"));
        card.add(txtId);
        card.add(Box.createRigidArea(new Dimension(0, 20)));

        card.add(createLabel("NAMA LENGKAP"));
        card.add(txtName);
        card.add(Box.createRigidArea(new Dimension(0, 40)));

        card.add(btnLogin);

        add(card);
    }

    private JTextField createStyledField(String placeholder) {
        JTextField tf = new JTextField();
        tf.setMaximumSize(new Dimension(280, 45));
        tf.setBackground(FIELD_BG);
        tf.setForeground(TEXT_WHITE);
        tf.setCaretColor(GRADIENT_START);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tf.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        tf.setAlignmentX(Component.CENTER_ALIGNMENT);
        return tf;
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(new Color(120, 120, 120));
        l.setFont(new Font("SansSerif", Font.BOLD, 10));
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        l.setBorder(new EmptyBorder(0, 0, 5, 0));
        return l;
    }

    private void styleMainButton(JButton btn) {
        btn.setMaximumSize(new Dimension(280, 45));
        btn.setBackground(GRADIENT_START);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void login() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
            return;
        }

        User user = UserRepository.load(id, name);

        // Tambahkan pengecekan null di sini
        if (user != null) {
            new MainFrame(user).setVisible(true);
            dispose();
        }
        // Jika null, tetap di halaman login karena sudah ada pesan error dari Repository
    }
}