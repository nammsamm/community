package efub.assignment.community.comment.repository;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/* 물리적 DB인 MySQL 을 이용하기 위하여 AutoConfigureTestDatabase.Replace.NONE 설정 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public MemberRepository memberRepository;

    /* 성공 : 댓글 저장 */
    @Test
    public void saveCommentTest(){
        /* given */
        String content = "테스트 댓글입니다.";
        Long postId= 2L;
        Long memberId = 2L;

        /* when */
        Post post = postRepository.findPostByPostId(postId);
        Member member = memberRepository.findMemberByMemberId(memberId);
        Comment comment = new Comment(content,post,member);
        Long commentId = commentRepository.save(comment).getCommentId();

        /* then */
        Comment findComment = commentRepository.findCommentByCommentId(commentId);
        assertThat(findComment.getContent()).isEqualTo(content);
        assertThat(findComment.getPost().getPostId()).isEqualTo(postId);
        assertThat(findComment.getWriter().getMemberId()).isEqualTo(memberId);
    }

    /* 실패 : 존재하지 않는 댓글 조회 */
    @Test
    public void findCommentByCommentId_GivenInvalidCommentId_ReturnNull(){
        /* given */
        final Long INVALID_COMMENT_ID = 10L;

        /* when */
        Comment comment = commentRepository.findCommentByCommentId(INVALID_COMMENT_ID);

        /* then */
        assertThat(comment).isNull();

    }

    /* 실패 : 존재하지 않는 댓글 삭제 */
    @Test
    public void deleteComment_GivenInvalidCommentId_ReturnInvalidDataAccessApiUsageException(){
        /* given */
        final Long INVALID_COMMENT_ID = 10L;

        /* when , then */
        Comment comment = commentRepository.findCommentByCommentId(INVALID_COMMENT_ID);
        InvalidDataAccessApiUsageException e = assertThrows(InvalidDataAccessApiUsageException.class ,
                ()-> commentRepository.delete(comment));
        assertThat(e.getMessage()).isEqualTo("Entity must not be null!; nested exception is java.lang.IllegalArgumentException: Entity must not be null!");
    }

}