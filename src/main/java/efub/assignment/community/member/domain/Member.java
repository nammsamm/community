package efub.assignment.community.member.domain;

import efub.assignment.community.global.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static efub.assignment.community.member.domain.MemberStatus.REGISTERED;

@Entity //테이블과의 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본 생성자를 생성.
// PROTECTED 접근 제어자로 생성자의 접근을 제어. -> 무분별한 객체 생성 막음.
@Getter
public class Member extends BaseTimeEntity {
    @Id //테이블의 PK를 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 전략을 정의
    @Column(name="member_id",updatable = false) //테이블의 칼럼을 매핑
    private Long memberId;

    @Column(nullable = false,length = 60)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false,updatable = false,length = 16)
    private String nickname;

    @Column(nullable = false,updatable = false,length = 7) // 추가
    private Integer studentId;

    @Column(nullable = false,updatable = false,length = 60) // 추가
    private String university;


    @Enumerated(EnumType.STRING) // enum 타입
    private MemberStatus status;

    @Builder // 객체를 생성하는 빌더 패턴.
    public Member(Long memberId,String email,String password,String nickname,Integer studentId,
                  String university){
        this.memberId=memberId;
        this.email=email;
        this.encodedPassword=password;
        this.nickname=nickname;
        this.university=university;
        this.studentId=studentId;
        this.status=REGISTERED;
    }

    public void updateMember(String email, String password,String nickname,String university,Integer studentId){
        this.email=email;
        this.encodedPassword=password;
        this.nickname=nickname;
        this.nickname=nickname;
        this.studentId=studentId;
    }

    public void withdrawMember(){
        this.status=MemberStatus.UNREGISTERED;
    }


}