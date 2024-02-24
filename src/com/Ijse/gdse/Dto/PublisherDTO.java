package com.Ijse.gdse.Dto;

public class PublisherDTO {
    @Override
    public String toString() {
        return "PublisherDTO{" +
                "publisherId='" + publisherId + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", publisherAddress='" + publisherAddress + '\'' +
                '}';
    }

    String publisherId;

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    String publisherName;

    public PublisherDTO(String publisherId, String publisherName, String publisherAddress) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.publisherAddress = publisherAddress;
    }

    String publisherAddress;

    public PublisherDTO() {
    }
}
