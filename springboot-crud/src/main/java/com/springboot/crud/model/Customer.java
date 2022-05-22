package com.springboot.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerid;
	
	@Column(name="trxamount",nullable=false)
	private int trxamount;
	
	@Column(name="description",nullable=false)
	private String description;
	
	@Column(name="trxdate",nullable=false)
	private int trxdate;
	
	@Column(name="trxtime",nullable=false)
	private int trxtime;
	
	@Column(name="accountnum",nullable=false)
	private int accountnum;
	
	public static String[] fields() {
		return new String[] {"customerid","trxamoun","description","trxdate","trxtime","accountnum"};
	}

}
