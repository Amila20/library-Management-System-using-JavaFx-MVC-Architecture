package com.Ijse.gdse.Controller;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.BookDTO;
import com.Ijse.gdse.Model.BookModel;
import com.Ijse.gdse.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class BooksManagementController  {
    public AnchorPane BooksManagementContext;
    public TextField txtBookId;
    public TextField txtBookName;
    public TextField txtAuthor;
    public TextField txtPrice;
    public TableView<BookDTO> tblBook;
    public TableColumn<Object, Object> tblBookId;
    public TableColumn tblBookName;
    public TableColumn tblBookTittle;
    public TableColumn tblBookPrice;
    public Button btnBookAdd;
    BookModel bookModel=new BookModel();
    String bookID;
    String bookName;
    String bookTittle;
    double bookPrice;
    ObservableList<BookDTO> itemList = FXCollections.observableArrayList();
    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern idPattern=Pattern.compile("^(B00)[0-9]{1,3}$");

    public BooksManagementController() throws SQLException, ClassNotFoundException {
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        setValuesToTable();
        //Validation
        Pattern idPattern=Pattern.compile("^(B00)[0-9]{1,3}$");
        map.put(txtBookId,idPattern);
    }

    ArrayList arrayList=bookModel.getBookIds();
    public void btnAddOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        bookID=txtBookId.getText();
        bookName=txtBookName.getText();
        bookTittle=txtAuthor.getText();
        bookPrice= Double.parseDouble(txtPrice.getText());
        BookDTO bookDTO=new BookDTO(bookID,bookName,bookTittle,bookPrice);

        int value=isAlreadyExits(bookID);

        if (value==-1) {
            BookModel.saveBook(bookDTO);
            BookModel.saveAvailableBooks(bookDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Book is ADD!").show();
            LoadAllBookData();
            clearFields();
            map.put(txtBookId, idPattern);
            setBorders(txtBookId);
        } else if (value!=-1){
            new Alert(Alert.AlertType.ERROR,"This id is Already Exits!").show();

        }else{
           new Alert(Alert.AlertType.ERROR,"Something went wrong! Try Again !").show();
       }
    }
    private int isAlreadyExits(String code){
        for (int i=0;i < arrayList.size();i++){
            if (arrayList.get(i).equals(code)){

                return i;

            }
        }
        return -1;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        bookID=txtBookId.getText();
        bookName=txtBookName.getText();
        boolean IsUpdate = BookModel.deleteBook(bookID,bookName);
        bookModel.deleteAvailableBook(bookID,bookName);
        if (IsUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Book id Deleted !").show();
            LoadAllBookData();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong! Try Again !").show();
        }
}

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        bookID=txtBookId.getText();
        bookName=txtBookName.getText();
        bookTittle=txtAuthor.getText();
        bookPrice= Double.parseDouble(txtPrice.getText());

        BookDTO bookDTO=new BookDTO(bookID,bookName,bookTittle,bookPrice);
        boolean IsUpdate= BookModel.updateBook(bookID,bookName,bookDTO);
        if (IsUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Book is Updated!").show();
            clearFields();
            LoadAllBookData();
            }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Try Again ! ").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
      try {bookID=txtBookId.getText();
        BookDTO bookDTO= bookModel.searchBook(bookID);
        setValueToTextFields(bookDTO);
      }catch (Exception e){

          new Alert(Alert.AlertType.ERROR,"Not in register this is ").show();}

 }
    public void clearFields(){
        txtBookName.clear();
        txtBookId.clear();
        txtAuthor.clear();
        txtPrice.clear();
    }
    public void setValueToTextFields(BookDTO bookDTO){
        txtBookName.setText(bookDTO.getBookName());
        txtAuthor.setText(bookDTO.getBookTittle());
        txtPrice.setText(String.valueOf(bookDTO.getBookPrice()));
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    public void setValuesToTable() throws SQLException, ClassNotFoundException {
        List<BookDTO> AddBookDetails=bookModel.GetBookDetails();
        AddBookDetails.forEach((Data)->{
            itemList.add(new BookDTO(
                    Data.getBookId(),
                    Data.getBookName(),
                    Data.getBookTittle(),
                    Data.getBookPrice()

            ));
            SetValueToTable();

        });
    }
    private void SetValueToTable(){
        tblBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        tblBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        tblBookTittle.setCellValueFactory(new PropertyValueFactory<>("BookTittle"));
        tblBookPrice.setCellValueFactory(new PropertyValueFactory<>("BookPrice"));
        tblBook.refresh();
        tblBook.setItems(itemList);
    }

    public void LoadAllBookData() throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book");
        ResultSet resultSet=preparedStatement.executeQuery();
        ObservableList<BookDTO> observableList=FXCollections.observableArrayList();
        while (resultSet.next()){
            observableList.add(new BookDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        tblBook.setItems(observableList);
    }
    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }
    public void textFields_Key_Released(javafx.scene.input.KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        ValidationUtil.validate(map,  btnBookAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,  btnBookAdd);;

            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
   public void btnViewAvailableBookList(ActionEvent actionEvent) throws IOException {
       Stage stage=new Stage();
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AvailableBooksForm.fxml"))));
       stage.show();

    }
}
