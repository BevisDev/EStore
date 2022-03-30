package com.pro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SideSetting")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SideSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name = "phoneone")
	String phoneOne;
	@Column(name = "phonetwo")
	String phoneTwo;
	String email;
	@Column(name ="companyname")
	String companyName;
	@Column(name ="companyaddress")
	String companyAddress;
	String facebook;
	String youtube;
	String instagram;
		
}
