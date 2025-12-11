package services;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import config.RabbitMQConfig;
import entities.Notification;
import model.Message;
import repos.NotificationRepo;

@Service
public class MessageConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	
	private final NotificationRepo notificationRepo;
	
	public MessageConsumer(NotificationRepo notificationRepo) {
		super();
		this.notificationRepo = notificationRepo;
	}

	@RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
	public void handleEmail(Message message) {
		logger.info("Email notification received: Id={}, Recipient={}, subject={}",
				message.getId(), message.getRecipient(), message.getSubject());
		
		try {
			// Simulating the Email sending time with thread sleep
			Thread.sleep(2000);
			
			Notification notification = new Notification();
			notification.setContent(message.getContent());
			notification.setMessageId(message.getId());
			notification.setProcessedAt(LocalDateTime.now());
			notification.setRecipient(message.getRecipient());
			notification.setStatus("SENT");
			notification.setSubject(message.getSubject());
			notification.setType(message.getType());
			
			notificationRepo.save(notification);
			logger.info("Email Notification message processed and saved with ID: {}", message.getId());
		} catch (Exception e) {
			logger.error("Failed to process the message: {}", e.getMessage(), e);
			
			Notification notification = new Notification();
			notification.setContent(message.getContent());
			notification.setMessageId(message.getId());
			notification.setProcessedAt(LocalDateTime.now());
			notification.setRecipient(message.getRecipient());
			notification.setStatus("FAILED");
			notification.setSubject(message.getSubject());
			notification.setType(message.getType());
			
			notificationRepo.save(notification);
		}
	}
	
	@RabbitListener(queues = RabbitMQConfig.SMS_QUEUE)
	public void handleSms(Message message) {
		logger.info("Email notification received: Id={}, Recipient={}, Content={}",
				message.getId(), message.getRecipient(), message.getContent());
		
		try {
			// Simulating the Email sending time with thread sleep
			Thread.sleep(500);
			
			Notification notification = new Notification();
			notification.setContent(message.getContent());
			notification.setMessageId(message.getId());
			notification.setProcessedAt(LocalDateTime.now());
			notification.setRecipient(message.getRecipient());
			notification.setStatus("SENT");
			notification.setSubject(message.getSubject());
			notification.setType(message.getType());
			
			notificationRepo.save(notification);
			logger.info("Sms Notification message processed and saved with ID: {}", message.getId());
		} catch (Exception e) {
			logger.error("Failed to process the message: {}", e.getMessage(), e);
			
			Notification notification = new Notification();
			notification.setContent(message.getContent());
			notification.setMessageId(message.getId());
			notification.setProcessedAt(LocalDateTime.now());
			notification.setRecipient(message.getRecipient());
			notification.setStatus("FAILED");
			notification.setSubject(message.getSubject());
			notification.setType(message.getType());
			
			notificationRepo.save(notification);
		}
	}
}
