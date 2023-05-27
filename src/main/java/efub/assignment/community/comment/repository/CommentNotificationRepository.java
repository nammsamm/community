package efub.assignment.community.comment.repository;

import efub.assignment.community.comment.domain.CommentNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentNotificationRepository extends JpaRepository<CommentNotification,Long> {
}
