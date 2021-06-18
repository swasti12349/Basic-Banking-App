package com.sro.basicbaningapp.model;
/* Code written by SWASTI RANJAN OJHA */
public class user {
    String name, acno, ifsc, email, phno;
    int bal;

    public user(String name, String acno, String ifsc, String email, String phno, int bal) {
        this.name = name;
        this.acno = acno;
        this.ifsc = ifsc;
        this.email = email;
        this.phno = phno;
        this.bal = bal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }
}
