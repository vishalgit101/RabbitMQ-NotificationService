package model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;



public class Message {
	private String id;
	private String type;
	private String recipient;
	private String subject;
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timeStamp;
	
	private String priority; //HIGH MEDIUM LOW

	public Message(String id, String type, String recipient, String subject, String content, LocalDateTime timeStamp,
			String priority) {
		super();
		this.id = id;
		this.type = type;
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
		this.timeStamp = timeStamp;
		this.priority = priority;
	}

	public Message() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
