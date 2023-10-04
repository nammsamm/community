package efub.assignment.community.board.repository;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.junit.jupiter.api.Assertions.*;

/* 물리적 DB인 MySQL 을 이용하기 위하여 AutoConfigureTestDatabase.Replace.NONE 설정 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public BoardRepository boardRepository;

    /* 성공 : 게시글 저장 */
    @Test
    public void saveBoard(){
        /* given */
        String boardName = "testBoard";
        String intro = "테스트 게시판";
        String notice = "테스트입니다";
        Long memberId = 2L;

        /* when */
        Board board = new Board(boardName,intro,notice,memberRepository.findMemberByMemberId(memberId));
        Long boardId = boardRepository.save(board).getBoardId();
        Board findBoard = boardRepository.findByBoardId(boardId);

        /* then */
        assertThat(findBoard.getBoardName()).isEqualTo(boardName);
        assertThat(findBoard.getIntro()).isEqualTo(intro);
        assertThat(findBoard.getNotice()).isEqualTo(notice);
        assertThat(findBoard.getBoardOwner().getMemberId()).isEqualTo(memberId);
    }

    /* 실패 : 존재하지 않는 boardId 로 게시판을 조회하는 경우 */
    @Test
    public void findByBoardId_GivenInvalidBoardId_ReturnNull(){
        /* given */
        final Long INVALID_BOARD_ID = 10L;

        /* when, then */
        Board board = boardRepository.findByBoardId(INVALID_BOARD_ID);
        assertThat(board).isEqualTo(null);
    }

    /* 실패 : 존재하지 않는 게시판을 삭제하려 하는 경우 */
    @Test
    public void deleteBoard_GivenInvalidBoard_ReturnInvalidDataAccessApiUsageException(){
        /* given */
        final Long INVALID_BOARD_ID = 10L;

        /* when , then */
        Board board = boardRepository.findByBoardId(INVALID_BOARD_ID);
        InvalidDataAccessApiUsageException e = assertThrows(InvalidDataAccessApiUsageException.class ,
                ()-> boardRepository.delete(board));
        assertThat(e.getMessage()).isEqualTo("Entity must not be null!; nested exception is java.lang.IllegalArgumentException: Entity must not be null!");
    }
}