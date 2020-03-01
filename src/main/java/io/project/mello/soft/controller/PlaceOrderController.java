package io.project.mello.soft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import io.project.mello.soft.Appinitializer;
import io.project.mello.soft.bussiness.custom.CustomerBO;
import io.project.mello.soft.bussiness.custom.ItemBO;
import io.project.mello.soft.dto.CustomerDTO;
import io.project.mello.soft.dto.ItemDTO;
import io.project.mello.soft.tm.PlaceOrderTM;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.List;

public class PlaceOrderController extends HomeImageController{

    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField textItemQtyOnHand;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemDiscount;
    public JFXTextField txtItemQty;
    public JFXTextField serviceDescription;
    public TableView<PlaceOrderTM> tblPlaceOrder;
    public Text txtTotal;
    public JFXTextField serviceCharge;
    public Text txtDate;
    public Spinner<Integer> txtItemWarranty;
    public JFXTextField txtRecervedAmount;
    public JFXButton btnAddToBill;
    public JFXButton btnPlaceOrder;
    public JFXTextField customerName;
    public JFXListView<String> customerNameList;

    private ItemBO itemBO = Appinitializer.ctx.getBean(ItemBO.class);
    private CustomerBO customerBO = Appinitializer.ctx.getBean(CustomerBO.class);

    public void initialize(){

        txtItemName.setEditable(false);
        textItemQtyOnHand.setEditable(false);
        txtItemUnitPrice.setEditable(false);
        customerNameList.setVisible(false);

        initialState();

        txtDate.setText(String.valueOf(LocalDate.now()));
        SpinnerValueFactory<Integer> valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        txtItemWarranty.setValueFactory(valueFactory);

        tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("service"));
        tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tblPlaceOrder.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("delete"));

        txtItemCode.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()){
               getItemDetail(newValue);
            }
        });

        customerNameList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super String>) observable -> {
            String selectedItem = customerNameList.getSelectionModel().getSelectedItem();
            if(!selectedItem.equals("")||!selectedItem.equals(null)){
                customerName.setText(selectedItem);
            }
            customerNameList.setVisible(false);
        });
    }

    private void getCustomerName(String newValue) {
        List<CustomerDTO> searchCustomer = customerBO.searchCustomer(newValue + "%");
        customerNameList.getItems().clear();
        ObservableList<String> items = customerNameList.getItems();
        for (CustomerDTO data:
             searchCustomer) {
            items.add(data.getName());
        }

    }

    private void getItemDetail(String newValue) {
        ItemDTO item = itemBO.findItem(newValue);
        txtItemName.setText(item.getDescription());
        textItemQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        txtItemUnitPrice.setText(String.valueOf(item.getUnitPrice()));
    }

    public void btnNewOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnAddToBillOnAction(ActionEvent actionEvent) {


    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }

    private void initialState(){
        /*txtItemCode.setDisable(true);
        txtItemName.setDisable(true);
        textItemQtyOnHand.setDisable(true);
        txtItemUnitPrice.setDisable(true);
        txtItemDiscount.setDisable(true);
        txtItemQty.setDisable(true);
        serviceCharge.setDisable(true);
        serviceDescription.setDisable(true);
        txtRecervedAmount.setDisable(true);
        txtItemWarranty.setDisable(true);
        customerName.setDisable(true);

        btnAddToBill.setDisable(true);
        btnPlaceOrder.setDisable(true);*/
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        String newValue = customerName.getText();
        System.out.println(newValue);
        if(!newValue.equals("")){
            getCustomerName(newValue);
            customerNameList.setVisible(true);
        }
        else{
            customerNameList.setVisible(false);
        }
    }
}
