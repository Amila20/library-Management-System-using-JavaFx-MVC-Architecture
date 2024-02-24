package com.Ijse.gdse.View.TM;

import javafx.scene.control.Button;

import java.sql.Date;

public class CartTm {
    String BookId;
    String BookName;
    String BookTittle;

    public CartTm() {
    }

    Date IssuesDate;

    public CartTm(String bookId, String bookName, String bookTittle, Date issuesDate, Button btn) {
        BookId = bookId;
        BookName = bookName;
        BookTittle = bookTittle;
        IssuesDate = issuesDate;
        this.btn = btn;
    }

    Button btn;


    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookTittle() {
        return BookTittle;
    }

    public void setBookTittle(String bookTittle) {
        BookTittle = bookTittle;
    }

    public Date getIssuesDate() {
        return IssuesDate;
    }

    public void setIssuesDate(Date issuesDate) {
        IssuesDate = issuesDate;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
