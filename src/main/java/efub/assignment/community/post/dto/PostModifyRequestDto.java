package efub.assignment.community.post.dto;

import lombok.Getter;

@Getter
public class PostModifyRequestDto {
    private Long writerId;
    private String content;

    public PostModifyRequestDto(Long writerId , String content){
        this.writerId=writerId;
        this.content=content;
    }
}
