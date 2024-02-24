package com.Ijse.gdse.Model;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.BookDTO;
import com.Ijse.gdse.Dto.ReturnDTO;
import com.Ijse.gdse.Dto.ReturnDetailsDTO;

import java.sql.*;
import java.util.ArrayList;

public class ReturnModel {

    public static String getNextOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  returnID FROM returnDetails ORDER BY  returnID DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return generateNextOrderId(resultSet.getString(1));

        }
        return generateNextOrderId(null);
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] ids = currentOrderId.split("R00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "R00" + id;
        }
        return "R001";
    }

    public boolean saveReturnBook(ReturnDTO returnDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO returnBook VALUES(?,?,?)");
        preparedStatement.setString(1, returnDTO.getReturnID());
        preparedStatement.setString(2, returnDTO.getMemberID());
        preparedStatement.setDate(3, returnDTO.getReturnDate());

        preparedStatement.executeUpdate();
        return true;

    }

    public static boolean SaveReturnBookDetails(ArrayList<ReturnDetailsDTO> details) throws SQLException, ClassNotFoundException {

        for (ReturnDetailsDTO det : details) {
            boolean isAdd =
                    connection(det.getReturnID(), det.getMemberId(), det.getBookID(), det.getBookName(), det.getReturnDate());

        }
        return true;

    }

    public static boolean connection(String returnId, String memberId, String bookId, String bookName, Date returnDate) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO  returnDetails VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, returnId);
        preparedStatement.setString(2, memberId);
        preparedStatement.setString(3, bookId);
        preparedStatement.setString(4, bookName);
        preparedStatement.setString(5, String.valueOf(returnDate));
        preparedStatement.execute();


        return false;
    }

    public static boolean connection2(String bookId, String bookName, String bookTittle, double bookPrice) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO  availablebooks VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookId);
        preparedStatement.setString(2, bookName);
        preparedStatement.setString(3, bookTittle);
        preparedStatement.setDouble(4, bookPrice);

        preparedStatement.execute();


        return true;
    }


    public static BookDTO SaveAvailableBookDetails(ArrayList<String> details2) throws SQLException, ClassNotFoundException {

        for (int i=0;i<details2.size();i++) {
            String bookID=details2.get(i);
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE book_id=?");
            preparedStatement.setObject(1, bookID);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                String bookName = resultSet.getString(2);
                String bookTittle = resultSet.getString(3);
                double bookPrice = resultSet.getDouble(4);

                boolean isADd = connection2(bookID, bookName, bookTittle, bookPrice);
                // System.out.println(bookID+","+bookName+","+bookTittle+","+bookPrice);*/
                // System.out.println("Book IDs : "+bookID);
            }
        }
        return null;
    }
}


