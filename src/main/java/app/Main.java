package app;

import javax.swing.*;
import javafx.embed.swing.JFXPanel;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Study Nook");
            frame.getContentPane().setBackground(new Color(224, 236, 215));
            JFXPanel fxPanel = new AppFrame();

            frame.add(fxPanel);
            frame.setSize(800, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

//
//import javax.swing.*;
//
//public class Main {
//    public static void main(String[] args) {
//        AppBuilder appBuilder = new AppBuilder();
//        JFrame application = appBuilder
//                .addLoginView()
//                .addSignupView()
//                .addLoggedInView()
//                .addSignupUseCase()
//                .addLoginUseCase()
//                .addChangePasswordUseCase()
//                .addLogoutUseCase()
//                .build();
//
//        application.pack();
//        application.setLocationRelativeTo(null);
//        application.setVisible(true);
//    }
//}