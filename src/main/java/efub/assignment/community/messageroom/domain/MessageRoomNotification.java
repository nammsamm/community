package efub.assignment.community.messageroom.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomNotification {
    @Id
    @GeneratedValue
    @Column(name = "messageRoom_notification_if")
    private Long messageRoomNotificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "messageRoom",updatable = false)
    private MessageRoom messageRoom;

    @Column(name = "messageRoom_notification-content")
    private String messageRoomNotificationContent;

    @Builder
    public MessageRoomNotification(MessageRoom messageRoom, String content){
        this.messageRoom=messageRoom;
        this.messageRoomNotificationContent=content;
    }
}
