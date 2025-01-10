package view;

import controller.PasswordToggleCommand;
import controller.RegisterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    public Register() {
        initComponents();
        this.setVisible(true);
    }

    public void initComponents() {
        this.setSize(400, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Register!");

        JLabel screenTitle = new JLabel("Register");
        screenTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 44));
        screenTitle.setBounds(116, 22, 200, 51);

        JPanel formLogin = new JPanel();
        formLogin.setBackground(Color.WHITE);
        formLogin.setLayout(null);
        formLogin.setBackground(Color.WHITE);
        formLogin.setBounds(44, 80, 300, 350);

        JLabel usernameUser = new JLabel("Username:");
        usernameUser.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        usernameUser.setBounds(5, 0, 130, 30);
        formLogin.add(usernameUser);

        JTextField usernameField = new JTextField(255);
        usernameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        usernameField.setBounds(0, 33, 296, 30);
        formLogin.add(usernameField);

        JLabel addressUser = new JLabel("Address:");
        addressUser.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        addressUser.setBounds(5, 66, 130, 30);
        formLogin.add(addressUser);

        JTextField addressField = new JTextField(255);
        addressField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        addressField.setBounds(0, 99, 296, 30);
        formLogin.add(addressField);

        JLabel passwordUser = new JLabel("Password:");
        passwordUser.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        passwordUser.setBounds(5, 140, 130, 30);
        formLogin.add(passwordUser);

        JPasswordField passwordField = new JPasswordField(255);
        passwordField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        passwordField.setBounds(0, 170, 246, 30);
        formLogin.add(passwordField);

        JButton showPass = new JButton("-");
        showPass.setBounds(246, 170, 50, 30);
        formLogin.add(showPass);

        PasswordToggleCommand passCommand = new PasswordToggleCommand(showPass, new JPasswordField[]{passwordField});
        showPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passCommand.execute();
            }
        });

        JLabel phoneUser = new JLabel("Phone:");
        phoneUser.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        phoneUser.setBounds(5, 210, 130, 30);
        formLogin.add(phoneUser);

        JTextField phoneField = new JTextField(255);
        phoneField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        phoneField.setBounds(0, 240, 296, 30);
        formLogin.add(phoneField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(43, 450, 300, 40);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(0, 0, 150, 40);
        buttonPanel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 0, 150, 40);
        buttonPanel.add(backButton);

        registerButton.addActionListener(e -> {
            String result = new RegisterController().register(
                    usernameField.getText(),
                    addressField.getText(),
                    phoneField.getText(),
                    new String(passwordField.getPassword())
            );
            if (!result.isBlank()) {
                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan. Silahkan Login.", "Sukses!", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new Login();
            }
        });

        backButton.addActionListener(e -> {
            this.dispose();
            new MainMenu();
        });

        add(screenTitle);
        add(formLogin);
        add(buttonPanel);
    }
}