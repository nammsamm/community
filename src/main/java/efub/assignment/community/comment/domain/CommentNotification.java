package efub.assignment.community.comment.domain;

import efub.assignment.community.global.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentNotification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_notification_id")
    private Long commentNotificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "댓글은 필수로 입력되어야 합니다.")
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public CommentNotification(Comment comment){
        this.comment=comment;
    }


}
