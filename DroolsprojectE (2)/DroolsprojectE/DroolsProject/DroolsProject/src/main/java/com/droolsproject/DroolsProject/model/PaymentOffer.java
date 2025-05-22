package com.droolsproject.DroolsProject.model;


public class PaymentOffer {
    private String channel;
    private double amount;
    private double discount;

    public PaymentOffer(String channel, double amount) {
        this.channel = channel;
        this.amount = amount;
        this.discount = 0;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
