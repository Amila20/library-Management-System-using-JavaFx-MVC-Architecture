package com.Ijse.gdse.Dto;

import java.sql.Date;

public class MemberDTO {

    String memberId;
    String memberName;
    String memberAddress;



    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName){
        this.memberName = memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public Date getMemberRegistorDate() {
        return memberRegistorDate;
    }

    public void setMemberRegistorDate(Date memberRegistorDate) {
        this.memberRegistorDate = memberRegistorDate;
    }

    public int getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(int memberContact) {
        this.memberContact = memberContact;
    }

    Date memberRegistorDate;

    @Override
    public String
    toString() {
        return "MemberDTO{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", memberContact=" + memberContact +
                ", memberRegistorDate=" + memberRegistorDate +
                '}';
    }

    int memberContact;

    public MemberDTO() {
    }



    public MemberDTO(String memberId, String memberName, String memberAddress, int memberContact, Date memberRegistorDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberContact = memberContact;
        this.memberRegistorDate = memberRegistorDate;
    }




}
