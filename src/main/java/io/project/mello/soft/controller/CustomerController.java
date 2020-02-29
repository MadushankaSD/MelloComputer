package io.project.mello.soft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.project.mello.soft.tm.CustomerTM;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.ENTER;


public class CustomerController extends HomeImageController {
    public JFXTextField txtCustomerID;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtTelephoneNo;
    public JFXButton btnSave;
    public TableView<CustomerTM> tblCustomer;
    public TextField txtCustomerSearch;


    public void initialize(){


    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }

    public void btnAddNewCustomerOnAction(ActionEvent actionEvent) {

    }

    public void OnKeyPressed(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if(code==ENTER){
            JFXTextField source = (JFXTextField) keyEvent.getSource();

            switch (source.getId()){
                case "txtCustomerID" : if(txtCustomerID.getText().matches("^[a-zA-Z]([0-9])+$]")){ txtName.requestFocus();}else {};break;
                case "txtName" : txtAddress.requestFocus();break;
                case "txtAddress" : txtTelephoneNo.requestFocus();break;
                case "txtTelephoneNo" : btnSave.requestFocus();break;
            }
        }
    }
}
