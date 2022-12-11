package com.example.firebase.Model;

public class request {
    String rate,text;

    public request(String rate, String text) {
        this.rate = rate;
        this.text = text;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
