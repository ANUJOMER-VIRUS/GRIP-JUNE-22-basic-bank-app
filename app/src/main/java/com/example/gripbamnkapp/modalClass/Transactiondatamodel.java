package com.example.gripbamnkapp.modalClass;

public class Transactiondatamodel {
    public Transactiondatamodel(String fromuser, String touser, int amounttransfered, int status) {
        this.fromuser = fromuser;
        Touser = touser;
        this.amounttransfered = amounttransfered;
        this.status = status;
    }

    private String fromuser, Touser;
    private int amounttransfered, status;

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return Touser;
    }

    public void setTouser(String touser) {
        Touser = touser;
    }

    public int getAmounttransfered() {
        return amounttransfered;
    }

    public void setAmounttransfered(int amounttransfered) {
        this.amounttransfered = amounttransfered;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}




