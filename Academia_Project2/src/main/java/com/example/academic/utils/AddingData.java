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
        calendar.add(Calendar.MONTH, 2);
        Date late_pay = calendar.getTime();
        calendar.add(Calendar.MONTH, -4);
        Date early_pay = calendar.getTime();
        Bills bill1 = new Bills("Admission Fee", deadline, 2000.00F, current, null, null);
        Bills bill2 = new Bills("Tuition Fee", deadline, 50000.00F, current, null, null);
        Bills bill3 = new Bills("Library Fee", deadline, 10000.00F, current, null, null);
        Bills bill4 = new Bills("Medical Fee", deadline, 5000.00F, current, null, null);
        Bills bill5 = new Bills("Security Fee", current, 1000.00F, current, null, null);
        BillsDao billsDao = new BillsDaoImpl();
        billsDao.registerBill(bill1);
        billsDao.registerBill(bill2);
        billsDao.registerBill(bill3);
        billsDao.registerBill(bill4);
        billsDao.registerBill(bill5);

        List<Bills> bills = new ArrayList<>();
        bills.add(bill1);
        bills.add(bill2);
        bills.add(bill3);
        bills.add(bill4);
        bills.add(bill5);

        Students student1 = new Students("Rachit", "Yagnik", "rachit@iiitb.org", "MT2020001", null, bills);
        StudentsDao studentsDao = new StudentsDaoImpl();
        studentsDao.registerStudent(student1);

        Students student2 = new Students("Saurav", "Thapliyal", "saurav@iiitb.org", "MT2020002", null, bills);
        studentsDao = new StudentsDaoImpl();
        studentsDao.registerStudent(student2);

        Students student3 = new Students("Shubham", "Aggarwal", "shubham@iiitb.org", "MT2020003", null, bills);
        studentsDao = new StudentsDaoImpl();
        studentsDao.registerStudent(student3);

        Students student4 = new Students("Garib", "Das", "garib@iiitb.org", "MT2020000", null, bills);
        studentsDao = new StudentsDaoImpl();
        studentsDao.registerStudent(student4);


        Student_Payment student_payment0 = new Student_Payment(student1, "Paying Security Fee", 1000.00F,early_pay, bill5);
        Student_Payment student_payment1 = new Student_Payment(student1, "Paying Tuition Fee 1", 35000.00F,current, bill2);
        Student_Payment student_payment2 = new Student_Payment(student1, "Paying Tuition Fee 2", 5000.00F, current, bill2);
        Student_Payment student_payment3 = new Student_Payment(student1, "Paying Medical Fee", 2000.00F, current, bill4);
        Student_PaymentDao student_paymentDao = new Student_PaymentDaoImpl();
        student_paymentDao.addStudentPayment(student_payment0);
        student_paymentDao.addStudentPayment(student_payment1);
        student_paymentDao.addStudentPayment(student_payment2);
        student_paymentDao.addStudentPayment(student_payment3);

        student_payment0 = new Student_Payment(student2, "Paying Security Fee", 1000.00F,current, bill5);
        student_payment1 = new Student_Payment(student2, "Paying Tuition Fee 1", 25000.00F,current, bill2);
        student_payment2 = new Student_Payment(student2, "Paying Library Fee", 8000.00F, current, bill3);
        student_paymentDao = new Student_PaymentDaoImpl();
        student_paymentDao.addStudentPayment(student_payment0);
        student_paymentDao.addStudentPayment(student_payment1);
        student_paymentDao.addStudentPayment(student_payment2);

        student_payment0 = new Student_Payment(student3, "Paying Security Fee", 1000.00F,current, bill5);
        student_payment1 = new Student_Payment(student3, "Paying Tuition Fee 1", 45000.00F,current, bill2);
        student_payment2 = new Student_Payment(student3, "Paying Library Fee", 7000.00F, late_pay, bill3);
        student_paymentDao = new Student_PaymentDaoImpl();
        student_paymentDao.addStudentPayment(student_payment0);
        student_paymentDao.addStudentPayment(student_payment1);
        student_paymentDao.addStudentPayment(student_payment2);


        System.out.println("All payments by student 1--------------------------");
        List<Student_Payment> student_payments = studentsDao.getPayment(student1);
        for(Student_Payment student_payment: student_payments){
            System.out.println("All payments  "+student_payment.getDescription()+"---"+student_payment.getAmount());
        }
        List<table_out> table_outs = studentsDao.getTotalPayment(student1);
        for (table_out table_out: table_outs) {
            System.out.println("Total payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }

        table_outs = studentsDao.getDueBill(student1);
        for (table_out table_out: table_outs) {
            System.out.println("Due payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }


        System.out.println("\n\nAll payments by student 2--------------------------");
        student_payments = studentsDao.getPayment(student2);
        for(Student_Payment student_payment: student_payments){
            System.out.println("All payments  "+student_payment.getDescription()+"---"+student_payment.getAmount());
        }
       table_outs = studentsDao.getTotalPayment(student2);
        for (table_out table_out: table_outs) {
            System.out.println("Total payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }

        table_outs = studentsDao.getDueBill(student2);
        for (table_out table_out: table_outs) {
            System.out.println("Due payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }

        System.out.println("\n\nAll payments by student 3--------------------------");
        student_payments = studentsDao.getPayment(student3);
        for(Student_Payment student_payment: student_payments){
            System.out.println("All payments  "+student_payment.getDescription()+"---"+student_payment.getAmount());
        }
        table_outs = studentsDao.getTotalPayment(student3);
        for (table_out table_out: table_outs) {
            System.out.println("Total payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }

        table_outs = studentsDao.getDueBill(student3);
        for (table_out table_out: table_outs) {
            System.out.println("Due payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }

        System.out.println("\n\nAll payments by student 4--------------------------");
        student_payments = studentsDao.getPayment(student4);
        for(Student_Payment student_payment: student_payments){
            System.out.println("All payments  "+student_payment.getDescription()+"---"+student_payment.getAmount());
        }
        table_outs = studentsDao.getTotalPayment(student4);
        for (table_out table_out: table_outs) {
            System.out.println("Total payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }

        table_outs = studentsDao.getDueBill(student4);
        for (table_out table_out: table_outs) {
            System.out.println("Due payments    "+table_out.getDescription()+"     "+table_out.getAmount()+"       "+table_out.getDeadline());
        }
    }
}
