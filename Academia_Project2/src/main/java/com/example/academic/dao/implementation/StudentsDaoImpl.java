package com.example.academic.dao.implementation;

import com.example.academic.bean.Student_Payment;
import com.example.academic.bean.Students;
import com.example.academic.dao.StudentsDao;
import com.example.academic.table_out;
import com.example.academic.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
            for( final Object fetch : query.list()) {
                return (Students) fetch;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Student_Payment> getPayment(Students student) {
        Session session = SessionUtil.getSession();
        try {
            Student_Payment obj;
            System.out.println("inside daoimpl get all payments");
            List<Student_Payment> pay_list = new ArrayList<>();
            Query query = session.createQuery("select s.id,p.amount,p.description,p.payment_date,p.bill.id from Students s INNER JOIN Student_Payment p ON s.id=p.student.id and s.id=:student_id");
            query.setParameter("student_id", student.getId());
            for( final Object fetch : query.list()) {
               // obj =
                System.out.println("inside");
                pay_list.add((Student_Payment) fetch);
            }
            return pay_list;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<table_out> gettotalPayment(Students student) {
        Session session = SessionUtil.getSession();
        try {
            List<table_out> pay = new ArrayList<>();
            Query query = session.createQuery("select b.description, sum(s.amount) from Student_Payment s,Bills b where s.student_id=:student_id and b.id=s.bill_id group by b.id");
            query.setParameter("student_id", student.getId());
            for (final Object fetch : query.list()) {
                pay.add((table_out) fetch);
            }
            return pay;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        } finally {
            session.close();
        }
    }
        @Override
        public List<table_out> getDueBill(Students student){
            Session session = SessionUtil.getSession();
            try {
                table_out obj;
                List<table_out> pay = new ArrayList<>();
                Query query = session.createQuery("select b.description, b.amount-sum(s.amount) from Student_Payment s,Bills b where s.student_id=:student_id and b.id=s.bill_id group by b.id");
                query.setParameter("student_id", student.getId());
                for (final Object fetch : query.list()) {
                    obj = (table_out) fetch;
                    if(obj.getAmount()>0)
                    pay.add((table_out) fetch);
                }
                return pay;
            } catch (HibernateException exception) {
                System.out.print(exception.getLocalizedMessage());
                return null;
            } finally {
                session.close();
            }
        }
}

