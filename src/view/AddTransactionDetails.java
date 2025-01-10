package view;

import controller.AuthenticationController;
import controller.DeliveryDetailController;
import model.classes.DeliveryDetails;
import model.enums.Status;

import javax.swing.*;
import java.awt.*;

public class AddTransactionDetails extends JFrame {
    private JComboBox<String> statusComboBox;
    private DeliveryDetailController dc;

    public AddTransactionDetails() {
        dc = new DeliveryDetailController();
        initComponents();
        if (!new AuthenticationController().checkUser()) {
            this.dispose();
        } else {
            this.setVisible(true);
        }
    }

    private void initComponents() {
        DeliveryDetails data = new DeliveryDetails();
        setTitle("Add Transaction Details");
        setSize(900, 600);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Add Transaction Details");
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
        mainPanel.add(new JLabel("Insert Transaction ID:"), gbc);

        JTextField transIDField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(transIDField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Insert Status:"), gbc);

        Status[] s = Status.values();
        String[] status = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            status[i] = s[i].toString();
        }

        statusComboBox = new JComboBox<>(status);
        gbc.gridx = 1;
        mainPanel.add(statusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Insert Current Position:"), gbc);

        JTextField positionField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Insert Evidence:"), gbc);

        JFileChooser fcFoto = new JFileChooser();
        JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(e -> {
            int returnValue = fcFoto.showOpenDialog(mainPanel);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                data.setEvidence(fcFoto.getSelectedFile().getAbsolutePath());
                browseButton.setText(data.getEvidence());
            }
        });
        gbc.gridx = 1;
        mainPanel.add(browseButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Updated by:"), gbc);

        JTextField updatedByField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(updatedByField, gbc);

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
            data.setTransaction_id(Integer.parseInt(transIDField.getText()));
            data.setCurrent_position(positionField.getText());
            data.setStatus(Status.valueOf(statusComboBox.getSelectedItem().toString()));
            data.setUpdated_by(updatedByField.getText());
            dc.addToDeliveryDetail(data);
        });

        backButton.addActionListener(e -> {
            this.dispose();
            new MainMenu();
        });

    }
}
