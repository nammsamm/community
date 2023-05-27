package efub.assignment.community.messageroom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
요청 내용 : 조회하는 사람의 id, 받는 사람의 id, 시작 게시글의 id
 */
@Getter
@NoArgsConstructor
public class MessageRoomFindRequestDto {
    private Long finderId;
    private Long receiverId;
    private Long postId;

}
