package efub.assignment.community.board.dto;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class AddBoardRequestDto {

    private String boardName;

    private String intro;

    private String notice;

    private Long boardOwner;


}
