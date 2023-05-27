package efub.assignment.community.messageroom.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
요청 내용 : 처음 보낸 사람 id, 처음 받는 사람 Id , 첫쪽지 내용,  쪽지가 시작된 글의 id
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomRequestDto {
    private Long senderId;
    private Long receiverId;
    private String firstMessage;

    private Long postId;

    public MessageRoomRequestDto(Long senderId,Long receiverId,String content,Long postId){
        this.senderId=senderId;
        this.receiverId=receiverId;
        this.firstMessage =content;
        this.postId=postId;
    }
}
