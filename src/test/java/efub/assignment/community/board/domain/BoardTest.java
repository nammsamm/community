package efub.assignment.community.board.domain;

import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/* 물리적 DB인 MySQL 을 이용하기 위하여 AutoConfigureTestDatabase.Replace.NONE 설정 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class BoardTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;

    /* 성공 : board 객체 생성 */
    @Test
    public void boardTest(){
        /* given */
        String boardName = "testBoard";
        String intro = "테스트 게시판";
        String notice = "테스트입니다";
        Long memberId = 2L;

        /* when */
        Board board = new Board(boardName,intro,notice,memberRepository.findMemberByMemberId(memberId));

        /* then */
        assertThat(board.getBoardName()).isEqualTo(boardName);
        assertThat(board.getIntro()).isEqualTo(intro);
        assertThat(board.getNotice()).isEqualTo(notice);
        assertThat(board.getBoardOwner().getMemberId()).isEqualTo(memberId);
    }

    /* 실패 : 존재하지 않는 게시판에 대해 owner 을 변경하려 하는 경우 */
    @Test
    public void updateBoard_GivenInvalidBoard_ReturnNullPointerException(){
        /* given */
        final Long INVALID_BOARD_ID = 10L;
        final Long VALID_MEMBER_ID = 3L;

        /* when , then */
        Board board = boardRepository.findByBoardId(INVALID_BOARD_ID);
        Member member = memberRepository.findMemberByMemberId(VALID_MEMBER_ID);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> board.updateBoard(member));

        assertThat(e.getMessage()).isEqualTo(null);
    }

    /* 실패 : 존재하지 않는 계정을 게시판의 주인으로 바꾸려고 하는 경우 */
    @Test
    public void updateBoard_GivenInvalidMember_ReturnNullPointerException(){
        /* given */
        Long boardId = 1L;
        final Long INVALID_MEMBER_ID = 11L;

        /* when */
        Member member = memberRepository.findMemberByMemberId(INVALID_MEMBER_ID);
        Board board = boardRepository.findByBoardId(boardId);
        board.updateBoard(member);

        /* then */
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> board.getBoardOwner().getMemberId());
        assertThat(e.getMessage()).isEqualTo(null);
    }


}