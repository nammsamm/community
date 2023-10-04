package efub.assignment.community.member.repository;

import efub.assignment.community.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/* 물리적 DB인 MySQL 을 이용하기 위하여 AutoConfigureTestDatabase.Replace.NONE 설정 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    /* 성공 : 존재하는 memberId로 member 조회 */
    @Test
    public void findMemberByMemberId_GivenValidId_ReturnId(){
        /* given */
        final Long VALID_MEMBER_ID = 3L;

        /* when , then */
        assertThat(memberRepository.findMemberByMemberId(VALID_MEMBER_ID).getMemberId()).isEqualTo(VALID_MEMBER_ID);
    }

    /* 실패 : 존재하지 않는 memberId로 member 조회 */
    @Test
    public void findMemberByMemberId_GivenInvalidId_ReturnNull(){
        /* given */
        final Long INVALID_MEMBER_ID = 6L;
        final String NULL_POINT_EXCEPTION = null;

        /* when, then */
        Member member1 = memberRepository.findMemberByMemberId(INVALID_MEMBER_ID);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> member1.withdrawMember());
        assertThat(e.getMessage()).isEqualTo(NULL_POINT_EXCEPTION);
    }


}