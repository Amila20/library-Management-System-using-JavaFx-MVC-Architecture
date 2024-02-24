package com.Ijse.gdse.Model;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.IssuesOder;
import com.Ijse.gdse.Dto.IssuesOrderDetails;

import java.sql.*;
import java.util.ArrayList;

public class issuesModel {
    public static boolean SaveIssuesBookDetails(ArrayList<IssuesOrderDetails> details) throws SQLException, ClassNotFoundException {

        for (IssuesOrderDetails det : details) {
            boolean isAdd =

                    connection(det.getOrderId(), det.getMemberId(), det.getBookId(), det.getBookName(), det.getIssueDate());
        }
        return true;

    }
    public static boolean DeleteAvailableBookDetails(ArrayList<String> details) throws SQLException, ClassNotFoundException {

        for (String id : details) {
            deleteBook(id);
        }


        return true;

    }




    public boolean saveIssueBook(IssuesOder issuesOder) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO issuebook VALUES(?,?,?)");
        preparedStatement.setString(1, issuesOder.getOrderId());
        preparedStatement.setString(2, issuesOder.getMemberId());
        preparedStatement.setDate(3, issuesOder.getIssueDate());

        preparedStatement.executeUpdate();
        return true;

    }

    public static String getNextOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  orderID FROM issuesoderdetails ORDER BY  orderID DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return generateNextOrderId(resultSet.getString(1));

        }
        return generateNextOrderId(null);
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] ids = currentOrderId.split("D00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "D00" + id;
        }
        return "D001";
    }

    public static boolean connection(String orderId, String memberId, String bookId, String bookName, Date issueDate) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO  issuesoderDetails VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, orderId);
        preparedStatement.setString(2, memberId);
        preparedStatement.setString(3, bookId);
        preparedStatement.setString(4, bookName);
        preparedStatement.setString(5, String.valueOf(issueDate));
        preparedStatement.execute();


        return false;
    }

 public static boolean deleteBook(String bookId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM availablebooks WHERE bookId=?");
        preparedStatement.setObject(1, bookId);
        return preparedStatement.executeUpdate() > 0;

    }



}