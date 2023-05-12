package efub.assignment.community.board.dto;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponsetDto {
    private Long boardId;
    private String boardName;
    private String intro;
    private String notice;
    private Long boardOwnerId;
    private LocalDateTime created;
    private LocalDateTime updated;


    public BoardResponsetDto(Board board){
        this.boardId=board.getBoardId();
        this.boardName=board.getBoardName();
        this.intro=board.getIntro();
        this.notice=board.getNotice();
        this.boardOwnerId=board.getBoardOwner().getMemberId();
        this.created=board.getCreatedDate();
        this.updated=board.getModifiedDate();
    }

}
