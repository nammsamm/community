package efub.assignment.community.member.domain;

import efub.assignment.community.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/*
 테스트코드 작성하는 방법 더 찾아보고... 이름이라던가 더 정리하기! 방식 같은 것들...
 */

/* 물리적 DB인 MySQL 을 이용하기 위하여 AutoConfigureTestDatabase.Replace.NONE 설정 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;


    /* 성공 : Member 객체 생성을 성공하는 경우 */
    @Test
    public void testMember(){
        /* given */
        String email = "efubTest@gmail.com";
        String password = "1234";
        String nickname = "efub";
        Integer studentId = 2171012;
        String university = "Ewha Womens University";

        /* when */
        Member member = new Member(email,password,nickname,studentId,university);

        /* then */
        assertNotNull(member);
    }


    /* 실패 : 존재하지 않는 계정을 휴면 계정으로 전환하려 하는 경우 */
    @Test
    public void withdrawMember_givenInvalidMember_ReturnNullPointerException(){
        /* given */
        final Long INVALID_MEMBER_ID = 10L;
        final String NULL_POINT_EXCEPTION = null;

        /* when , then */
        Member member1 = memberRepository.findMemberByMemberId(INVALID_MEMBER_ID);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> member1.withdrawMember());
        assertThat(e.getMessage()).isEqualTo(NULL_POINT_EXCEPTION);
    }


    /* 실패 : 존재하지 않는 계정에 대해 닉네임을 변경하려 하는 경우 */
    @Test
    public void updateNickname_givenInvalidMember_ReturnNullPointerException(){
        /* given */
        final Long INVALID_MEMBER_ID = 10L;
        String nickname = "testNickname";

        /* when, then */
        Member member = memberRepository.findMemberByMemberId(INVALID_MEMBER_ID);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> member.updateNickName(nickname));
        assertThat(e.getMessage()).isEqualTo(null);
    }
}