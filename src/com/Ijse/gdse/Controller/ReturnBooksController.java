package com.Ijse.gdse.Controller;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.BookDTO;
import com.Ijse.gdse.Dto.MemberDTO;
import com.Ijse.gdse.Dto.ReturnDTO;
import com.Ijse.gdse.Dto.ReturnDetailsDTO;
import com.Ijse.gdse.Model.BookModel;
import com.Ijse.gdse.Model.MemberModel;
import com.Ijse.gdse.Model.ReturnModel;
import com.Ijse.gdse.View.TM.CartTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static java.sql.Date.valueOf;

public class ReturnBooksController {


    public AnchorPane ReturnContex;
    public TableView<CartTm> tblReturn;
    public TableColumn<Object, Object> tblColumBookId;
    public TableColumn tblCoulmBookName;
    public TableColumn tblCoulmBookTittle;
    public TableColumn tblCoulmBookIssuDate;
    public TableColumn tblCoulmBookOption;
    public TextField txtMemberName;
    public TextField txtBookName;
    public TextField txtMemberAddress;
    public TextField txtBookTittle;
    public ComboBox cmbMemberID;
    public ComboBox<String> cmbBookID;
    public TextField txtOrdderId;
    public TextField txtDate;
    ObservableList<CartTm> observableList=FXCollections.observableArrayList();
   // issuesModel issuesModel=new issuesModel();
    ReturnModel returnModel=new ReturnModel();
    int count=0;


    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllBookIds();
        loadAllMembersIds();
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        loadNextOrderId();


        cmbMemberID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            try {
                setMemberDetails((String) newValue);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        cmbBookID.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            try {
                setBookDetails(newValue);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });


        tblCoulmBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        tblColumBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        tblCoulmBookTittle.setCellValueFactory(new PropertyValueFactory<>("BookTittle"));
        tblCoulmBookIssuDate.setCellValueFactory(new PropertyValueFactory<>("IssuesDate"));
        tblCoulmBookOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }

    private void loadAllBookIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList= FXCollections.observableArrayList(BookModel.getIssuesBookIds());
        cmbBookID.setItems(observableList);
    }
    private void  loadAllMembersIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList=FXCollections.observableArrayList(MemberModel.getIssuesMembersId());
        cmbMemberID.setItems(observableList);
    }

    private void setMemberDetails(String selectedId) throws SQLException, ClassNotFoundException {
        MemberDTO memberDTO=MemberCrudController.getMemberDetails(selectedId);
        if (memberDTO != null){
            txtMemberName.setText(memberDTO.getMemberName());
            txtMemberAddress.setText(memberDTO.getMemberAddress());
        }else{
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void setBookDetails(String selectedId) throws SQLException, ClassNotFoundException {
        BookDTO bookDTO=BooksCrudController.getBookDetails(selectedId);
        if (bookDTO != null){
            txtBookTittle.setText(bookDTO.getBookTittle());
            txtBookName.setText(bookDTO.getBookName());
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went Wrong !").show();
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {


        String bookId=cmbBookID.getValue();
        String bookName=txtBookName.getText();
        String bookTittle=txtBookTittle.getText();
        java.sql.Date issueDate=valueOf(txtDate.getText());
        Button btn =new Button("Delete");


        int raw= isAlreadyExits(cmbBookID.getValue());


        if (raw==-1 & count<6){
            CartTm tm =new CartTm(bookId,bookName,bookTittle,issueDate,btn);
            CheckIssuesBookCount();
            observableList.add(tm);
            tblReturn.setItems(observableList);
            System.out.println(count);

        } else if (count>=6){
            new Alert(Alert.AlertType.ERROR,"Only 4 books can issue for one Member").show();
            return;

        } else{
            new Alert(Alert.AlertType.ERROR,"This Book is Already Issue!").show();
        }

        btn.setOnAction(event -> {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType=alert.showAndWait();

            if (buttonType.get()==ButtonType.YES){
                for (CartTm tm:observableList){
                    if (tm.getBookId().equals(tm.getBookId())){
                        observableList.remove(tm);
                        return;
                    }
                }

                {

                }
            }

        });

    }
    private int isAlreadyExits(String code){
        for (int i=0;i < observableList.size();i++){
            if (observableList.get(i).getBookId().equals(code)){

                return i;

            }
        }
        return -1;
    }
    private void CheckIssuesBookCount(){
        for (int i = 0; i < observableList.size(); i++) {
            count++;
        }
    }
    ;
    private void loadNextOrderId() {
        try {
            String orderId = returnModel.getNextOrderId();
            txtOrdderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReturnOnACtion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        java.sql.Date returnDate=valueOf(txtDate.getText());
        String returnId=txtOrdderId.getText();
        String memberId= (String) cmbMemberID.getValue();

        ReturnDTO returnDTO=new ReturnDTO(returnId,memberId,returnDate);

        ArrayList<ReturnDetailsDTO> returnBookDetails=new ArrayList<>();

        ArrayList<String> AvailableBookDetails = new ArrayList<>();

        for (CartTm tm:observableList){
            returnBookDetails.add(
                    new ReturnDetailsDTO(
                            txtOrdderId.getText(),
                            (String)cmbMemberID.getValue(),
                            tm.getBookId(),
                            tm.getBookName(),
                            tm.getIssuesDate()));

            AvailableBookDetails.add(new String(tm.getBookId()));
        }


        Connection connection= DBConnection.getInstance().getConnection();


        try {
            boolean saveIssueBooks = returnModel.saveReturnBook(returnDTO);
            if (saveIssueBooks){
                boolean saveIssueBookDetails=returnModel.SaveReturnBookDetails(returnBookDetails);
                returnModel.SaveAvailableBookDetails(AvailableBookDetails);
                cleartext();
                if (saveIssueBookDetails){
                    new Alert(Alert.AlertType.CONFIRMATION,"BookS Return Success !").show();

                }else{
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                }
            }else {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }




    }
    private  void cleartext(){
        cmbBookID.getItems().clear();
        cmbMemberID.getItems().clear();
        txtBookName.clear();
        txtBookTittle.clear();
        txtMemberAddress.clear();
        txtMemberName.clear();
    }
}
