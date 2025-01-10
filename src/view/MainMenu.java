package view;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private JButton loginButton, registerButton, addTransButton, viewHistoryButton;

    public MainMenu() {
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        this.setSize(400, 360);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Main Menu");

        JLabel title = new JLabel("Pratama Delivery");
        title.setBounds(75, 30, 250, 40);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBounds(44, 80, 300, 300);

        loginButton = new JButton("Login");
        loginButton.setBounds(0, 0, 300, 40);
        panel.add(loginButton);

        loginButton.addActionListener(e ->  {
            this.dispose();
            new Login();
        });

        registerButton = new JButton("Register");
        registerButton.setBounds(0, 50, 300, 40);
        panel.add(registerButton);

        registerButton.addActionListener(e ->  {
            this.dispose();
            new Register();
        });

        addTransButton = new JButton("Add Transaction");
        addTransButton.setBounds(0, 100, 300, 40);
        panel.add(addTransButton);

        addTransButton.addActionListener(e ->  {
            this.dispose();
            new AddTransaction();
        });

        viewHistoryButton = new JButton("View Transactions");
        viewHistoryButton.setBounds(0, 150, 300, 40);
        panel.add(viewHistoryButton);

        viewHistoryButton.addActionListener(e ->  {
            this.dispose();
            new ViewHistory();
        });

        add(title);
        add(panel);
    }
}