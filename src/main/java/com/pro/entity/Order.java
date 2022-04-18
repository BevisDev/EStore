package com.pro.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name = "paymentid")
	String paymentId;
	@Column(name = "paymentamount")
	Double paymentAmount;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "orderdate")
	Date orderDate = new Date();
	String recipient;
	String address;
	String phone;
	Double total;
	String notes;
	
	@ManyToOne
	@JoinColumn(name = "username")
	Account account;

	@ManyToOne
	@JoinColumn(name = "statusid")
	Status status;

	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
}
