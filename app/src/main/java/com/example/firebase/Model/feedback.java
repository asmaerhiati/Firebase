package com.example.firebase.Model;

public class feedback {
    String text;
    String rate;

    public feedback() {
     }

    public feedback(String text, String rate) {
        this.text = text;
        this.rate = rate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getText() {
        return text;
    }

    public String getRate() {
        return rate;
    }
}
