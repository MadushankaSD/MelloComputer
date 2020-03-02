package io.project.mello.soft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import io.project.mello.soft.Appinitializer;
import io.project.mello.soft.bussiness.custom.CustomerBO;
import io.project.mello.soft.bussiness.custom.ItemBO;
import io.project.mello.soft.bussiness.custom.OrderBO;
import io.project.mello.soft.dto.*;
import io.project.mello.soft.tm.PlaceOrderTM;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.*;

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
    public Text txtOrderId;
    public TableColumn btnDelete;

    private ItemBO itemBO = Appinitializer.ctx.getBean(ItemBO.class);
    private CustomerBO customerBO = Appinitializer.ctx.getBean(CustomerBO.class);
    private OrderBO orderBO = Appinitializer.ctx.getBean(OrderBO.class);

    double Total=0;
    String newValue;

    public void initialize(){

        txtItemName.setEditable(false);
        textItemQtyOnHand.setEditable(false);
        txtItemUnitPrice.setEditable(false);
        customerNameList.setVisible(false);

        txtItemDiscount.setText("0");

        initialState();

        txtDate.setText(String.valueOf(LocalDate.now()));
        SpinnerValueFactory<Integer> valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        txtItemWarranty.setValueFactory(valueFactory);

        tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("service"));
        tblPlaceOrder.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("warranty"));
        tblPlaceOrder.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tblPlaceOrder.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("delete"));


        txtItemCode.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()){
               getItemDetail(newValue);
            }
        });

        customerNameList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super String>) observable -> {
            String selectedItem = customerNameList.getSelectionModel().getSelectedItem();

            if(!selectedItem.equals("") || !selectedItem.equals(null)){
                customerName.setText(selectedItem);
            }
            customerNameList.setVisible(false);
        });

        /*tblPlaceOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue1) ->  {
            PlaceOrderTM selectedItem = tblPlaceOrder.getSelectionModel().getSelectedItem();
            Button delete = tblPlaceOrder.getSelectionModel().getSelectedItem().getDelete();
            if(delete.isPressed()){
                System.out.println("done");
            }
        });*/

        tblPlaceOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue1) -> {
            PlaceOrderTM selectedItem = tblPlaceOrder.getSelectionModel().getSelectedItem();
            Button delete = selectedItem.getDelete();
            if(delete.isPressed()){
                System.out.println("done");
            }
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
        try {
            ItemDTO item = itemBO.findItem(newValue);
            if (!item.equals(null)) {
                txtItemName.setText(item.getDescription());
                textItemQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                txtItemUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtItemCode.setFocusColor(Color.BLUE);
            }
        }catch (NullPointerException e){
            txtItemCode.setFocusColor(Color.RED);
            txtItemName.clear();
            textItemQtyOnHand.clear();
            txtItemUnitPrice.clear();
        }
    }

    public void btnNewOrderOnAction() {
        try {
            String lastOrderId = orderBO.getLastOrderId();

            if(lastOrderId==null){
                txtOrderId.setText("OD:001");
            }
            else {
                String[] split = lastOrderId.split(":");
                int val = Integer.parseInt(split[1]);
                val++;
                if(val<10){txtOrderId.setText("OD:00"+val);}
                else if(val<100){txtOrderId.setText("OD:0"+val);}
                else if(val<1000){txtOrderId.setText("OD:"+val);}
            }

        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong(GET last id)").show();
        }

        startOrder();
        txtItemCode.requestFocus();
    }

    public void btnAddToBillOnAction() {
        btnPlaceOrder.setDisable(false);
        ObservableList<PlaceOrderTM> orders = tblPlaceOrder.getItems();

      /*boolean isExists = false;
        for (PlaceOrderTM detail : tblPlaceOrder.getItems()) {
            if (detail.getItemName().equals(txtItemName.getText())) {
                isExists = true;

                if (btnSave.getText().equals("Update")) {
                    detail.setQty(qty);
                } else {
                    detail.setQty(detail.getQty() + Integer.parseInt(txtItemQty.getText()));
                }
                detail.setTotal(detail.getQty() * detail.getUnitPrice());
                tblOrderDetails.refresh();
                break;
            }
        }
        if(!isExists){}*/

        if(!serviceDescription.getText().isEmpty()){
            JFXButton btnDelete = new JFXButton("Delete");
            PlaceOrderTM placeOrderTM = new PlaceOrderTM(serviceDescription.getText(), txtItemWarranty.getValue(), Double.parseDouble(serviceCharge.getText()), btnDelete);
            btnDelete.setOnAction(event -> {
                double subTotal = placeOrderTM.getSubTotal();
                Total-=subTotal;
                txtTotal.setText(String.valueOf(Total));
                tblPlaceOrder.getItems().remove(placeOrderTM);

            });
            orders.add(placeOrderTM);
            Total+=Double.parseDouble(serviceCharge.getText());

            clearState();
        }
        if(!txtItemCode.getText().isEmpty()){

            JFXButton btnDelete = new JFXButton("Delete");

            double subTotal = (Double.parseDouble(txtItemUnitPrice.getText())-Double.parseDouble(txtItemDiscount.getText()))*Integer.parseInt(txtItemQty.getText());
            double discount = Integer.parseInt(txtItemQty.getText())*Double.parseDouble(txtItemDiscount.getText());
            PlaceOrderTM delete = new PlaceOrderTM(
                    txtItemName.getText(),
                    Integer.parseInt(txtItemQty.getText()),
                    Double.parseDouble(txtItemUnitPrice.getText()),
                    discount,
                    txtItemWarranty.getValue(),
                    subTotal,
                    btnDelete
                    );
                btnDelete.setOnAction(event -> {
                    double subTotal1 = delete.getSubTotal();
                    Total-=subTotal1;
                    txtTotal.setText(String.valueOf(Total));
                    tblPlaceOrder.getItems().remove(delete);
                });
            orders.add(delete);
            Total+=subTotal;
            clearState();


        }
        txtTotal.setText(String.valueOf(Total));
        txtItemCode.requestFocus();
    }

    public void btnPlaceOrderOnAction() {

        List<ServiceDTO> services = new ArrayList<>();
        List<OrderDetailDTO> orders = new ArrayList<>();

        for (PlaceOrderTM data:tblPlaceOrder.getItems()) {
            if(data.getService()==null){
                orders.add(new OrderDetailDTO(txtOrderId.getText(),itemBO.getItemId(data.getItemName()),data.getDiscount(),data.getQty(),data.getWarranty()));
            }else {
               services.add(new ServiceDTO(data.getService(),data.getSubTotal(),txtOrderId.getText(),data.getWarranty()));
            }
        }

        orderBO.placeOrder(new OrderDTO(txtOrderId.getText(),Date.valueOf(LocalDate.now()),newValue,services,orders));

        initialState();
        clearState();
        tblPlaceOrder.getItems().clear();
        txtRecervedAmount.clear();
        Total=0;
        txtTotal.setText("0.00");
        customerName.clear();

    }

    private void initialState(){

        txtItemCode.setDisable(true);
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
        btnPlaceOrder.setDisable(true);
    }

    private void clearState(){
        if(!txtItemCode.getText().isEmpty()){

            txtItemCode.clear();
            txtItemName.clear();
            textItemQtyOnHand.clear();
            txtItemUnitPrice.clear();
            txtItemDiscount.clear();
            txtItemDiscount.setText("0");
            txtItemQty.clear();

            txtItemWarranty.getValueFactory().setValue(0);


        }
        if(!serviceDescription.getText().isEmpty()){

            serviceCharge.clear();
            serviceDescription.clear();
            txtItemWarranty.getValueFactory().setValue(0);
            customerName.clear();
        }
    }

    private void startOrder(){
        txtItemCode.setDisable(false);
        txtItemName.setDisable(false);
        textItemQtyOnHand.setDisable(false);
        txtItemUnitPrice.setDisable(false);
        txtItemDiscount.setDisable(false);
        txtItemQty.setDisable(false);
        serviceCharge.setDisable(false);
        serviceDescription.setDisable(false);
        txtRecervedAmount.setDisable(false);
        txtItemWarranty.setDisable(false);
        customerName.setDisable(false);

        btnAddToBill.setDisable(false);
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        newValue = customerName.getText();
        if(keyEvent.getCode() == ENTER){
            customerNameList.setVisible(false);
            txtItemQty.requestFocus();
        }
        else if(!newValue.equals("")){
            getCustomerName(newValue);
            customerNameList.setVisible(true);
        }
        else{
            customerNameList.setVisible(false);
        }
    }

    public void itemCodeEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == ENTER){
            if(txtItemCode.getText().equals("")){
                serviceDescription.requestFocus();
            }
            else{
                customerName.requestFocus();
            }

        }
    }

    public void discountEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == ENTER){
            btnAddToBill.requestFocus();
        }
    }

    public void qtyEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == ENTER){
            txtItemDiscount.requestFocus();
        }
    }

    public void serviceEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == ENTER){
            serviceCharge.requestFocus();
        }
    }

    public void addToBillButonKey(KeyEvent keyEvent) {
        if(keyEvent.getCode() == SHIFT){
            btnAddToBillOnAction();
        }
    }

    public void serviceChargeEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == ENTER){
           btnAddToBill.requestFocus();
        }
    }

    public void warrantyEnter(KeyEvent keyEvent) {

    }

    public void placeOrderButtonKey(KeyEvent keyEvent) {
        if(keyEvent.getCode()==ALT){
            btnPlaceOrderOnAction();
        }
    }

    public void recerveAmountEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == ENTER){
            btnPlaceOrder.requestFocus();
        }
    }
}
