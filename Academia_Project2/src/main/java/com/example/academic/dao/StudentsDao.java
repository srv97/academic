package com.example.academic.dao;

import com.example.academic.bean.Student_Payment;
import com.example.academic.bean.Students;
import com.example.academic.table_out;

import java.util.List;

public interface StudentsDao {

   Students emailVerify(Students student);
   List<Student_Payment> getPayment(Students student);
   List<table_out> getTotalPayment(Students student);
   List<table_out> getDueBill(Students student);

   void registerStudent(Students student);
}
