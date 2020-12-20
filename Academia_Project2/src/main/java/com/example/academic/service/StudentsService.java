package com.example.academic.service;

import com.example.academic.bean.Student_Payment;
import com.example.academic.bean.Students;
import com.example.academic.dao.StudentsDao;
import com.example.academic.dao.implementation.StudentsDaoImpl;
import com.example.academic.table_out;

import java.util.List;

public class StudentsService {

    StudentsDao studentDao = new StudentsDaoImpl();
    public Students verifyEmail(Students student){
        return studentDao.emailVerify(student);
    }
    public List<Student_Payment> getStuPayment(Students student) { return studentDao.getPayment(student);}
    public List<table_out> sumPayment(Students student) { return studentDao.getTotalPayment(student);}
    public List<table_out> duePayment(Students student) { return studentDao.getDueBill(student);}

}
