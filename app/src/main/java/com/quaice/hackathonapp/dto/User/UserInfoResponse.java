package com.quaice.hackathonapp.dto.User;

public class UserInfoResponse {
    private String userId;
    private String FullName;
    private String email;
    private String phoneNumber;
    private Double numberOfDonatsCoins;
    private Long LastDonateTime;
    private Double DailyMultiplier;
    private Double OverallMultiplier;

    public UserInfoResponse(String userId, String fullName, String email, String phoneNumber, Double numberOfDonatsCoins, Long lastDonateTime, Double dailyMultiplier, Double overallMultiplier) {
        this.userId = userId;
        FullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.numberOfDonatsCoins = numberOfDonatsCoins;
        LastDonateTime = lastDonateTime;
        DailyMultiplier = dailyMultiplier;
        OverallMultiplier = overallMultiplier;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getNumberOfDonatsCoins() {
        return numberOfDonatsCoins;
    }

    public void setNumberOfDonatsCoins(Double numberOfDonatsCoins) {
        this.numberOfDonatsCoins = numberOfDonatsCoins;
    }

    public Long getLastDonateTime() {
        return LastDonateTime;
    }

    public void setLastDonateTime(Long lastDonateTime) {
        LastDonateTime = lastDonateTime;
    }

    public Double getDailyMultiplier() {
        return DailyMultiplier;
    }

    public void setDailyMultiplier(Double dailyMultiplier) {
        DailyMultiplier = dailyMultiplier;
    }

    public Double getOverallMultiplier() {
        return OverallMultiplier;
    }

    public void setOverallMultiplier(Double overallMultiplier) {
        OverallMultiplier = overallMultiplier;
    }
}
