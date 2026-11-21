package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane(new Label("Hello, JavaFX!"));
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("My App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
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