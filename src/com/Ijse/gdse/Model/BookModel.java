package com.Ijse.gdse.Model;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.BookDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel {
    public static boolean saveBook(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  book VALUES (?,?,?,?)");

        preparedStatement.setObject(1, bookDTO.getBookId());
        preparedStatement.setObject(2, bookDTO.getBookName());
        preparedStatement.setObject(3, bookDTO.getBookTittle());
        preparedStatement.setObject(4, bookDTO.getBookPrice());

        return preparedStatement.executeUpdate() >0;
    }

    public static boolean saveAvailableBooks(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  availablebooks VALUES (?,?,?,?)");

        preparedStatement.setObject(1, bookDTO.getBookId());
        preparedStatement.setObject(2, bookDTO.getBookName());
        preparedStatement.setObject(3, bookDTO.getBookTittle());
        preparedStatement.setObject(4, bookDTO.getBookPrice());

        return preparedStatement.executeUpdate() >0;
    }




    public static boolean updateBook(String bookId, String bookName, BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  book SET book_id=?,book_name=?,book_tittle=?,book_price=? WHERE book_id=? AND book_name=?");
        preparedStatement.setObject(1, bookDTO.getBookId());
        preparedStatement.setObject(2, bookDTO.getBookName());
        preparedStatement.setObject(3, bookDTO.getBookTittle());
        preparedStatement.setObject(4, bookDTO.getBookPrice());
        preparedStatement.setObject(5, bookId);
        preparedStatement.setObject(6, bookName);
        return preparedStatement.executeUpdate() > 0;

    }

    public static boolean deleteBook(String bookId, String bookName) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE book_id=? AND book_name=?");
        preparedStatement.setObject(1, bookId);
        preparedStatement.setObject(2, bookName);
        return preparedStatement.executeUpdate() > 0;

    }
    public static boolean deleteAvailableBook(String bookId, String bookName) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM availablebooks WHERE bookId=? AND bookName=?");
        preparedStatement.setObject(1, bookId);
        preparedStatement.setObject(2, bookName);
        return preparedStatement.executeUpdate() > 0;

    }

    public  BookDTO searchBook(String bookID) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE book_id=?");
        preparedStatement.setObject(1, bookID);
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next()) {
            String bookName = resultSet.getString(2);
            String bookTittle = resultSet.getString(3);
            double bookPrice = resultSet.getDouble(4);

            return new BookDTO(bookID, bookName, bookTittle, bookPrice);
        }

        return null;
    }
    public List<BookDTO> GetBookDetails() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM  book");
        ResultSet resultSet= preparedStatement.executeQuery();
        List<BookDTO> list=new ArrayList<>();

        while (resultSet.next()){
            String BookId=resultSet.getString(1);
            String BookName=resultSet.getString(2);
            String BookTittle=resultSet.getString(3);
            double BookPrice= resultSet.getDouble(4);

            list.add(new BookDTO(BookId,BookName,BookTittle,BookPrice));
        }

        return list;
    }

    public static ArrayList<String> getBookIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT bookId FROM availablebooks");
        ResultSet resultSet= preparedStatement.executeQuery();
        ArrayList<String> ids=new ArrayList<>();
        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    public static ArrayList<String> getIssuesBookIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT bookID FROM issuesoderdetails");
        ResultSet resultSet= preparedStatement.executeQuery();
        ArrayList<String> ids=new ArrayList<>();
        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
