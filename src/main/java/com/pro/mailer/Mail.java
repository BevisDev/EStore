package com.pro.mailer;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Mail {

	String to, subject, text, from ,cc , bcc, attachments;

	/**
	 * Tạo Mail Object từ các tham số 
	 * @param to là mail người nhận
	 * @param subject là tiêu đề mail
	 * @param text là nội dung mail
	 * */
	public Mail(String to, String subject, String text) {
		this(to, subject, text, Map.of());
	}


	/**
	 * Tạo Mail Object từ các tham số
	 * @param to là mail người nhận
	 * @param subject là tiêu đề mail
	 * @param text là nội dung email
	 * @param others là Map<String, String> chứa các thông tin khác như form, cc, bcc, attachments
	 * @param others lấy giá trị từ mail còn ko lấy mặc định ( hoặc null)
	 * */
	public Mail(String to, String subject, String text, Map<String, String> others) {
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.from = others.getOrDefault("from", "Bevis Shop <truongthanhbinh572000@gmail.com>");
		this.cc = others.get("cc");
		this.bcc = others.get("bc");
		this.attachments = others.get("attachments");
	}
}
