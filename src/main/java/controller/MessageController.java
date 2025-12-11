package controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import services.MessageProducer;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	private final MessageProducer messageProducer;
	
	@Autowired
	public MessageController(MessageProducer messageProducer) {
		super();
		this.messageProducer = messageProducer;
	}

	@PostMapping("/email-notification")
	public ResponseEntity<?> sendEmailNotification(
			@RequestParam String recipient,
			@RequestParam String subject,
			@RequestParam String content,
			@RequestParam(defaultValue = "MEDIUM") String priority
			){
		
		logger.info("Request to send Email Notofication to: {}", recipient);
		
		try {
			String messageId = messageProducer.sendEmailNotification(recipient, subject, content, priority);
			
			return ResponseEntity.ok().body(Map.of(
					"status", "sucess",
					"messageId", messageId,
					"message", "Email Notification sent to RabbitMQ successfully"
			));
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of(
					"status", "error",
					"message", e.getMessage()
			));
		}
	}
	

	@PostMapping("/sms-notification")
	public ResponseEntity<?> sendSmsNotification(
			@RequestParam String recipient,
			@RequestParam String content
			){
		
		logger.info("Request to send Sms Notofication to: {}", recipient);
		
		try {
			String messageId = messageProducer.sendSmsNotification(recipient, content);
			
			return ResponseEntity.ok().body(Map.of(
					"status", "sucess",
					"messageId", messageId,
					"message", "Sms Notification sent to RabbitMQ successfully"
			));
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of(
					"status", "error",
					"message", e.getMessage()
			));
		}
	}
	
}
