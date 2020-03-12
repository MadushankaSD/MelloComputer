package io.project.mello.soft.controller;

import io.project.mello.soft.Appinitializer;
import io.project.mello.soft.bussiness.custom.OrderBO;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

public class BalanceController {
    public Text txtBalance;
    public Text txtTodayCollection;
    public AnchorPane root;

    public static double totalfrom;
    public static String recerved;

    private OrderBO orderBO = Appinitializer.ctx.getBean(OrderBO.class);

    public void initialize(){
        txtBalance.requestFocus();
       double x= Double.parseDouble(recerved);
       txtBalance.setText(String.valueOf(x-totalfrom));


        double dailyCollection = orderBO.getDailyCollection(Date.valueOf(LocalDate.now()));
        txtTodayCollection.setText(String.valueOf(dailyCollection));
    }

    public void btnOkOnAction(ActionEvent actionEvent) {
        btn_BackOnAction();
    }

    public void btnOkEnterd(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER){
            btn_BackOnAction();
        }
    }

    public void btn_BackOnAction() {
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.close();
    }
}
