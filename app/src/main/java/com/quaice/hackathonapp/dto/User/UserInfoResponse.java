package com.quaice.hackathonapp.dto.User;

public class UserInfoResponse {
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Double numberOfDonatsCoins;
    private Long LastDonateTime;
    private Double dailyMultiplier;
    private Double overallMultiplier;

    public UserInfoResponse(String userId, String fullName, String email, String phoneNumber, Double numberOfDonatsCoins, Long lastDonateTime, Double dailyMultiplier, Double overallMultiplier) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.numberOfDonatsCoins = numberOfDonatsCoins;
        this.LastDonateTime = lastDonateTime;
        this.dailyMultiplier = dailyMultiplier;
        this.overallMultiplier = overallMultiplier;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        this.LastDonateTime = lastDonateTime;
    }

    public Double getDailyMultiplier() {
        return dailyMultiplier;
    }

    public void setDailyMultiplier(Double dailyMultiplier) {
        this.dailyMultiplier = dailyMultiplier;
    }

    public Double getOverallMultiplier() {
        return overallMultiplier;
    }

    public void setOverallMultiplier(Double overallMultiplier) {
        this.overallMultiplier = overallMultiplier;
    }
}
