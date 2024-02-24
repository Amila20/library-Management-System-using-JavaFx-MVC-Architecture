package com.Ijse.gdse.Dto;

public class BookDTO {
    String bookId;
    String bookName;


    public String getBookId() {
        return bookId;
    }

    public void setBookId() {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookTittle() {
        return bookTittle;
    }

    public void setBookTittle(String bookTittle) {
        this.bookTittle = bookTittle;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    String bookTittle;

    public BookDTO() {
    }

    public BookDTO(String bookId, String bookName, String bookTittle, double bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookTittle = bookTittle;
        this.bookPrice = bookPrice;
    }

    Double bookPrice;
    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookTittle='" + bookTittle + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }


}
