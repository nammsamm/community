package efub.assignment.community.comment.repository;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //작성자별 댓글 목록 조회
    List<Comment> findAllByWriter(Member writer);

    // 게시글별 댓글 목록 조회
    List<Comment> findAllByPost(Post post);

    // 댓글 조회
    Optional<Comment> findByCommentIdAndWriter_MemberId(Long commentId,Long memberId);

    Comment findCommentByCommentId(Long commentId);

}
