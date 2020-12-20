package com.example.academic.utils;

import com.example.academic.bean.Bills;
import com.example.academic.bean.Student_Payment;
import com.example.academic.bean.Students;
import com.example.academic.dao.BillsDao;
import com.example.academic.dao.Student_PaymentDao;
import com.example.academic.dao.StudentsDao;
import com.example.academic.dao.implementation.BillsDaoImpl;
import com.example.academic.dao.implementation.Student_PaymentDaoImpl;
import com.example.academic.dao.implementation.StudentsDaoImpl;
import com.example.academic.table_out;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddingData {

    public static void main(String[] args) {
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date deadline = calendar.getTime();
        Bills bill1 = new Bills("Tuition Fee", deadline, 50000.00F, current, null, null);
        Bills bill2 = new Bills("Library Fee", deadline, 10000.00F, current, null, null);
        Bills bill3 = new Bills("Medical Fee", deadline, 5000.00F, current, null, null);
        BillsDao billsDao = new BillsDaoImpl();
        billsDao.registerBill(bill1);
        billsDao.registerBill(bill2);
        billsDao.registerBill(bill3);

        List<Bills> bills = new ArrayList<>();
        bills.add(bill1);
        bills.add(bill2);
        bills.add(bill3);

        Students student1 = new Students("clark", "kent", "ck@iiitb.org", "MT2020001", null, bills);
        StudentsDao studentsDao = new StudentsDaoImpl();
        studentsDao.registerStudent(student1);

        Student_Payment student_payment1 = new Student_Payment(student1, "Paying tuition fee 1", 35000.00F, current, bill1);
        Student_Payment student_payment2 = new Student_Payment(student1, "Paying tuition fee 2", 5000.00F, current, bill1);
        Student_Payment student_payment3 = new Student_Payment(student1, "Paying medical fee", 2000.00F, current, bill3);
        Student_PaymentDao student_paymentDao = new Student_PaymentDaoImpl();
        student_paymentDao.addStudentPayment(student_payment1);
        student_paymentDao.addStudentPayment(student_payment2);
        student_paymentDao.addStudentPayment(student_payment3);
        List<Student_Payment> student_payments = studentsDao.getPayment(student1);
        for(Student_Payment student_payment: student_payments){
            System.out.println(student_payment.getDescription()+"---"+student_payment.getAmount());
        }
        List<table_out> table_outs = studentsDao.getTotalPayment(student1);
        for (table_out table_out: table_outs) {
            System.out.println(table_out.getDescription()+"     "+table_out.getAmount());
        }

        table_outs = studentsDao.getDueBill(student1);
        for (table_out table_out: table_outs) {
            System.out.println(table_out.getDescription()+"     "+table_out.getAmount());
        }


    }
}
