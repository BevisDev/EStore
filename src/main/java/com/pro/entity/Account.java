package com.pro.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

	@Id
	String username;
	String password;
	String fullname;
	
	@Column(unique = true)
	String email;
	String phone;
	String avatar = "user.png";
	Boolean activated;
	String address;

	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@OneToMany(mappedBy = "account")
	List<Wishlist> wishlists;
	
	public boolean hasRole(Role role) {
		if(this.authorities != null) {
			return this.authorities.stream()
					.anyMatch(a -> a.getRole().getId().equals(role.getId()));
		}
		return false;
	}
}
