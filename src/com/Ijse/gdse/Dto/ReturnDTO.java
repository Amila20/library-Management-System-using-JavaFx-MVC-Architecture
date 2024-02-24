package com.Ijse.gdse.Dto;

import java.sql.Date;

public class ReturnDTO {

    private String returnID;

    public ReturnDTO() {
    }

    private String memberID;

    public ReturnDTO(String returnID, String memberID, Date returnDate) {
        this.returnID = returnID;
        this.memberID = memberID;
        this.returnDate = returnDate;
    }

    private Date returnDate;

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
