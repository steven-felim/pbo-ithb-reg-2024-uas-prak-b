package view;

import controller.AuthenticationController;
import controller.PasswordToggleCommand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public Login() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        this.setSize(400, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Login");
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Login");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        title.setBounds(143, 22, 200, 51);
        JPanel formLogin = new JPanel();
        formLogin.setBackground(Color.WHITE);
        formLogin.setLayout(null);
        formLogin.setBounds(44, 111, 300, 150);

        JLabel profileUser = new JLabel("Nomor telepon:");
        profileUser.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        profileUser.setBounds(88, 0, 130, 30);
        formLogin.add(profileUser);

        JTextField profileField = new JTextField(255);
        profileField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        profileField.setBounds(0, 33, 298, 30);
        formLogin.add(profileField);

        JLabel passwordUser = new JLabel("Password:");
        passwordUser.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        passwordUser.setBounds(113, 77, 130, 30);
        formLogin.add(passwordUser);

        JPasswordField passwordField = new JPasswordField(255);
        passwordField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        passwordField.setBounds(0, 110, 248, 30);
        passwordField.setEchoChar('*');
        formLogin.add(passwordField);

        JButton showPass = new JButton("-");
        showPass.setBounds(248, 110, 50, 30);
        formLogin.add(showPass);

        PasswordToggleCommand passCommand = new PasswordToggleCommand(showPass, new JPasswordField[]{passwordField});
        showPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passCommand.execute();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(43, 300, 300, 40);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(0, 0, 150, 40);
        buttonPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            String userProfile = profileField.getText();
            String password = passwordField.getText();
            int results = new AuthenticationController().login(userProfile, password);
            switch (results) {
                case 0:
                    JOptionPane.showMessageDialog(
                            null,
                            "Nomor telepon/Password Anda salah",
                            "Input yang Benar",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    break;
                case 1:
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Login berhasil");
                    new MainMenu();
                    break;
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 0, 150, 40);
        buttonPanel.add(backButton);

        backButton.addActionListener(e -> {
            this.dispose();
            new MainMenu();
        });

        add(title);
        add(formLogin);
        add(buttonPanel);
    }
}
