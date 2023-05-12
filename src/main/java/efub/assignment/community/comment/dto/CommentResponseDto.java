package efub.assignment.community.comment.dto;

import efub.assignment.community.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CommentResponseDto {
    private Long commentId;
    private Long postId;
    private String writerName;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;

    public static CommentResponseDto of(Comment comment){
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .postId(comment.getPost().getPostId())
                .writerName(comment.getWriter().getNickname())
                .content(comment.getContent())
                .created(comment.getCreatedDate())
                .modified(comment.getModifiedDate())
                .build();

    }
}
