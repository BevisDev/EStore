package com.pro.admin.bean;

import lombok.Data;

@Data
public class AccountFilter {

	int role;
	int activated;
	String keyword = "";
}
