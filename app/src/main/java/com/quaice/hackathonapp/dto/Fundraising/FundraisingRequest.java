package com.quaice.hackathonapp.dto.Fundraising;

public class FundraisingRequest {
    private String fundraisingId;

    public FundraisingRequest(String fundraisingId) {
        this.fundraisingId = fundraisingId;
    }
    public String getFundraisingId() {
        return fundraisingId;
    }
    public void setFundraisingId(String fundraisingId) {
        this.fundraisingId = fundraisingId;
    }
}
