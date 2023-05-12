package efub.assignment.community.post.dto;

import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto {
    private Long postId;
    private String boardName;
    private Long writerId;
    private boolean writerShow;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    public PostResponseDto(Post post){
        this.postId=post.getPostId();
        this.boardName=post.getBoard().getBoardName();
        this.writerId=post.getWriter().getMemberId();
        this.writerShow= post.isWriterShow();
        this.content=post.getContent();
        this.created=post.getCreatedDate();
        this.updated=post.getModifiedDate();
    }


    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
        );
    }
}
