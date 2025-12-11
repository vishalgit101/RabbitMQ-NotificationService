package services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import config.RabbitMQConfig;
import model.Message;

@Service
public class MessageProducer {

	private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
	
	private final RabbitTemplate rabbitTemplate;
	
	public MessageProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public String sendEmailNotification(String recipient, String subject, String content, String priority) {
		String messageId = UUID.randomUUID().toString();
		
		Message message = new Message();
		message.setId(messageId);
		message.setPriority("HIGH");
		message.setSubject(subject);
		message.setRecipient(recipient);
		message.setType("EMAIL");
		message.setContent(content);
		message.setTimeStamp(LocalDateTime.now());
		
		
		try {
			logger.info("Sending email-notification with ID: {} to RabbitMQ", messageId);
			rabbitTemplate.convertAndSend(RabbitMQConfig.APP_EXCHANGE, RabbitMQConfig.EMAIL_NOTIFICATION_ROUTING_KEY, message);
			logger.info("Email-notification sent to the Queue");
			return messageId;
		} catch (Exception e) {
			logger.error("Failed to send the email message: {}", e.getMessage());
			throw new RuntimeException("Failed to send the email meessage");
		}
	}

	public String sendSmsNotification(String recipient, String content) {
String messageId = UUID.randomUUID().toString();
		
		Message message = new Message();
		message.setId(messageId);
		message.setSubject("Sms notification");
		message.setPriority("HIGH");
		message.setRecipient(recipient);
		message.setType("SMS");
		message.setContent(content);
		message.setTimeStamp(LocalDateTime.now());
		
		
		try {
			logger.info("Sending sms-notification with ID: {} to RabbitMQ", messageId);
			rabbitTemplate.convertAndSend(RabbitMQConfig.APP_EXCHANGE, RabbitMQConfig.SMS_NOTIFICATION_ROUTING_KEY, message);
			logger.info("sms-notification sent to the Queue");
			return messageId;
		} catch (Exception e) {
			logger.error("Failed to send the sms message: {}", e.getMessage());
			throw new RuntimeException("Failed to send the sms meessage");
		}
	}

}
