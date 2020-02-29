package io.project.mello.soft.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class PlaceOrderController extends HomeImageController{

    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField textItemQtyOnHand;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemDiscount;
    public JFXTextField txtItemQty;
    public JFXTextField serviceDescription;
    public TableView tblPlaceOrder;
    public Text txtTotal;
    public JFXTextField serviceCharge;
    public Text txtDate;
    public Spinner<Integer> txtItemWarranty;
    public JFXTextField txtRecervedAmount;

    public void initialize(){
        txtDate.setText(String.valueOf(LocalDate.now()));
        SpinnerValueFactory<Integer> valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        txtItemWarranty.setValueFactory(valueFactory);

    }

    public void btnNewOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnAddToBillOnAction(ActionEvent actionEvent) {


    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }
}
