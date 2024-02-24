package com.Ijse.gdse.Dto;

import java.sql.Date;

public class IssuesOrderDetails {
   private String orderId;
   private String memberId;
   private String bookId;

    public IssuesOrderDetails() {
    }

    private String bookName;

    public IssuesOrderDetails(String orderId, String memberId, String bookId, String bookName, Date issueDate) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.issueDate = issueDate;
    }

    private Date issueDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}


