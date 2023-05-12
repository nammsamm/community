package efub.assignment.community.member.service;


import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service // 서비스 계층임을 명시하여 자바 로직을 처리합니다.
@Transactional // import class 할 때 (org.springframework.annotation)으로 선택. 트랜잭션 처리를 위해 사용
@RequiredArgsConstructor // final 또는 @NotNull이 붙은 필드의 생성자를 자동 생성하여 의존성을 외부에서 주입.
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signUp(SignUpRequestDto requestDto) {
        if (existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 email 입니다." + requestDto.getEmail());
        }
        Member member = memberRepository.save(requestDto.toEntity());
        return member.getMemberId();
    }

    @Transactional(readOnly = true) // 트랜잭션을 일기 전용 모드로 설정 -> 메모리 사용량 최적화
    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 member를 찾을 수 없습니다. id = "+id));
    }

    public Long update(Long memberId, MemberUpdateRequestDto requestDto){
        Member member=findMemberById(memberId);
        member.updateMember(requestDto.getEmail(),requestDto.getPassword(),requestDto.getNickname()
                ,requestDto.getUniversity(),requestDto.getStudentId());

        return member.getMemberId();
    }

    @Transactional
    public void withdraw(Long memberId){
        Member member=findMemberById(memberId);
        member.withdrawMember();
    }
}