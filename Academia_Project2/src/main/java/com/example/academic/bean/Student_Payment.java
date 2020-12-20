package com.example.academic.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Student_Payment")
public class Student_Payment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="student_id")
    private Students student;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="bill_id")
    private Bills bill;

    private String description;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private Date payment_date;



    public Student_Payment() {

    }

    public Student_Payment(Students student, String description, float amount, Date payment_date, Bills bill) {
        this.student = student;
        this.description = description;
        this.amount = amount;
        this.payment_date = payment_date;
        this.bill = bill;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Bills getBill() {
        return bill;
    }

    public void setBill(Bills bill) {
        this.bill = bill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }



}
