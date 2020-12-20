package com.example.academic.dao.implementation;

import com.example.academic.bean.Student_Payment;
import com.example.academic.dao.Student_PaymentDao;
import com.example.academic.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Student_PaymentDaoImpl implements Student_PaymentDao {


    @Override
    public void addStudentPayment(Student_Payment student_payment) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student_payment);
            transaction.commit();
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }

    }
}
