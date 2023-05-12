package efub.assignment.community.member.controller;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.service.CommentService;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberCommentsResponseDto;
import efub.assignment.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/comments")
public class MemberCommentController {
    private final CommentService commentService; // 의존관계 : MemberCommentController -> CommentService
    private final MemberService memberService; // 의존관계 : MemberCommentController -> MemberService

    //특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberCommentsResponseDto readMemberComments(@PathVariable Long memberId){
        Member writer = memberService.findMemberById(memberId);
        List<Comment> commentList=commentService.findCommentListByWriter(writer);
        return MemberCommentsResponseDto.of(writer.getNickname(),commentList);
    }
}
