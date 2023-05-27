package efub.assignment.community.message.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/*
요청 : messageRoom id , 보낸 사람 Id, 메시지 내용
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRequestDto {
    @NotNull(message = "보내는 사람 ID를 입력하세요.")
    private Long senderId;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

    public Message toEntity(MessageRoom messageRoom,Member member){
        return Message.builder()
                .messageRoom(messageRoom)
                .sender(member)
                .content(this.content)
                .build();
    }
}
