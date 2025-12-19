package CekelDuit.ui;

import CekelDuit.model.User;
import CekelDuit.model.UserRepository;
import CekelDuit.service.AuthService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;

    public LoginFrame() {
        setTitle("CekelDuit - Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(buildForm());
    }

    private JPanel buildForm() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtId = new JTextField();
        txtName = new JTextField();

        JButton btnLogin = new JButton("Masuk");
        btnLogin.addActionListener(e -> login());

        panel.add(new JLabel("ID Pengguna"));
        panel.add(txtId);
        panel.add(new JLabel("Nama"));
        panel.add(txtName);
        panel.add(btnLogin);

        return panel;
    }

    private void login() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "ID dan Nama wajib diisi");
            return;
        }

        User user = UserRepository.load(id, name);
        new MainFrame(user).setVisible(true);
        dispose(); // tutup login
    }
}
