package controller;

import model.classes.DeliveryDetails;
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

public class TransactionController {
    public void addToTransaction(int customer_id, String delivery_type, int expected_weight, String receipt_name, String receipt_address, String receipt_phone) {
        if (!validateInputs(customer_id, delivery_type, expected_weight, receipt_name, receipt_address, receipt_phone)) {
            JOptionPane.showMessageDialog(null, "All fields must be filled");
            return;
        }

        DatabaseHandler.getInstance().connect();
        String query = "INSERT INTO transactions (customer_id, delivery_type, expected_weight, total_cost, createdAt, receipt_name, receipt_address, receipt_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DatabaseHandler.getInstance().con.prepareStatement(query)) {
            pstmt.setInt(1, customer_id);
            pstmt.setString(2, delivery_type);
            pstmt.setInt(3, expected_weight);
            pstmt.setInt(4, (expected_weight * getDeliveryFee(delivery_type)));
            pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
            pstmt.setString(6, receipt_name);
            pstmt.setString(7, receipt_address);
            pstmt.setString(8, receipt_phone);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transaction added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "All fields must be filled");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Weight must be a number");
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }
    }

    public boolean validateInputs(int customer_id, String delivery_type, int expected_weight, String receipt_name, String receipt_address, String receipt_phone) {
        if (customer_id <= 0) {
            System.out.println("Invalid customer ID.");
            return false;
        }

        if (delivery_type == null || delivery_type.isEmpty()) {
            System.out.println("Delivery type cannot be empty.");
            return false;
        }

        if (expected_weight <= 0) {
            System.out.println("Expected weight must be greater than 0.");
            return false;
        }

        if (receipt_name == null || receipt_name.isEmpty()) {
            System.out.println("Receipt name cannot be empty.");
            return false;
        }

        if (receipt_address == null || receipt_address.isEmpty()) {
            System.out.println("Receipt address cannot be empty.");
            return false;
        }

        if (receipt_phone == null || receipt_phone.isEmpty()) {
            System.out.println("Receipt phone cannot be empty.");
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

    public String[] getCategory() {
        DatabaseHandler.getInstance().connect();
        String[] cat = new String[3];
        int index = 0;
        try (
                Statement stmt = DatabaseHandler.getInstance().con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM delivery_category")) {
            while (rs.next() && index < 3) {
                cat[index++] = rs.getString("category");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }
        return cat;
    }

    public int getDeliveryFee(String category) {
        DatabaseHandler.getInstance().connect();
        int fee = 0;
        try (
                Statement stmt = DatabaseHandler.getInstance().con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT delivery_fee FROM delivery_category WHERE category = '" + category + "';")) {
            while (rs.next()) {
                fee = rs.getInt("delivery_fee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }
        return fee;
    }
}