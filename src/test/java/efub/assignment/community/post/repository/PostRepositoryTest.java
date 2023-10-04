package efub.assignment.community.post.repository;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    public PostRepository postRepository;
    @Autowired
    public BoardRepository boardRepository;
    @Autowired
    public MemberRepository memberRepository;

    /* 성공 : 게시글 저장 */
    @Test
    public void savePostTest(){
        /* given */
        Long boardId = 1L;
        Long memberId = 1L;
        boolean writerShow = true;
        String content = "테스트 게시글입니다.";

        /* when */
        Board board = boardRepository.findByBoardId(boardId);
        Member member = memberRepository.findMemberByMemberId(memberId);
        Post post = new Post(board,member,writerShow,content);
        Long postId= postRepository.save(post).getPostId();

        /* then */
        Post findPost = postRepository.findPostByPostId(postId);
        assertThat(findPost.getBoard().getBoardId()).isEqualTo(boardId);
        assertThat(findPost.getWriter().getMemberId()).isEqualTo(memberId);
        assertThat(findPost.isWriterShow()).isEqualTo(writerShow);
        assertThat(findPost.getContent()).isEqualTo(content);
    }

    /* 실패 : 존재하지 않는 게시글 조회 */
    @Test
    public void findPostByPostId_GivenInvalidPostId_ReturnNull(){
        /* given */
        final Long INVALID_POST_ID = 20L;

        /* when */
        Post post = postRepository.findPostByPostId(INVALID_POST_ID);

        /* then */
        assertThat(post).isNull();
    }

    /* 실패 : 존재하지 않는 게시글 삭제 */
    @Test
    public void deletePost_GivenInvalidPostId_ReturnInvalidDataAccessApiUsageException(){
        /* given */
        final Long INVALID_POST_ID = 20L;

        /* when , then */
        Post post = postRepository.findPostByPostId(INVALID_POST_ID);
        InvalidDataAccessApiUsageException e = assertThrows(InvalidDataAccessApiUsageException.class ,
                ()-> postRepository.delete(post));
        assertThat(e.getMessage()).isEqualTo("Entity must not be null!; nested exception is java.lang.IllegalArgumentException: Entity must not be null!");
    }
}