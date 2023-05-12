package efub.assignment.community.board.domain;

import efub.assignment.community.board.dto.BoardModifyRequestDto;
import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity // 테이블과의 매핑
@NoArgsConstructor // 파라미터가 없는 기몬 생성자 생성
@Getter
public class Board extends BaseTimeEntity {
    @Id // 테이블의 PK 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 전략을 정의
    private Long boardId;

    @Column
    private String boardName;

    @Column
    private String intro;

    @Column
    private String notice;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member boardOwner;

    //일단 Enum은 제외하고 한다..

    @Builder // 객체를 생성하는 빌더 패턴.
    public Board(Long boardId,String boardName,String intro,String notice,Member boardOwner){
        this.boardId=boardId;
        this.boardName=boardName;
        this.intro=intro;
        this.notice=notice;
        this.boardOwner=boardOwner;
    }

    public void updateBoard(Member boardOwner) {
        this.boardOwner=boardOwner;
    }


}
