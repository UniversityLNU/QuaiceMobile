package com.quaice.hackathonapp.dto.Fundraising;

import java.util.List;

public class AllFundraisingResponse {
    private List<FundraisingResponse> fundraisingList;

    public AllFundraisingResponse(List<FundraisingResponse> fundraisingResponses) {
        this.fundraisingList = fundraisingResponses;
    }

    public List<FundraisingResponse> getFundraisingList() {
        return fundraisingList;
    }
    public void setFundraisingList(List<FundraisingResponse> fundraisingList) {
        this.fundraisingList = fundraisingList;
    }
}
