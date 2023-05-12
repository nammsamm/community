package efub.assignment.community.comment.dto;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    @NotNull(message = "작성자 ID를 입력해주세요.")
    private Long memberId;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

    public Comment toEntity(Post post, Member writer){
        return Comment.builder()
                .post(post)
                .writer(writer)
                .content(this.content)
                .build();

    }
}
