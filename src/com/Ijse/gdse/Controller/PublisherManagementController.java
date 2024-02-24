package com.Ijse.gdse.Controller;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.PublisherDTO;
import com.Ijse.gdse.Model.PublisherModel;
import com.Ijse.gdse.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class PublisherManagementController {

    public AnchorPane PublisherContext;
    public TextField txtPublisherName;
    public TextField txtPublisherId;
    public TextField txtPublisherAddress;
    public TableView<PublisherDTO> tblPublisher;
    public TableColumn<Object, Object> tblColumPublisherID;
    public TableColumn<Object, Object> tblColumPublisherName;
    public TableColumn<Object, Object> tblColumPublisherAddress;
    public Button btnADD;
    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern idPattern=Pattern.compile("^(P00)[0-9]{1,3}$");

    PublisherModel publisherModel = new PublisherModel();
    String publisherId;
    String publisherName;
    String publisherAddress;
    ObservableList<PublisherDTO> itemList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        setValue();
        map.put(txtPublisherId,idPattern);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        publisherId = txtPublisherId.getText();
        publisherName = txtPublisherName.getText();
        publisherAddress = txtPublisherAddress.getText();

        PublisherDTO publisherDTO = new PublisherDTO(publisherId, publisherName, publisherAddress);
        boolean isAdd = PublisherModel.Save(publisherDTO);
        if (isAdd) {
            new Alert(Alert.AlertType.CONFIRMATION, "Publisher Add !").show();
            LoadAllPublisher();
            clearFields();
            map.put(txtPublisherId,idPattern);
            setBorders(txtPublisherId);
            } else {
            new Alert(Alert.AlertType.ERROR, "Something went Wrong, Try Again!").show();
        }


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        publisherId = txtPublisherId.getText();
        publisherName = txtPublisherName.getText();
        publisherAddress = txtPublisherAddress.getText();

        PublisherDTO publisherDTO = new PublisherDTO(publisherId, publisherName, publisherAddress);
        boolean isUpdate = PublisherModel.updatePublisher(publisherId, publisherDTO);
        if (isUpdate) {
            clearFields();
            LoadAllPublisher();
            new Alert(Alert.AlertType.CONFIRMATION, "Publisher is Updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went Wrong").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        publisherId = txtPublisherId.getText();
        boolean isDeleted = PublisherModel.deletePublisher(publisherId);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Publisher is Deleted!").show();
            tblPublisher.refresh();
            LoadAllPublisher();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went Wrong!").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        publisherId = txtPublisherId.getText();
        PublisherDTO publisherDTO = publisherModel.searchPublisher(publisherId);
        setValueToTextField(publisherDTO);
    }

    public void clearFields() {
        txtPublisherId.clear();
        txtPublisherName.clear();
        txtPublisherAddress.clear();
    }

    public void setValue() throws SQLException, ClassNotFoundException {
        List<PublisherDTO> addPublisherDetails = publisherModel.getPublisherDetails();
        addPublisherDetails.forEach((Data) -> {
            itemList.add(new PublisherDTO(
                    Data.getPublisherId(),
                    Data.getPublisherName(),
                    Data.getPublisherAddress()
            ));
            setValueToTable();
        });
    }

    private void setValueToTextField(PublisherDTO publisherDTO) {
        txtPublisherName.setText(publisherDTO.getPublisherName());
        txtPublisherAddress.setText(publisherDTO.getPublisherAddress());
    }

    private void setValueToTable() {
        tblColumPublisherID.setCellValueFactory(new PropertyValueFactory<>("PublisherId"));
        tblColumPublisherName.setCellValueFactory(new PropertyValueFactory<>("PublisherName"));
        tblColumPublisherAddress.setCellValueFactory(new PropertyValueFactory<>("PublisherAddress"));
        tblPublisher.setItems(itemList);
    }




public void LoadAllPublisher() throws SQLException, ClassNotFoundException {
    Connection connection= DBConnection.getInstance().getConnection();
    PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM publisher");
    ResultSet resultSet=preparedStatement.executeQuery();
    ObservableList<PublisherDTO> observableList=FXCollections.observableArrayList();
    while (resultSet.next()){
        observableList.add(new PublisherDTO(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3)
        ));
    }
    tblPublisher.setItems(observableList);
}

    public void onKeyRelesed(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnADD);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,  btnADD);;

            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {



            }
        }
    }
    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }


}