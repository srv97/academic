package com.example.academic.dao.implementation;

import com.example.academic.bean.Student_Payment;
import com.example.academic.bean.Students;
import com.example.academic.dao.StudentsDao;
import com.example.academic.table_out;
import com.example.academic.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class StudentsDaoImpl implements StudentsDao {

    @Override
    public Students emailVerify(Students student) {
        Session session = SessionUtil.getSession();
        try {
            Query query = session.createQuery("from Students where email=:email");
            query.setParameter("email", student.getEmail());
            for (final Object fetch : query.list()) {
                return (Students) fetch;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Student_Payment> getPayment(Students student) {
        List<Student_Payment> student_payments = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            Query query = session.createQuery("from Students where id=:id");
            query.setParameter("id", student.getId());
            for (final Object fetch : query.list()) {
                System.out.println("inside");
                for (final Object payment : ((Students) fetch).getPayments()) {
                    student_payments.add(((Student_Payment) payment));
                }
                return student_payments;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return student_payments;
        }
        return student_payments;
    }

    @Override
    public List<table_out> getTotalPayment(Students student) {
        try (Session session = SessionUtil.getSession()) {
            List<table_out> pay = new ArrayList<>();
            Query query = session.createQuery("select b.description, sum(s.amount) from Student_Payment s,Bills b where s.student.id=:student_id and b.id=s.bill.id group by b.id");
            query.setParameter("student_id", student.getId());
            List rows = query.list();
            for (Object o : rows) {
                Object[] row = (Object[]) o;
                table_out table_out = new table_out((String) row[0], ((Double) row[1]).floatValue());
                pay.add(table_out);
            }
            return pay;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<table_out> getDueBill(Students student) {
        try (Session session = SessionUtil.getSession()) {
            List<table_out> pay = new ArrayList<>();
            Query query = session.createQuery("select b.description, b.amount-sum(s.amount) from Student_Payment s,Bills b where s.student.id=:student_id and b.id=s.bill.id group by b.id");
            query.setParameter("student_id", student.getId());
            List rows = query.list();
            for (Object o : rows) {
                Object[] row = (Object[]) o;
                table_out table_out = new table_out((String) row[0], ((Double) row[1]).floatValue());
                pay.add(table_out);
            }
            return pay;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void registerStudent(Students student) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
    }
}

