package efub.assignment.community.post.dto;

import lombok.Getter;

@Getter
public class PostModifyRequestDto {
    private Long writerId;
    private String content;
}
