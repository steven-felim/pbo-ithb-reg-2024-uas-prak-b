package view;

import controller.AuthenticationController;
import controller.AuthenticationHelper;
import controller.TransactionController;
import javax.swing.*;
import java.awt.*;

public class AddTransaction extends JFrame {
    private JComboBox<String> categoryComboBox;
    private TransactionController tc;

    public AddTransaction() {
        tc = new TransactionController();
        initComponents();
        if (!new AuthenticationController().checkUser()) {
            this.dispose();
        } else {
            this.setVisible(true);
        }
    }

    private void initComponents() {
        setTitle("Add Transaction");
        setSize(900, 600);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Add Transaction");
        title.setBounds(330, 40, 700, 60);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(title);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Insert Name:"), gbc);

        JTextField nameField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Insert Address:"), gbc);

        JTextField addressField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Insert Phone:"), gbc);

        JTextField phoneField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Insert Estimated Weight:"), gbc);

        JTextField weightField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Select Delivery Details:"), gbc);

        categoryComboBox = new JComboBox<>(tc.getCategory());
        gbc.gridx = 1;
        mainPanel.add(categoryComboBox, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton confirmButton = new JButton("Confirm");
        JButton backButton = new JButton("Back");

        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        confirmButton.addActionListener(e -> {
            tc.addToTransaction(AuthenticationHelper.getInstance().getUserId(), categoryComboBox.getSelectedItem().toString(), Integer.parseInt(weightField.getText()), nameField.getText(), addressField.getText(), phoneField.getText());
        });

        backButton.addActionListener(e -> {
            this.dispose();
            new MainMenu();
        });

    }
}