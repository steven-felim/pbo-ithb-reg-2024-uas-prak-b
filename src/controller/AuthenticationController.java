package controller;

import javax.swing.*;

import view.Login;
import view.MainMenu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationController {
	public int login(String userProfileField, String passwordField) {
		DatabaseHandler.getInstance().connect();
		String queryGetPassenger = "SELECT cust_id, name, address, phone, password FROM customer";
		try {
			PreparedStatement stmt = DatabaseHandler.getInstance().con.prepareStatement(queryGetPassenger);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("cust_id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String password = rs.getString("password");
				if (phone.equalsIgnoreCase(userProfileField)) {
					AuthenticationHelper.getInstance().setUserId(userId);
					return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseHandler.getInstance().disconnect();
		}

		return 0;
	}

	public void logout() {
	    AuthenticationHelper.getInstance().reset();
		new Login();
	}

	public boolean checkUser() {
		if (AuthenticationHelper.getInstance().getUserId() == 0) {
			new MainMenu();
			JOptionPane.showMessageDialog(
					null,
					"Anda tidak dapat menjalankan program tersebut, " +
							"dikarenakan harus login terlebih dahulu.",
					"Login Terlebih Dahulu",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
