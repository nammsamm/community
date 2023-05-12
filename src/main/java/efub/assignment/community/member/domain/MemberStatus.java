package efub.assignment.community.member.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //final 이나 @NotNull이 붙은 필드의 생성자를 자동 생성.
public enum MemberStatus {
    REGISTERED(0,"등록 상태","사용자 등록상태"),
    UNREGISTERED(1,"해지","사용자 해지상태");

    private final Integer id;
    private final String title;
    private final String description;
}
