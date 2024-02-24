package com.Ijse.gdse.Dto;

import java.sql.Date;

public class ReturnDetailsDTO {
    private String returnID;
    private String memberId;
    private String bookID;

    public ReturnDetailsDTO() {
    }

    private String bookName;

    public ReturnDetailsDTO(String returnID, String memberId, String bookID, String bookName, Date returnDate) {
        this.returnID = returnID;
        this.memberId = memberId;
        this.bookID = bookID;
        this.bookName = bookName;
        this.returnDate = returnDate;
    }

    private Date returnDate;

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
