package efub.assignment.community.member.dto;


import efub.assignment.community.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String nickname;
    private String university;
    private Integer studentId;
    private String bio;

    //bio는 제외시킴.
    public MemberResponseDto(Long memberId,String email,String nickname,String university,Integer studentId){
        this.memberId=memberId;
        this.email=email;
        this.nickname=nickname;
        this.university=university;
        this.studentId=studentId;
    }
    public static MemberResponseDto from(Member member){
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(),
                member.getNickname(),
                member.getUniversity(),
                member.getStudentId());
    }
}