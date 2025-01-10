package view;

import javax.swing.*;
import java.awt.*;

public class Logo {
    public ImageIcon getLogo() {
        ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir") + "\\src\\img\\logo.jpg");
        Image originalImage = imageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(62, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
}
