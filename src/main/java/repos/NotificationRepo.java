package repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

	List<Notification> findByRecipientOrderByCreatedAtDesc(String recipient);

}
