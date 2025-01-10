package view;

import controller.AuthenticationController;
import controller.TransactionController;
import model.classes.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewHistory extends JFrame {
    private TransactionController tc;
    private JTable historyTable;
    private DefaultTableModel tableModel;

    public ViewHistory() {
        initComponents();
        if (!new AuthenticationController().checkUser()) {
            this.dispose();
        } else {
            setVisible(true);
        }
    }

    private void initComponents() {
        setTitle("View History");
        setSize(800, 600);
        this.getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("View History", JLabel.CENTER);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Transaction ID");
        tableModel.addColumn("Delivery Type");
        tableModel.addColumn("Delivery Fee");
        tableModel.addColumn("Total Cost");
        tableModel.addColumn("Created at");
        tableModel.addColumn("Updated at");
        tableModel.addColumn("Details");



        historyTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(historyTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Transaction Details");
        addButton.addActionListener(e -> {
            this.dispose();
            new AddTransactionDetails();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            new MainMenu();
        });
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }


}
