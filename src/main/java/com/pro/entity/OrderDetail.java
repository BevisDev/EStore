package com.pro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderdetails")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "unitprice")
	Double unitPrice;
	String size;
	String color;
	Integer quantity;
	Double discount;

	@ManyToOne
	@JoinColumn(name= "orderid")
	Order order;
	
	@ManyToOne
	@JoinColumn(name ="productid")
	Product product;
	
	public double getPromotePrice() {
		return this.unitPrice * ( (100 - this.discount) / 100);
	}
}
