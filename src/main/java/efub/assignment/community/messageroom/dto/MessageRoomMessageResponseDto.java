package efub.assignment.community.messageroom.dto;

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.MessageFindRequestDto;
import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
messageRoom id, 상대방 id, 메시지 목록 =  [message 내용, 시각, 보낸 건지 받은 건지 여부]
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MessageRoomMessageResponseDto {
    private Long messageRoomId;
    private Long otherMemberId;
    private List<MessageRoomMessage> messageRoomMassages;


    public static MessageRoomMessageResponseDto of(Long messageRoomId, Long otherUserId, List<MessageFindRequestDto> requestDtoList) {
        return MessageRoomMessageResponseDto.builder()
                .messageRoomId(messageRoomId)
                .otherMemberId(otherUserId)
                .messageRoomMassages(requestDtoList.stream().map(MessageRoomMessage::of).collect(Collectors.toList()))
                .build();
    }

    @Getter
    public static class MessageRoomMessage{
        private String messageContent;
        private LocalDateTime messageCreated;
        private Boolean isReceived;

        public MessageRoomMessage(MessageFindRequestDto requestDto){
            this.messageContent= requestDto.getMessageContent();
            this.messageCreated=requestDto.getMessageCreated();
            this.isReceived=requestDto.getIsReceived();
        }

        private static MessageRoomMessage of(MessageFindRequestDto requestDto){
            return new MessageRoomMessage(requestDto);
        }
    }
}
