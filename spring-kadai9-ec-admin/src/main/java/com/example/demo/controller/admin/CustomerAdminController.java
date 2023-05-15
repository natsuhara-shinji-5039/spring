package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class CustomerAdminController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@GetMapping("/admin/customers")
	public String index(Model model) {
		List<Customer> customers = customerRepository.findAll();
		model.addAttribute("customers", customers);
		return "admin/customers";
	}
	
	@GetMapping("/admin/customers/{id}/orders")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {
		Customer customer = customerRepository.findById(id).get();
		List<Order> orders = orderRepository.findByCustomerId(customer.getId());
		
		List<Integer> orderIdList = new ArrayList<>();
		for(Order order: orders) {
			orderIdList.add(order.getId());
//			List<OrderDetail> orderDetail = orderDetailRepository.findByOrderId(order.getId());
//			orderDetails.addAll(orderDetail);
		}
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrderIdInOrderByOrderId(orderIdList);
		
		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("customer", customer);
		model.addAttribute("orders", orders);
		return "/admin/showCustomer";
	}
}
