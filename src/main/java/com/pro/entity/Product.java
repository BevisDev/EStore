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
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	@Column(name = "unitprice")
	Double unitPrice;
	Double discount;
	String size;
	String color;
	Integer quantity;
	String description;
	@Column(name = "imageone")
	String imageOne = "product.png";
	
	@Column(name = "imagetwo")
	String imageTwo = "product.png";
	
	@Column(name = "imagethree")
	String imageThree = "product.png";
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "productdate")
	Date productDate = new Date();
	Boolean special;

	@Column(name = "likecount")
	Integer likeCount;
	Boolean available;

	@ManyToOne
	@JoinColumn(name = "categoryid")
	Category category;
	
	@ManyToOne
	@JoinColumn(name = "brandid")
	Brand brand;

	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "product")
	List<Share> shares;

	@OneToMany(mappedBy = "product")
	List<Wishlist> wishlists;
	
	public double getPromotePrice() {
		return this.unitPrice * ((100 - this.discount) / 100);
	}
}
