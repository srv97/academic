package com.example.academic.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Bills")
public class Bills {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String description;
    private Date deadline;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private Date bill_date;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bill",cascade = {CascadeType.PERSIST})
    private List<Student_Payment> payments;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "bills")
    private List<Students> students;

    public Bills() {

    }

    public Bills(String description, Date deadline, float amount, Date bill_date, List<Student_Payment> payments, List<Students> students) {
        this.description = description;
        this.deadline = deadline;
        this.amount = amount;
        this.bill_date = bill_date;
        this.payments = payments;
        this.students = students;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


    public Date getBill_date() {
        return bill_date;
    }

    public void setBill_date(Date bill_date) {
        this.bill_date = bill_date;
    }

    public List<Student_Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Student_Payment> payments) {
        this.payments = payments;
    }
}
