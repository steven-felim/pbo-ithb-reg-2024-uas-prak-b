package controller;

import model.classes.DeliveryDetails;
import model.classes.DeliveryTransaction;
import model.classes.Transaction;
import model.enums.Status;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryDetailController {
    public void addToDeliveryDetail(DeliveryDetails dd) {
        if (!validateInputs(dd)) {
            JOptionPane.showMessageDialog(null, "All fields must be filled");
            return;
        }

        DatabaseHandler.getInstance().connect();
        String query = "INSERT INTO delivery_details (trans_id, status, current_position, evidence, date, updated_by) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DatabaseHandler.getInstance().con.prepareStatement(query)) {
            pstmt.setInt(1, dd.getTransaction_id());
            pstmt.setString(2, String.valueOf(dd.getStatus()));
            pstmt.setString(3, dd.getCurrent_position());
            pstmt.setString(4, dd.getEvidence());
            pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
            pstmt.setString(6, dd.getUpdated_by());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Detail added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "All fields must be filled");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Transaction ID must be a number");
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }
    }

    public boolean validateInputs(DeliveryDetails dd) {
        if (dd.getTransaction_id() <= 0) {
            System.out.println("Invalid transaction ID.");
            return false;
        }

        if (dd.getStatus() == null) {
            System.out.println("Status cannot be empty.");
            return false;
        }

        if (dd.getEvidence() == null || dd.getEvidence().isEmpty()) {
            System.out.println("Evidence cannot be empty.");
            return false;
        }

        if (dd.getCurrent_position() == null || dd.getCurrent_position().isEmpty()) {
            System.out.println("Current position cannot be empty.");
            return false;
        }

        if (dd.getUpdated_by() == null || dd.getUpdated_by().isEmpty()) {
            System.out.println("Updated by cannot be empty.");
            return false;
        }

        return true;
    }

    public DeliveryDetails getDeliveryDetails(int searchID) {
        DatabaseHandler.getInstance().connect();

        DeliveryDetails dd = new DeliveryDetails();

        try (
                Statement stmt = DatabaseHandler.getInstance().con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM delivery_details WHERE trans_id = " + searchID + ";")) {

            while (rs.next()) {
                dd.setId(rs.getInt("id"));
                dd.setTransaction_id(rs.getInt("trans_id"));
                dd.setStatus(Status.valueOf(rs.getString("status")));
                dd.setCurrent_position(rs.getString("current_position"));
                dd.setDate(rs.getDate("date"));
                dd.setUpdated_by(rs.getString("updated_by"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }
        return dd;
    }

    public List<DeliveryTransaction> getAllDeliveryTransactions(int userId) {
        List<DeliveryTransaction> list = new ArrayList<>();
        DatabaseHandler.getInstance().connect();
        try (
                Statement stmt = DatabaseHandler.getInstance().con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT t.*, MAX(d.date) AS latest_date FROM transactions AS t LEFT JOIN delivery_details AS d ON t.trans_id = d.trans_id WHERE t.customer_id = " + userId + ";");) {

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTrans_id(rs.getInt("trans_id"));
                t.setDelivery_type(rs.getString("delivery_type"));
                t.setExpected_weight(rs.getInt("expected_weight"));
                t.setTotal_cost(rs.getInt("total_cost"));
                t.setCreatedAt(rs.getDate("createdAt"));

                DeliveryDetails d = new DeliveryDetails();
                d.setDate(rs.getDate("latest_date"));

                list.add(new DeliveryTransaction(t, d));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }

        return list;
    }
}
