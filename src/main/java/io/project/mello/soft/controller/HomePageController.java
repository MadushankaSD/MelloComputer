package io.project.mello.soft.controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class HomePageController {
    public ImageView settings;
    public ImageView searchOrder;
    public ImageView placeOrder;
    public ImageView manageCustomer;
    public Text lable;
    public Text description;
    public AnchorPane root;
    public static JFXProgressBar progressBar;



    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "manageCustomer":
                    root = FXMLLoader.load(this.getClass().getResource("/viwe/customerpage.fxml"));
                    break;
                case "settings":
                    root = FXMLLoader.load(this.getClass().getResource("/viwe/itemPage.fxml"));
                    break;
                case "searchOrder":
                    root = FXMLLoader.load(this.getClass().getResource("/viwe/searchOrderpage.fxml"));
                    break;
                case "placeOrder":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/viwe/placeOrder.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();

                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();

        switch (icon.getId()) {
            case "manageCustomer":
                lable.setText("Manage Customers");
                description.setText("Click to add, edit, delete, search Customers");
                break;
            case "settings":
                lable.setText("Manage Items");
                description.setText("Click to add, edit, delete, search Items");
                break;
            case "placeOrder":
                lable.setText("Place Orders");
                description.setText("Click here if want to place a new order");
                break;
            case "searchOrder":
                lable.setText("Search Orders");
                description.setText("Click if want to search orders");
                break;
        }

        ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();

        DropShadow glow = new DropShadow();
        glow.setColor(Color.CORNFLOWERBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        icon.setEffect(glow);
    }



    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        ImageView img = (ImageView) mouseEvent.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();

        img.setEffect(null);
        lable.setText("WELCOME");
        description.setText("Please select one of above main operations to proceed");

    }

    public void buttonBackupOnAction(ActionEvent actionEvent) {

    }

    public void buttonRestoreOnAction(ActionEvent actionEvent) {

    }
}
