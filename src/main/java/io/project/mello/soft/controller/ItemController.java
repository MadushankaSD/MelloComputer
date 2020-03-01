package io.project.mello.soft.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.project.mello.soft.Appinitializer;
import io.project.mello.soft.bussiness.custom.ItemBO;
import io.project.mello.soft.dto.ItemDTO;
import io.project.mello.soft.tm.ItemTM;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.Optional;

public class ItemController extends HomeImageController {
    public JFXTextField txtItemCode;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDescription;
    public TextField searchItem;
    public TableView<ItemTM> tblItems;
    public JFXButton btnAddNewItem;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXButton btnPrint;

    private ItemBO itemBO = Appinitializer.ctx.getBean(ItemBO.class);

    public void initialize(){
        initialState();

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        try {

            ObservableList<ItemTM> items = tblItems.getItems();
            List<ItemDTO> allItem = itemBO.findAllItems();
            for (ItemDTO data :
                    allItem) {
                items.add(new ItemTM(data.getItemCode(), data.getDescription(), data.getUnitPrice(), data.getQtyOnHand()));
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong,Contact 0712385700", ButtonType.OK).show();
        }


        tblItems.getSelectionModel().selectedItemProperty().addListener(observable -> {
            ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();

            if (selectedItem == null) {
                btnSave.setText("SAVE");
                btnDelete.setDisable(true);
                return;
            }

            btnSave.setText("UPDATE");
            btnSave.setDisable(false);
            btnDelete.setDisable(false);

            txtDescription.setDisable(false);
            txtUnitPrice.setDisable(false);
            txtQtyOnHand.setDisable(false);

            txtItemCode.setText(selectedItem.getItemCode());
            txtDescription.setText(selectedItem.getDescription());
            txtQtyOnHand.setText(String.valueOf(selectedItem.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));

            btnClear.setDisable(false);
        });

        searchItem.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.equals(" ")){
                tblItems.getItems().clear();
                searchItem(newValue);
            }
        });

    }

    private void searchItem(String newValue) {
        try {
            ObservableList<ItemTM> items = tblItems.getItems();
            List<ItemDTO> itemDTOS = itemBO.searchItems(newValue + "%");
            for (ItemDTO data :
                    itemDTOS) {
                items.add(new ItemTM(data.getItemCode(), data.getDescription(), data.getUnitPrice(), data.getQtyOnHand()));
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong,Contact 0712385700", ButtonType.OK).show();
        }
    }


    private void initialState() {
        txtItemCode.clear();
        txtQtyOnHand.clear();
        txtDescription.clear();
        txtUnitPrice.clear();

        txtItemCode.setDisable(true);
        txtDescription.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);

        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        btnClear.setDisable(true);
    }

    public void btnAddNewItemOnAction(ActionEvent actionEvent) {
        txtDescription.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtItemCode.setDisable(false);
        txtItemCode.requestFocus();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtDescription.clear();
        txtItemCode.clear();

        btnSave.setDisable(false);
        btnClear.setDisable(false);
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtDescription.getText().matches("[A-Za-z][A-Za-z. ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Description").show();
            return;
        }

        if (btnSave.getText().equals("SAVE")) {
            ObservableList<ItemTM> items = tblItems.getItems();

            try {
                itemBO.saveItem(new ItemDTO(txtItemCode.getText(),txtDescription.getText(),Double.valueOf(txtUnitPrice.getText()),Long.parseLong(txtQtyOnHand.getText())));
                items.add(new ItemTM(txtItemCode.getText(),txtDescription.getText(),Double.valueOf(txtUnitPrice.getText()),Long.parseLong(txtQtyOnHand.getText())));


                initialState();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong(Save Items)").show();
            }
        } else {
            ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
            try {
                itemBO.updateItem(new ItemDTO(txtItemCode.getText(),txtDescription.getText(),Double.valueOf(txtUnitPrice.getText()),Long.parseLong(txtQtyOnHand.getText())));
                selectedItem.setDescription(txtDescription.getText());
                selectedItem.setQtyOnHand(Long.parseLong(txtQtyOnHand.getText()));
                selectedItem.setUnitPrice(Double.valueOf(txtUnitPrice.getText()));
                tblItems.refresh();

                initialState();
                btnSave.setText("SAVE");
                tblItems.getSelectionModel().clearSelection();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong(Update Item)").show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
            try {
                itemBO.deleteItem(selectedItem.getItemCode());
                tblItems.getItems().remove(selectedItem);
                btnClear.setDisable(false);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtItemCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        tblItems.getSelectionModel().clearSelection();
        initialState();
    }

    public void btnPrintReportOnAction(ActionEvent actionEvent) {

    }

}
