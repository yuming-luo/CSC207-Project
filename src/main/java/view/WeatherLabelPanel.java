package view;

import javax.swing.*;

public class WeatherLabelPanel extends JPanel{
    WeatherLabelPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
