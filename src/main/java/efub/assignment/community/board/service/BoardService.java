package efub.assignment.community.board.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.AddBoardRequestDto;
import efub.assignment.community.board.dto.BoardModifyRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Board addBoard(AddBoardRequestDto requestDto) {
        Member boardOwner=memberRepository.findById(requestDto.getBoardOwner())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다"));
        return boardRepository.save(
                Board.builder()
                        .boardName(requestDto.getBoardName())
                        .intro(requestDto.getIntro())
                        .notice(requestDto.getNotice())
                        .boardOwner(boardOwner)
                        .build()
        );
    }

    public Board findBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판입니다."));
    }


    public void removeBoard(Long boardId,Long memberId) {
        Board board=boardRepository.findByBoardIdAndBoardOwner_MemberId(boardId,memberId)
                        .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        boardRepository.delete(board);
    }

    //
    public Board modifyBoard(Long boardId,  BoardModifyRequestDto requestDto) {
        Board board=boardRepository.findByBoardIdAndBoardOwner_MemberId(boardId,requestDto.getBoardOwner())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다.1"));

        Member member=memberRepository.findById(requestDto.getNewBoardOwner())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다.2"));

        board.updateBoard(member);
        return board;
    }






}
