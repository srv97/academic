package com.example.academic.dao.implementation;

import com.example.academic.bean.Bills;
import com.example.academic.dao.BillsDao;
import com.example.academic.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BillsDaoImpl implements BillsDao {
    @Override
    public void registerBill(Bills bill) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(bill);
            transaction.commit();
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }


    }
}
