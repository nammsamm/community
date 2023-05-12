package efub.assignment.community.board.dto;


import lombok.Getter;

@Getter
public class BoardModifyRequestDto {
    private Long boardOwner;
    private Long newBoardOwner;
}
