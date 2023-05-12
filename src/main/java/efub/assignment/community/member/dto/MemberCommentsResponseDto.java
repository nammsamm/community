package efub.assignment.community.member.dto;

import efub.assignment.community.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberCommentsResponseDto {

    private String writerName;
    private List<MemberComment> comments;
    private Integer count;

    public static MemberCommentsResponseDto of(String writerName, List<Comment> commentList) {
        return MemberCommentsResponseDto.builder()
                .writerName(writerName)
                .comments(commentList.stream().map(MemberComment::of).collect(Collectors.toList()))
                .count(commentList.size())
                .build();
    }

    @Getter
    public static class MemberComment {

        private Long commentId;
        private Long postId;
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public MemberComment(Comment comment) {
            this.commentId = comment.getCommentId();
            this.postId = comment.getPost().getPostId();
            this.content = comment.getContent();
            this.createdDate = comment.getCreatedDate();
            this.modifiedDate = comment.getModifiedDate();
        }

        public static MemberComment of(Comment comment) {
            return new MemberComment(comment);
        }
    }
}
