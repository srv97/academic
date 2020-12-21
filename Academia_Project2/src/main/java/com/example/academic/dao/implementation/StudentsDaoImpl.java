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
import java.util.Calendar;
import java.util.Date;
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
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            Student_Payment ob;
            Date time_frame = calendar.getTime();
            Query query = session.createQuery("from Students where id=:id");
       //     System.out.println("id in dao "+ student.getId());
            query.setParameter("id", student.getId());
            for (final Object fetch : query.list()) {
         //       System.out.println("inside");
                for (final Object payment : ((Students) fetch).getPayments()) {
                    ob = (Student_Payment) payment;
                    if(time_frame.before(ob.getPayment_date()))
                    student_payments.add(((Student_Payment) payment));
                }

          //      for(Student_Payment student_payment: student_payments){
           //         System.out.println(student_payment.getDescription()+"--inside dao-"+student_payment.getAmount());
           //     }
                return student_payments;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }

    @Override
    public List<table_out> getTotalPayment(Students student) {
        try (Session session = SessionUtil.getSession()) {
            List<table_out> pay = new ArrayList<>();
            Students obj;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -2);
         //   Date time_frame = calendar.getTime();
        //    System.out.println("id in dao "+ student.getId());

            Query query = session.createQuery("select b.description, sum(s.amount), b.deadline from Student_Payment s,Bills b where s.student.id=:student_id and b.id=s.bill.id group by b.id");
            query.setParameter("student_id", student.getId());
            List rows = query.list();
            for (Object o : rows) {
                Object[] row = (Object[]) o;
                table_out table_out = new table_out((String) row[0], ((Double) row[1]).floatValue(),((Date) row[2]));
                pay.add(table_out);
            }

           for (table_out table_out: pay) {
                System.out.println(table_out.getDescription()+"  totalpay   "+table_out.getAmount()+"     "+table_out.getDeadline());
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
          //  Date current = new Date();
            List<table_out> pay = new ArrayList<>();
       //     System.out.println("id in dao "+ student.getId());
            Query query = session.createQuery("select b.description, b.amount-sum(s.amount),b.deadline from Student_Payment s,Bills b where s.student.id=:student_id and b.id=s.bill.id group by b.id");
            query.setParameter("student_id", student.getId());
          //  query.setParameter("current",current);
            List rows = query.list();
            for (Object o : rows) {
                Object[] row = (Object[]) o;
                table_out table_out = new table_out((String) row[0], ((Double) row[1]).floatValue(),((Date) row[2]));
                if(table_out.getAmount()>0)
                pay.add(table_out);
            }

            query = session.createQuery("select description, amount,deadline from Bills where id not in (SELECT bill.id from Student_Payment  where student.id=:student_id)");
            query.setParameter("student_id", student.getId());
          //  query.setParameter("current",current);
            rows = query.list();
            for (Object o : rows) {
                Object[] row = (Object[]) o;
                table_out table_out = new table_out((String) row[0], ((float) row[1]),((Date) row[2]));
                if(table_out.getAmount()>0)
                pay.add(table_out);
            }

       //     for (table_out table_out: pay) {
        //        System.out.println(table_out.getDescription()+"  due  "+table_out.getAmount()+"     "+table_out.getDeadline());
        //    }

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

