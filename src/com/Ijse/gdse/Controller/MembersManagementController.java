package com.Ijse.gdse.Controller;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.MemberDTO;
import com.Ijse.gdse.Model.MemberModel;
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

import static java.sql.Date.valueOf;

public class MembersManagementController {

    public AnchorPane MemberContext;
    public TextField txtMemberId;
    public TextField txtmemberName;
    public TextField txtMemberAddress;
    public TextField txtContac;
    public TextField txtRegistorDate;
    public TableView<MemberDTO> tblMember;
    public TableColumn<Object, Object> tblMemberId;
    public TableColumn<Object, Object> tblmemberName;
    public TableColumn tblmemberAddress;
    public TableColumn tblMemberContac;
    public TableColumn tblMemberDate;
    public Button btnADD;
    String memberId;
    String memberName;
    String memberAddress;
    int  memberContact;
    Date memberRegistorDate;
    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern idPattern=Pattern.compile("^(M00)[0-9]{1,3}$");

    MemberModel memberModel=new MemberModel();
    ObservableList<MemberDTO> itemList = FXCollections.observableArrayList();
    public  void initialize() throws SQLException, ClassNotFoundException {
        setValuesToTable();

        Pattern idPattern=Pattern.compile("^(M00)[0-9]{1,3}$");
        map.put(txtMemberId,idPattern);

    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        memberId=txtMemberId.getText();
        memberName=txtmemberName.getText();
        memberAddress=txtMemberAddress.getText();
        memberContact= Integer.parseInt(txtContac.getText());
        memberRegistorDate= valueOf(txtRegistorDate.getText());

        MemberDTO memberDTO=new MemberDTO(memberId,memberName,memberAddress,memberContact,memberRegistorDate);

       boolean isAdd= MemberModel.Save(memberDTO);

       if (isAdd){
           new Alert(Alert.AlertType.CONFIRMATION,"Member is Register !").show();
           LoadALlMembersData();
           clearFields();
           map.put(txtMemberId,idPattern);
           setBorders(txtMemberId);

       }else{
           new Alert(Alert.AlertType.ERROR,"Something went Wrong!,Try Again!").show();

       }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        memberId=txtMemberId.getText();
        memberName=txtmemberName.getText();
        memberAddress=txtMemberAddress.getText();
        memberContact= Integer.parseInt(txtContac.getText());
        memberRegistorDate= valueOf(txtRegistorDate.getText());

        MemberDTO memberDTO=new MemberDTO(memberId,memberName,memberAddress,memberContact,memberRegistorDate);
        boolean IsUpdate= MemberModel.updateMember(memberId,memberName,memberDTO);
        if (IsUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Member is Updated!").show();
            LoadALlMembersData();
            clearFields();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!, Try Again !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        memberId=txtMemberId.getText();
        memberName=txtmemberName.getText();
        boolean IsDelete= MemberModel.deleteMember(memberId,memberName);
        if (IsDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            clearFields();
            LoadALlMembersData();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went Wrong, Try Again!").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       try {
           memberId = txtMemberId.getText();
           MemberDTO memberDTO = memberModel.searchMember(memberId);
           setValueToTextFields(memberDTO);
       }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"This Id is not valid!").show();
       }
    }

    public void setValueToTextFields(MemberDTO memberDTO){
        txtmemberName.setText(memberDTO.getMemberName());
        txtMemberAddress.setText(memberDTO.getMemberAddress());
        txtContac.setText(String.valueOf(memberDTO.getMemberContact()));
        txtRegistorDate.setText(String.valueOf(memberDTO.getMemberRegistorDate()));
    }


    public void setValuesToTable() throws SQLException, ClassNotFoundException {
        List<MemberDTO> AddMemberDetails=memberModel.GetMembersDetails();
        AddMemberDetails.forEach((Data)->{
            itemList.add(new MemberDTO(
                    Data.getMemberId(),
                    Data.getMemberName(),
                    Data.getMemberAddress(),
                    Data.getMemberContact(),
                    Data.getMemberRegistorDate()
            ));
            setTable();
        });
    }

    public void setTable(){


        tblMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tblmemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        tblmemberAddress.setCellValueFactory(new PropertyValueFactory<>("memberAddress"));
        tblMemberContac.setCellValueFactory(new PropertyValueFactory<>("memberContact"));
        tblMemberDate.setCellValueFactory(new PropertyValueFactory<>("memberRegistorDate"));


        tblMember.setItems(itemList);





    }
    public void btnClearOnAction(ActionEvent actionEvent) {
    clearFields();
    }
    public void clearFields(){
        txtMemberId.clear();
        txtmemberName.clear();
        txtMemberAddress.clear();
        txtContac.clear();
        txtRegistorDate.clear();

    }
    public void LoadALlMembersData() throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM member");
        ResultSet resultSet=preparedStatement.executeQuery();
        ObservableList<MemberDTO> observableList=FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(new MemberDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5)
            ));
        }
        tblMember.setItems(observableList);
    }
    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }


    public void textFields_Key_Released(KeyEvent keyEvent) {
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
}

