package com.quaice.hackathonapp.dto.Fundraising;

public class FundraisingResponse {
    private String fundraisingId;
    private String title;
    private String description;
    private String fundraisingUrl;
    private String fundraisingCompany;
    private Double goal;
    private FundraisingType fundraisingType;

    public FundraisingResponse(String fundraisingId, String title, String description, String fundraisingUrl, String fundraisingCompany, Double goal, FundraisingType fundraisingType) {
        this.fundraisingId = fundraisingId;
        this.title = title;
        this.description = description;
        this.fundraisingUrl = fundraisingUrl;
        this.fundraisingCompany = fundraisingCompany;
        this.goal = goal;
        this.fundraisingType = fundraisingType;
    }

    public String getFundraisingId() {
        return fundraisingId;
    }

    public void setFundraisingId(String fundraisingId) {
        this.fundraisingId = fundraisingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFundraisingUrl() {
        return fundraisingUrl;
    }

    public void setFundraisingUrl(String fundraisingUrl) {
        this.fundraisingUrl = fundraisingUrl;
    }

    public String getFundraisingCompany() {
        return fundraisingCompany;
    }

    public void setFundraisingCompany(String fundraisingCompany) {
        this.fundraisingCompany = fundraisingCompany;
    }

    public Double getGoal() {
        return goal;
    }

    public void setGoal(Double goal) {
        this.goal = goal;
    }

    public FundraisingType getFundraisingType() {
        return fundraisingType;
    }

    public void setFundraisingType(FundraisingType fundraisingType) {
        this.fundraisingType = fundraisingType;
    }

    public enum FundraisingType {
        All("All"),
        Medical("Medical"),
        Clothes("Clothes"),
        Equipment("Equipment"),
        Army("Army");

        private final String type;

        FundraisingType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }
}
