package io.project.mello.soft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

public class Appinitializer extends Application {

    public static AnnotationConfigApplicationContext ctx;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ctx= new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();


        URL resource = this.getClass().getResource("/viwe/mainpage.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene mainscene = new Scene(load);
        primaryStage.setScene(mainscene);
        primaryStage.setTitle("Mellow Computer");
        primaryStage.centerOnScreen();
        primaryStage.alwaysOnTopProperty();
        primaryStage.show();

    }
}
