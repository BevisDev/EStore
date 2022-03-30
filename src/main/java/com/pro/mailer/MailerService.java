package com.pro.mailer;

import javax.mail.MessagingException;

import com.pro.entity.Share;

public interface MailerService {

	/**
	 * Gửi email
	 * @param mail thông mail cần gửi 
	 * @exception MessagingException Lỗi gửi mail;
	 * */
	void send(Mail mail) throws MessagingException;
	
	/**
	 * Xếp mail vào hàng đợi(Queue) để gửi theo lịch trình
	 * @param mail thông tin email cần gửi
	 * */
	void addToQueue(Mail mail);

	void sendShare(Share share);
}
