package com.ascendcorp.exam.model;

import java.io.Serializable;

public class InquiryServiceResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private java.lang.String tranID;

    private String namespace;

    private java.lang.String reasonCode;

    private java.lang.String reasonDesc;

    private java.lang.String balance;

    private java.lang.String refNo1;

    private java.lang.String refNo2;

    private java.lang.String amount;

    private String accountName = null;

    public java.lang.String getTranID() {
        return tranID;
    }

    public void setTranID(java.lang.String tranID) {
        this.tranID = tranID;
    }

    public java.lang.String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(java.lang.String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public java.lang.String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(java.lang.String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public java.lang.String getBalance() {
        return balance;
    }

    public void setBalance(java.lang.String balance) {
        this.balance = balance;
    }

    public java.lang.String getRefNo1() {
        return refNo1;
    }

    public void setRefNo1(java.lang.String refNo1) {
        this.refNo1 = refNo1;
    }

    public java.lang.String getRefNo2() {
        return refNo2;
    }

    public void setRefNo2(java.lang.String refNo2) {
        this.refNo2 = refNo2;
    }

    public java.lang.String getAmount() {
        return amount;
    }

    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }



    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "InquiryServiceResultDTO [tranID=" + tranID + ",namespace = "+namespace + ", reasonCode="
                + reasonCode + ", reasonDesc=" + reasonDesc + ", balance="
                + balance + ", ref_no1=" + refNo1 + ", ref_no2=" + refNo2
                + ", amount=" + amount + " ,account_name="+accountName+"  ]";
    }



}
