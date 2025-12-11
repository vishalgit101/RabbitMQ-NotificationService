package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Notification;
import repos.NotificationRepo;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	private final NotificationRepo notificationRepo;

	public NotificationController(NotificationRepo notificationRepo) {
		super();
		this.notificationRepo = notificationRepo;
	}
	
	@GetMapping("/allnotification")
	public ResponseEntity<?> getAllNotification(){
		List<Notification> listofnotifications = notificationRepo.findAll();
		return ResponseEntity.ok().body(listofnotifications); 
	}
	
	@GetMapping("/notificationsbyrecipient")
	public ResponseEntity<?> getNotificationByRecipient(@RequestParam String recipient){
		List<Notification> notificationsbyrecipient = notificationRepo.findByRecipientOrderByCreatedAtDesc(recipient);
		return ResponseEntity.ok().body(notificationsbyrecipient);
	}
	
}
