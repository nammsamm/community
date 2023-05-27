package efub.assignment.community.message.dto;

/*
조회한 사람의 id
 */

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageFindRequestDto {
    private Boolean isReceived;
    private String  messageContent;
    private LocalDateTime messageCreated;

    @Builder
    public MessageFindRequestDto(Boolean isReceived, String messageContent, LocalDateTime messageCreated){
        this.isReceived=isReceived;
        this.messageContent=messageContent;
        this.messageCreated=messageCreated;
    }
}
