package com.Ijse.gdse.Model;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.PublisherDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherModel {
    public static boolean Save(PublisherDTO publisherDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publisher VALUES (?,?,?)");
        preparedStatement.setObject(1, publisherDTO.getPublisherId());
        preparedStatement.setObject(2, publisherDTO.getPublisherName());
        preparedStatement.setObject(3, publisherDTO.getPublisherAddress());
        return preparedStatement.executeUpdate() > 0;

    }
    public List<PublisherDTO> getPublisherDetails() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT  * FROM  publisher");
        ResultSet resultSet= preparedStatement.executeQuery();
        List<PublisherDTO> list=new ArrayList<>();

        while (resultSet.next()){
            String publisherId=resultSet.getString(1);
            String publisherName=resultSet.getString(2);
            String publishrAddress=resultSet.getString(3);

            list.add(new PublisherDTO(publisherId,publisherName,publishrAddress));
        }
        return list;

    }
    public static boolean updatePublisher(String id,PublisherDTO publisherDTO) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("UPDATE publisher SET publisherName=?,publisherAddress=? WHERE publisherId=?");

        preparedStatement.setObject(1,publisherDTO.getPublisherName());
        preparedStatement.setObject(2,publisherDTO.getPublisherAddress());
        preparedStatement.setObject(3,id);
        return preparedStatement.executeUpdate()>0;
    }

    public static boolean deletePublisher(String id) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM publisher WHERE publisherId=?");
        preparedStatement.setObject(1,id);
        return preparedStatement.executeUpdate()>0;
    }
    public PublisherDTO searchPublisher(String id) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT  * FROM publisher WHERE publisherId=?");
        preparedStatement.setObject(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next()){
            String pubName=resultSet.getString(2);
            String pubAddress=resultSet.getString(3);
            return new PublisherDTO(id,pubName,pubAddress);
        }
        return null;
    }


}

