package efub.assignment.community.member.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberResponseDto;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController // JSON 형태로 객체 데이터를 반환
@RequestMapping("/members") // 요청이 들어온 URI와 Annotation의 value가 일치하면 해당 클래스나 메소드를 실행.
@RequiredArgsConstructor
public class MemeberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MemberResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Long id=memberService.signUp(requestDto);
        Member findMember=memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getMember(@PathVariable Long memberId)
    {
        Member findmember = memberService.findMemberById(memberId);
        return MemberResponseDto.from(findmember);
    }

    @PatchMapping("/profile/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto update(@PathVariable final Long memberId,
                                    @RequestBody @Valid final MemberUpdateRequestDto requestDto){
        Long id=memberService.update(memberId,requestDto);
        Member findMember = memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }

    @PatchMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable long memberId){
        memberService.withdraw(memberId);
        return "성공적으로 탈퇴가 완료되었습니다.";
    }
}
