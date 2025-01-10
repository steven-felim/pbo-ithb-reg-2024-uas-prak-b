package controller;

import model.interfaces.Command;

import javax.swing.*;

// design pattern command
public class PasswordToggleCommand implements Command {
    private JPasswordField[] passwordFields;
    private JButton toggleButton;
    private boolean isPasswordVisible;

    public PasswordToggleCommand(JButton toggleButton, JPasswordField[] passwordFields) {
        this.passwordFields = passwordFields;
        this.toggleButton = toggleButton;
        this.isPasswordVisible = false;
    }

    // Show/hide password using Command Design Pattern
    @Override
    public void execute() {
        for (JPasswordField pinField : passwordFields) {
            if (isPasswordVisible) {
                pinField.setEchoChar('*');
            } else {
                pinField.setEchoChar((char) 0);
            }
        }

        if (isPasswordVisible) {
            toggleButton.setText("-");
        } else {
            toggleButton.setText("O");
        }

        isPasswordVisible = !isPasswordVisible;
    }
}