package efub.assignment.community.messageroom.repository;

import efub.assignment.community.messageroom.domain.MessageRoomNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRoomNotificationRepository extends JpaRepository<MessageRoomNotification,Long> {
}
