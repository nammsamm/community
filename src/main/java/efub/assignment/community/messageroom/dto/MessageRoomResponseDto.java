package efub.assignment.community.messageroom.dto;

import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
생성에 대한 응답 내용 : message room id , 처음 보낸 사람 Id, 처음 받는 사람 Id , 메시지 내용 ,생성 시각(보낸시각)
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomResponseDto {
    private Long messageRoomId;
    private Long senderId;
    private Long receiverId;
    private String firstMessage;
    private LocalDateTime created;


    public MessageRoomResponseDto(MessageRoom messageRoom){
        this.messageRoomId =messageRoom.getMessageRoomId();
        this.senderId=messageRoom.getStarter().getMemberId();
        this.receiverId=messageRoom.getReceiver().getMemberId();
        this.firstMessage= messageRoom.getFirstMessage();
        this.created=messageRoom.getCreatedDate();

    }

}
