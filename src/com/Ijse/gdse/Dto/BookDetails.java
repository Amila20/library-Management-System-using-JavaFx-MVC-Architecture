package com.Ijse.gdse.Dto;

public class BookDetails {

    public BookDetails(String bookId, String bookName, String bookAddress) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAddress = bookAddress;
    }

    private String bookId ;

    @Override
    public String toString() {
        return "BookDetails{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAddress='" + bookAddress + '\'' +
                '}';
    }

    private String bookName;

    public BookDetails() {
    }

    private String bookAddress;

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

    public String getBookAddress() {
        return bookAddress;
    }

    public void setBookAddress(String bookAddress) {
        this.bookAddress = bookAddress;
    }
}
