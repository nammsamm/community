package efub.assignment.community.board.controller;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.AddBoardRequestDto;
import efub.assignment.community.board.dto.BoardModifyRequestDto;
import efub.assignment.community.board.dto.BoardResponsetDto;
import efub.assignment.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController // JSON 형태로 객체 데이터를 반환
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponsetDto boardAdd(@RequestBody AddBoardRequestDto requestDto){
        Board board = boardService.addBoard(requestDto);
        return new BoardResponsetDto(board);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponsetDto boardFind(@PathVariable Long boardId){
        Board board=boardService.findBoard(boardId);
        return new BoardResponsetDto(board);
    }

    @DeleteMapping("/{boardId}/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String boardRemove(@PathVariable Long boardId, @PathVariable Long memberId){
        boardService.removeBoard(boardId,memberId);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponsetDto boardModify(@PathVariable Long boardId, @RequestBody BoardModifyRequestDto requestDto){
        Board board=boardService.modifyBoard(boardId,requestDto);
        return new BoardResponsetDto(board);
    }




}
