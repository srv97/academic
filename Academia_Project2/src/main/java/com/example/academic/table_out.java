package com.example.academic;

import com.example.academic.bean.Bills;

import java.util.Date;

public class table_out {
    private String description;
    private float amount;
    private Date deadline;

    public table_out(String description, float amount, Date deadline) {
        this.description = description;
        this.amount = amount;
        this.deadline = deadline;
    }

    public table_out() {
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
