package com.Ijse.gdse.Controller;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.BookDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvailableBooksForm {

    public TableView tblAvailableBook;
    public TableColumn colBookId;
    public TableColumn colBookName;
    public TableColumn colBookAuthor;
    public TableColumn colbookPrice;

    ObservableList<BookDTO> observableList= FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllData();
        SetValueToTable();

    }

    public void loadAllData() throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT  * FROM  availablebooks");
        ResultSet resultSet= preparedStatement.executeQuery();


        while (resultSet.next()){
            observableList.add(new BookDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }

        tblAvailableBook.setItems(observableList);
    }

    private void SetValueToTable(){
        colBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        colBookAuthor.setCellValueFactory(new PropertyValueFactory<>("BookTittle"));
        colbookPrice.setCellValueFactory(new PropertyValueFactory<>("BookPrice"));
        tblAvailableBook.refresh();
        tblAvailableBook.setItems(observableList);
    }

}

