package com.crm.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crm.spring.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    
    public List<Customer> getCustomers() {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create a query, sort by last name
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName",
        		Customer.class);

        //execute query and get result list
        List<Customer> customers = query.getResultList();

        //return the results
        return customers;

    }


	@Override
	public void saveCustomer(Customer theCust) {
		
		//get current hb session
		Session currSession = sessionFactory.getCurrentSession();
		//save the customer to db
		//saveOrUpdate, insert new or update existing
		currSession.saveOrUpdate(theCust);
	}


	@Override
	public Customer getCustomer(int theId) {
		//get curr hb session
		Session currSession = sessionFactory.getCurrentSession();
		//read from db using pk id
		Customer theCust = currSession.get(Customer.class, theId);
		return theCust;
	}


	@Override
	public void deleteCustomer(int theId) {
		//get the curr hb session
		Session currSession = sessionFactory.getCurrentSession();
		//delete obj with primary key id, match paras
		Query theQuery = currSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}


	@Override
	public List<Customer> searchCustomers(String theSearNm) {
		//get the curr hb session
		Session currSession = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = null;
		
		//only search by name if the search name is not empty
		if (theSearNm != null && theSearNm.trim().length() > 0) {
			
			theQuery = currSession.createQuery("from Customer "
					+ "where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			
			theQuery.setParameter("theName", "%" + theSearNm.toLowerCase() + "%");
		} else  {
			//get return all customers
			theQuery = currSession.createQuery("from Customer", Customer.class);
		}
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

}
