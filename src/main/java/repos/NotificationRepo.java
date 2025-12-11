package repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long> {

	List<Notification> findByRecipientOrderByCreatedAtDesc(String recipient);

}
