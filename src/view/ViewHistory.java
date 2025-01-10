package view;

import controller.AuthenticationController;
import controller.AuthenticationHelper;
import controller.DeliveryDetailController;
import model.classes.DeliveryTransaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewHistory extends JFrame {
    private DeliveryDetailController dc;
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private JButton viewDetail;

    public ViewHistory() {
        dc = new DeliveryDetailController();
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
        loadDataToView();

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

        viewDetail = new JButton("View Details");
    }

    private void loadDataToView() {
        List<DeliveryTransaction> dt = dc.getAllDeliveryTransactions(AuthenticationHelper.getInstance().getUserId());

        tableModel.setRowCount(0);

        for (DeliveryTransaction d : dt) {
            Object[] rowData = { d.getTransaction().getTrans_id(), d.getTransaction().getDelivery_type(), d.getTransaction().getExpected_weight(), d.getTransaction().getTotal_cost(), d.getTransaction().getCreatedAt(), d.getDetails().getDate(), viewDetail };
            tableModel.addRow(rowData);
        }
    }
}