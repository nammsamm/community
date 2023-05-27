package efub.assignment.community.message.dto;

/*
messageRoom id , 보낸 사람 Id,메시지 내용,  생성 시각(보낸시각)
 */

import efub.assignment.community.message.domain.Message;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MessageResponseDto {
    private Long messageRoomId;
    private Long senderId;
    private String messageContent;
    private LocalDateTime created;

    public MessageResponseDto(Message message){
        this.messageRoomId=message.getMessageRoom().getMessageRoomId();
        this.senderId=message.getSender().getMemberId();
        this.messageContent=message.getContent();
        this.created=message.getCreatedDate();
    }

    public static MessageResponseDto of(Message message){
        return MessageResponseDto.builder()
                .messageRoomId(message.getMessageId())
                .senderId(message.getSender().getMemberId())
                .messageContent(message.getContent())
                .created(message.getCreatedDate())
                .build();
    }


}
