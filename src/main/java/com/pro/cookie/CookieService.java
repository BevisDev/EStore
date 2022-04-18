package com.pro.cookie;

import javax.servlet.http.Cookie;

public interface CookieService {
	/**
	 * Đọc cookie
	 * @param name tên cookie
	 * @return cookie hoặc null nếu không tồn tại
	 */
	Cookie get(String name);
	/**
	 * Đọc giá trị cookie
	 * @param name tên cookie
	 * @return giá trị cookie hoặc "" nếu không tồn tại
	 */
	String getValue(String name, String defval);
	/**
	 * Tạo và gửi cookie về client
	 * @param name tên cookie
	 * @param value giá trị cookie
	 * @param days số ngày tồn tại
	 * @return cookie đã tạo
	 */
	Cookie create(String name, String value, int days);
	/**
	 * Xóa cookie
	 * @param name tên cookie
	 */
	void delete(String name);
}
