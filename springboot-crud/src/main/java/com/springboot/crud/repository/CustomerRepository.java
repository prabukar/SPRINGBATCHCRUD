package com.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.crud.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
