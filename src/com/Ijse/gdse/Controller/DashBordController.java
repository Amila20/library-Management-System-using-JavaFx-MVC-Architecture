package com.Ijse.gdse.Controller;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.MemberDTO;
import com.Ijse.gdse.Model.MemberModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DashBordController {

    public AnchorPane DashBordContext;
    public Label lblMember;
    public AnchorPane LoadContext;
    public TableColumn colMemberContac;
    public TableColumn<Object, Object> colMemberId;
    public TableView<MemberDTO> tblMembers;
    public Label lblPublisher;
    public Label lblBooks;
    public Label lblMembers;
    public TableColumn<Object, Object> colMemberName;
    public TableColumn<Object, Object> colmemberAddress;
    public Label lblDate;
    public Label lblTime;
    public Label lblAvailbleMembers;
    MemberModel memberModel=new MemberModel();

    ObservableList<MemberDTO> itemList = FXCollections.observableArrayList();


    public void initialize() throws SQLException, ClassNotFoundException {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //lblTime.setText(new SimpleTimeZone(""));



        try {
            setValuesToTable();
            setLabel();

        } catch (SQLException e ) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException e){
            throw  new RuntimeException(e);
        }
    }


    public void btnLogOnAction(ActionEvent actionEvent) {
    }

    public void btnBooksOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(getClass().getResource("../View/BooksManagement.fxml"));
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(parent);
    }

    public void btnMemberOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(getClass().getResource("../View/MembersManagement.fxml"));
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(parent);
    }

    public void btnPublisherOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/PublisherManagement.fxml")));
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(parent);

    }
    public void btnIssuesOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(getClass().getResource("../View/IssuesBooks.fxml"));
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(parent);
    }
    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) LoadContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBord.fxml"))));
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
        tblMembers.setItems(observableList);
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
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colmemberAddress.setCellValueFactory(new PropertyValueFactory<>("memberAddress"));
        colMemberContac.setCellValueFactory(new PropertyValueFactory<>("memberContact"));
        tblMembers.setItems(itemList);

    }

    private void setLabel() throws SQLException, ClassNotFoundException {

        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT COUNT(memberID) FROM member");
        ResultSet resultSet= preparedStatement.executeQuery();
        resultSet.next();
        int MemberCount=resultSet.getInt(1);
        lblMembers.setText(String.valueOf(MemberCount));


        preparedStatement=connection.prepareStatement("SELECT COUNT(book_id) FROM book");
        ResultSet resultSet1=preparedStatement.executeQuery();
        resultSet1.next();
        int BookCount=resultSet1.getInt(1);
        lblBooks.setText(String.valueOf(BookCount));


        preparedStatement=connection.prepareStatement("SELECT count(publisherId) FROM publisher");
        ResultSet resultSet2=preparedStatement.executeQuery();
        resultSet2.next();
        int publisherCount=resultSet2.getInt(1);
        lblPublisher.setText(String.valueOf(publisherCount));

        preparedStatement=connection.prepareStatement("SELECT count(bookId) FROM availablebooks");
        ResultSet resultSet3=preparedStatement.executeQuery();
        resultSet3.next();
        int AvailableBookCount=resultSet3.getInt(1);
        lblAvailbleMembers.setText(String.valueOf(AvailableBookCount));


        }

    public void btnRetunBooksOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(getClass().getResource("../View/ReturnBooks.fxml"));
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(parent);
    }
}





