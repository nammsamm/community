package efub.assignment.community.comment.dto;

import lombok.Getter;

@Getter
public class CommentModifyRequestDto {
    private Long memberId;
    private String content;
}
