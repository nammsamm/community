package efub.assignment.community.messageroom.dto;

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
메시지룸 목록들  = [messageRoom id, 최신 메시지 1개, 최신 메시지 보낸 시각]
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MessageRoomListResponseDto {
  private Long memberId;
  private List<MemberMessageRoom> messageRoomList;
  private Integer count;


  public static MessageRoomListResponseDto of(Long memberId,List<MessageRoom> messageRoomList){
    return MessageRoomListResponseDto.builder()
            .memberId(memberId)
            .messageRoomList(messageRoomList.stream().map(MemberMessageRoom::of).collect(Collectors.toList()))
            .count(messageRoomList.size())
            .build();
  }

  @Getter
  public static class MemberMessageRoom{
    private Long messageRoomId;
    private String lastMessageContent;
    private LocalDateTime lastMessageCreated;

    public MemberMessageRoom(MessageRoom messageRoom){
      this.messageRoomId=messageRoom.getMessageRoomId();

      // 첫 메시지만 보낸 상태일 경우
      if(messageRoom.getMessageList().isEmpty()){
        this.lastMessageContent=messageRoom.getFirstMessage();
        this.lastMessageCreated=messageRoom.getCreatedDate();
        }

      // 첫 메시지 이후의 메시지가 존재할 경우
      else{
        this.lastMessageContent= messageRoom.getMessageList().get(messageRoom.getMessageList().size()-1).getContent();
        this.lastMessageCreated=messageRoom.getMessageList().get(messageRoom.getMessageList().size()-1).getCreatedDate();
        }

    }

    public static MemberMessageRoom of(MessageRoom messageRoom){
      return new MemberMessageRoom(messageRoom);
    }
  }
}
