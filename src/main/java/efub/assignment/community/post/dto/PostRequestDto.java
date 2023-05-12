package efub.assignment.community.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
{
		"boardName": "벗들의 맛집",
		"writerId" : "200"
		"writerShow" : true,
		"content" : "낭만식탁 맛조항요~"
}
 */
@Getter
@NoArgsConstructor
public class PostRequestDto {
    private Long boardId;
    private Long writerId;
    private boolean writerShow;
    private String content;

}
