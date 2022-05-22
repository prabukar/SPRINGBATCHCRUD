package com.springboot.crud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.crud.exception.ResourceNotFoundException;
import com.springboot.crud.model.Customer;
import com.springboot.crud.repository.CustomerRepository;
import com.springboot.crud.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    
	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}


	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}


	@Override
	public Customer getCustomerById(long id) {
//		Optional<Customer> customer=customerRepository.findById(id);
//		if(customer.isPresent()) {
//			return customer.get();
//		}else {
//			throw new ResourceNotFoundException("Customer","customerid","customerid");
//		}
		return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","Id","id"));
	}


	@Override
	public Customer updateCustomer(Customer customer, long id) {
		
		Customer existingCustomer=customerRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("customer","Id",id));
		existingCustomer.settrxamount(customer.getrxamount());
		existingCustomer.setdescription(customer.getdescription());
		existingCustomer.settrxdate(customer.gettrxdate());
		existingCustomer.settrxtime(customer.gettrxtime());
		existingCustomer.setaccountnum(customer.getaccountnum());
		customerRepository.save(existingCustomer);
		return existingCustomer; 
	}


	@Override
	public void deleteCustomer(long id) {
		//check whether a customer exist in a DB  r not
		customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","Id",id));
		
		customerRepository.deleteById(id);
		
	}

}
