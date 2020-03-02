package io.project.mello.soft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.project.mello.soft.Appinitializer;
import io.project.mello.soft.bussiness.custom.CustomerBO;
import io.project.mello.soft.dto.CustomerDTO;
import io.project.mello.soft.tm.CustomerTM;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.List;
import java.util.Optional;


import static javafx.scene.input.KeyCode.ENTER;


public class CustomerController extends HomeImageController {
    public JFXTextField txtCustomerID;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtTelephoneNo;
    public JFXButton btnSave;
    public TableView<CustomerTM> tblCustomer;
    public TextField txtCustomerSearch;
    public JFXButton btnDelete;
    public JFXButton btnClear;


    private CustomerBO customerBO = Appinitializer.ctx.getBean(CustomerBO.class);

    public void initialize(){

        initialState();

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephonNo"));

        try {

            ObservableList<CustomerTM> items = tblCustomer.getItems();
            List<CustomerDTO> allCustomers = customerBO.findAllCustomers();
            for (CustomerDTO data :
                    allCustomers) {
                items.add(new CustomerTM(data.getCustomerId(), data.getName(), data.getAddress(), data.getTelephonNo()));
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong,Contact 0712385700", ButtonType.OK).show();
        }



        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {
                CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("SAVE");
                    btnDelete.setDisable(true);
                    return;
                }

                btnSave.setText("UPDATE");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtTelephoneNo.setDisable(false);
                txtCustomerID.setText(selectedItem.getCustomerId());
                txtName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                txtTelephoneNo.setText(String.valueOf(selectedItem.getTelephonNo()));
                txtName.requestFocus();
                btnClear.setDisable(false);
            }
        });
        txtCustomerSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.equals(" ")){
                tblCustomer.getItems().clear();
                searchCustomer(newValue);
            }
        });
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (!txtName.getText().matches("[A-Za-z][A-Za-z. ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            return;
        }
        if (btnSave.getText().equals("SAVE")) {
            ObservableList<CustomerTM> customers = tblCustomer.getItems();
            try {

            customerBO.saveCustomer(new CustomerDTO(txtCustomerID.getText(),txtName.getText(),txtAddress.getText(),Long.parseLong(txtTelephoneNo.getText())));
            customers.add(new CustomerTM(txtCustomerID.getText(),txtName.getText(),txtAddress.getText(),Long.parseLong(txtTelephoneNo.getText())));


            initialState();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong(Save Customer)").show();
            }
        } else {
            CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
            try {
                customerBO.updateCustomer(new CustomerDTO(txtCustomerID.getText(),txtName.getText(),txtAddress.getText(),Long.parseLong(txtTelephoneNo.getText())));
                selectedCustomer.setName(txtName.getText());
                selectedCustomer.setAddress(txtAddress.getText());
                selectedCustomer.setTelephonNo(Long.parseLong(txtTelephoneNo.getText()));
                tblCustomer.refresh();

                initialState();
                btnSave.setText("SAVE");
                tblCustomer.getSelectionModel().clearSelection();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong(Update Customer)").show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
            try {
                customerBO.deleteCustomer(selectedItem.getCustomerId());
                tblCustomer.getItems().remove(selectedItem);
                btnClear.setDisable(false);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCustomerID.clear();
        txtName.clear();
        txtAddress.clear();
        txtTelephoneNo.clear();
        tblCustomer.getSelectionModel().clearSelection();
        initialState();

    }

    public void btnAddNewCustomerOnAction(ActionEvent actionEvent) {
        txtName.setDisable(false);
        txtName.requestFocus();
        txtAddress.setDisable(false);
        txtTelephoneNo.setDisable(false);
        txtCustomerID.clear();
        txtName.clear();
        txtAddress.clear();
        txtTelephoneNo.clear();
        btnSave.setDisable(false);
        btnClear.setDisable(false);
        tblCustomer.getSelectionModel().clearSelection();


        try {
            String lastCustomerId = customerBO.getLastCustomerId();

                if(lastCustomerId==null){
                    txtCustomerID.setText("C:001");
                }
                else {
                    String[] split = lastCustomerId.split(":");
                    int val = Integer.parseInt(split[1]);
                    val++;
                    txtCustomerID.setText("C:00"+val);
                }

        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong(GET last id)").show();
        }
    }

    public void OnKeyPressed(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if(code==ENTER){
            JFXTextField source = (JFXTextField) keyEvent.getSource();

            switch (source.getId()){
                case "txtCustomerID" : txtName.requestFocus();break;
                case "txtName" : txtAddress.requestFocus();break;
                case "txtAddress" : txtTelephoneNo.requestFocus();break;
                case "txtTelephoneNo" : btnSave.requestFocus();break;
            }
        }
    }

    private void initialState(){
        txtCustomerID.clear();
        txtName.clear();
        txtTelephoneNo.clear();
        txtAddress.clear();

        txtCustomerID.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtTelephoneNo.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        btnClear.setDisable(true);
    }


    private void searchCustomer(String newValue){
        try {
                ObservableList<CustomerTM> items = tblCustomer.getItems();
                List<CustomerDTO> searchCustomer = customerBO.searchCustomer(newValue+"%");
                for (CustomerDTO data :
                        searchCustomer) {
                    items.add(new CustomerTM(data.getCustomerId(), data.getName(), data.getAddress(), data.getTelephonNo()));
                }

            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Something went wrong,Contact 0712385700", ButtonType.OK).show();
            }
    }
}
