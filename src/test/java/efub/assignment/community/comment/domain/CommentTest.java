package efub.assignment.community.comment.domain;

import efub.assignment.community.comment.dto.CommentModifyRequestDto;
import efub.assignment.community.comment.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/* 물리적 DB인 MySQL 을 이용하기 위하여 AutoConfigureTestDatabase.Replace.NONE 설정 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CommentTest {
    @Autowired
    public CommentRepository commentRepository;

    /* 성공 : 댓글 수정 */
    @Test
    public void updateCommentTest(){
        /* given */
        Long commentId= 1L;
        Long memberId = 1L;
        String content = "테스트 댓글입니다.";

        /* when */
        CommentModifyRequestDto requestDto = new CommentModifyRequestDto(memberId,content);
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        comment.updateComment(requestDto);

        /* then */
        assertThat(comment.getContent()).isEqualTo(content);
    }

    /* 실패 : 존재하지 않는 댓글에 대한 수정 */
    @Test
    public void updateComment_GivenInvalidCommentId_ReturnNullPointerException(){
        /* given */
        Long INVALID_COMMENT_ID= 10L;
        Long memberId = 1L;
        String content = "테스트 댓글입니다.";

        /* when , then */
        CommentModifyRequestDto requestDto = new CommentModifyRequestDto(memberId,content);
        Comment comment = commentRepository.findCommentByCommentId(INVALID_COMMENT_ID);

        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> comment.updateComment(requestDto));
        assertThat(e.getMessage()).isEqualTo(null);
    }
}