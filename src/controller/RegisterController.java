package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {
    public String register(String name, String address, String phone, String password) {
        DatabaseHandler.getInstance().connect();
        String query = "SELECT * FROM customer WHERE phone = ?";
        try {
            PreparedStatement stmt = DatabaseHandler.getInstance().con.prepareStatement(query);
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (phone.equals(rs.getString("name"))) {
                    return "Nomor telepon \"" + name + "\" sudah ada sebelumnya!";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }

        if (password.length() <= 4) {
            return "Buatlah password minimal 4 karakter!";
        }

        DatabaseHandler.getInstance().connect();
        String queryInsert = "INSERT INTO customer(name, address, phone, password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = DatabaseHandler.getInstance().con.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setString(4, password);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Masukkan semua field!";
        } finally {
            DatabaseHandler.getInstance().disconnect();
        }
        return "Berhasil melakukan registrasi!";
    }
}