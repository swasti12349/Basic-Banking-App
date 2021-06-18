package com.sro.basicbaningapp.model;
// Code written by SWASTI RANJAN OJHA
public class Transaction {
    String fromName;
    String toName;
    int amount;
    String status;

    public Transaction(String fromName, String toName, int amount, String status) {
        this.fromName = fromName;
        this.toName = toName;
        this.amount = amount;
        this.status = status;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
