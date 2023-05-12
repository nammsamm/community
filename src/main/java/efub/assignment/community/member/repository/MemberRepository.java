package efub.assignment.community.member.repository;

import efub.assignment.community.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//Spring Data JPA : JpaRepository<엔티티타입,식별자타입>을 상속하여 사용한다.
public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByEmail(String email); // email을 이용하여 찾겠다.
}
