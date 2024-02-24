package com.Ijse.gdse.Dto;

import java.sql.Date;

public class IssuesOder {
    private String orderId;

    public IssuesOder() {
    }

    private String memberId;

    public IssuesOder(String orderId, String memberId, Date issueDate) {
        this.orderId = orderId;
        this.memberId = memberId;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
