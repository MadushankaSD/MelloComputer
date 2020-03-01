package io.project.mello.soft;

import io.project.mello.soft.controller.HomePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

public class Appinitializer extends Application {

    public static AnnotationConfigApplicationContext ctx;
    public AnchorPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Thread t1= new Thread(() -> {

            ctx= new AnnotationConfigApplicationContext();
            ctx.register(AppConfig.class);
            ctx.refresh();

         },"ctxTread");
       t1.start();

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
