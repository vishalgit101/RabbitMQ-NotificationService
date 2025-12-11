package entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String messageId;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String recipient;
	
	@Column(nullable = false)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column(nullable = false)
	private String status; // SENT PENDING FAILED
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	private LocalDateTime processedAt;
	
	
	
	public Notification() {
		super();
	}

	public Notification(Long id, String messageId, String type, String recipient, String subject, String content,
			String status, LocalDateTime createdAt, LocalDateTime processedAt) {
		super();
		this.id = id;
		this.messageId = messageId;
		this.type = type;
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
		this.status = status;
		this.createdAt = createdAt;
		this.processedAt = processedAt;
	}

	@PrePersist
	public void prePersist() {
		if(createdAt == null) {
			createdAt = LocalDateTime.now();
		}
		
		if(status == null) {
			status = "PENDING";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(LocalDateTime processedAt) {
		this.processedAt = processedAt;
	}
	
	
}
