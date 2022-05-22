package com.springboot.crud.service;

import java.util.List;

import com.springboot.crud.model.Customer;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(long id);
	Customer updateCustomer(Customer customer,long id);
	void deleteCustomer(long id);

}
