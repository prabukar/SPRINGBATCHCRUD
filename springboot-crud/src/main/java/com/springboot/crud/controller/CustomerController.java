package com.springboot.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.model.Customer;
import com.springboot.crud.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	//Create RESTAPI
	@PostMapping()
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
	   return new ResponseEntity<Customer>(customerService.saveCustomer(customer),HttpStatus.CREATED);	
	}
	//build get all customer REST API
	@GetMapping
	public List<Customer> getAllCustomer(){
		return customerService.getAllCustomers();
				
	}
	
	//build get customer by id
	@GetMapping("{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id")long customerid){
		return new ResponseEntity<Customer>(customerService.getCustomerById(customerid),HttpStatus.OK);
	}
	
	//build update customer REST API
	@PutMapping("{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id")long id,@RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.updateCustomer(customer, id),HttpStatus.OK);
	}
	
	//build delete REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id")long id){
		
		customerService.deleteCustomer(id);
		
		return new ResponseEntity<String>("Employee deleted sucessfully!.",HttpStatus.OK);
		
	}
	
}
