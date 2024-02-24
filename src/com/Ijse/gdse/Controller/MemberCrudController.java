package com.Ijse.gdse.Controller;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.MemberDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberCrudController {




    public static MemberDTO getMemberDetails(String id) throws SQLException, ClassNotFoundException {

         String sqlQuery = "SELECT * FROM   member WHERE memberId=?";
         PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sqlQuery);
         pstm.setString(1,id);
         ResultSet resultSet=pstm.executeQuery();
         if (resultSet.next()){
             return new MemberDTO(
                     resultSet.getString(1),
                     resultSet.getString(2),
                     resultSet.getString(3),
                     resultSet.getInt(4),
                     resultSet.getDate(5)
             );
         }
         return null;
    }




}
