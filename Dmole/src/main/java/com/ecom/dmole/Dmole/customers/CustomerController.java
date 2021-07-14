package com.ecom.dmole.Dmole.customers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	@Autowired
CustomerRepository customerRepository;
@PostMapping("/customer/signup")
public Status registerCustomer(@Valid @RequestBody Customer newCustomer) {
	List<Customer> customers=customerRepository.findAll();
	System.out.println(newCustomer.toString());
	for(Customer customer:customers) {
		if(customer.equals(newCustomer)) {
			System.out.println("Customer "+newCustomer.getContactFirstName()+":"+newCustomer.getContactLastName()+"already exists");
			return Status.CUSTOMER_ALREADY_EXISTS;
		}
	}
	customerRepository.save(newCustomer);
    return Status.SUCCESS;
}
@PostMapping("customer/login")
public Status loginCustomer(@Valid @RequestBody Customer newCustomer) {
	List<Customer> customers=customerRepository.findAll();
	for(Customer customer:customers) {
		if(customer.getPassword().equals(newCustomer.getPassword())) {
			System.out.println("Customer "+newCustomer.getContactFirstName()+":"+newCustomer.getContactLastName()+"password  matched");
			return Status.SUCCESS;
		}
	}
	System.out.println("Customer "+newCustomer.getContactFirstName()+":"+newCustomer.getContactLastName()+"password  does notmatched");
	return Status.FAILURE;
}
@GetMapping("")
public String index() {
    return "index";
    
}
}
