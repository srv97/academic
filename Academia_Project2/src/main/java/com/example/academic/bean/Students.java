package com.example.academic.bean;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Students")
public class Students {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String firstname;
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String roll_no;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "student",cascade = {CascadeType.PERSIST})
    private List<Student_Payment> payments;

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_bill",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "bill_id")}
    )
    private List<Bills> bills;

    public Students() {

    }

    public Students(String firstname, String lastname, String email, String roll_no, List<Student_Payment> payments, List<Bills> bills) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.roll_no = roll_no;
        this.payments = payments;
        this.bills = bills;
    }

    @JsonbTransient
    public List<Bills> getBills() {
        return bills;
    }

    public void setBills(List<Bills> bills) {
        this.bills = bills;
    }

    @JsonbTransient
    public List<Student_Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Student_Payment> payments) {
        this.payments = payments;
    }


    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
