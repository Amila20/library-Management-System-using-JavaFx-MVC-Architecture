package com.Ijse.gdse.Model;

import com.Ijse.gdse.DB.DBConnection;
import com.Ijse.gdse.Dto.MemberDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberModel {
    public static boolean Save(MemberDTO memberDTO) throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO member VALUES (?,?,?,?,?)");
        preparedStatement.setObject(1,memberDTO.getMemberId());
        preparedStatement.setObject(2,memberDTO.getMemberName());
        preparedStatement.setObject(3,memberDTO.getMemberAddress());
        preparedStatement.setObject(4,memberDTO.getMemberContact());
        preparedStatement.setObject(5,memberDTO.getMemberRegistorDate());

        return preparedStatement.executeUpdate()>0;

    }
    public static boolean updateMember(String memberId,String memberName,MemberDTO memberDTO) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("UPDATE member SET memberId=?,memberName=?,memberAddress=?,memberContact=?,memberRegistorDate=? WHERE memberId=? AND memberName=?");
        preparedStatement.setObject(1,memberDTO.getMemberId());
        preparedStatement.setObject(2,memberDTO.getMemberName());
        preparedStatement.setObject(3,memberDTO.getMemberAddress());
        preparedStatement.setObject(4,memberDTO.getMemberContact());
        preparedStatement.setObject(5,memberDTO.getMemberRegistorDate());
        preparedStatement.setObject(6,memberId);
        preparedStatement.setObject(7,memberName);

        return preparedStatement.executeUpdate()>0;
    }
    public static boolean deleteMember(String memberId,String memberName) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM member WHERE memberId=? AND memberName=?");
        preparedStatement.setObject(1,memberId);
        preparedStatement.setObject(2,memberName);

        return preparedStatement.executeUpdate()>0;
    }

    public MemberDTO searchMember(String memberId) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM member WHERE memberId=?");
        preparedStatement.setObject(1,memberId);
        ResultSet resultSet= preparedStatement.executeQuery();

        if (resultSet.next()){
            String memberName= resultSet.getString(2);
            String memberAddress=resultSet.getString(3);
            int memberContact=resultSet.getInt(4);
            Date memberRegisterDate=resultSet.getDate(5);
            return new MemberDTO(memberId,memberName,memberAddress,memberContact,memberRegisterDate);
        }
        return null;
    }




    public List<MemberDTO> GetMembersDetails() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT  * FROM member");
        ResultSet resultSet=preparedStatement.executeQuery();
        List<MemberDTO> list=new ArrayList<>();

        while (resultSet.next()){
            String memberId=resultSet.getString(1);
            String memberName=resultSet.getString(2);
            String memberAddress=resultSet.getString(3);
            int memberContact=resultSet.getInt(4);
            Date registerDate=resultSet.getDate(5);
            list.add(new MemberDTO(memberId,memberName,memberAddress,memberContact,registerDate));
        }
        return list;


    }
    public static ArrayList<String> getMembersId() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT  memberId FROM member");
        ResultSet resultSet= preparedStatement.executeQuery();
        ArrayList<String> ids=new ArrayList<>();

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;

    }

    public static ArrayList<String> getIssuesMembersId() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT  memberID FROM issuesoderdetails");
        ResultSet resultSet= preparedStatement.executeQuery();
        ArrayList<String> ids=new ArrayList<>();

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;

    }



}
