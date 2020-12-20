package com.example.academic;

import com.example.academic.bean.Bills;

public class table_out {
    private String description;
    private float amount;

    public table_out() {
    }

    public table_out(String description, float amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
