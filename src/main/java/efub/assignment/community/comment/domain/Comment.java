package efub.assignment.community.comment.domain;

import efub.assignment.community.comment.dto.CommentModifyRequestDto;
import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //객체를 테이블(엔티티)와 매핑
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성, protected 접근 권한 사용
public class Comment extends BaseTimeEntity {
    @Id // PK 컬럼 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 전략 설정 : AUTO INCREMENT
    private Long commentId;

    @Column(nullable = false , length = 500) // 컬럼 설정.
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 연관관계 매핑.
    @JoinColumn(name = "post_id",nullable = false,updatable = false) // FK 컬럼 지정.
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false,updatable = false)
    private Member writer;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL,orphanRemoval = true)
    List<CommentHeart> commentHeartList=new ArrayList<>();

    @Builder
    public Comment(String content,Post post,Member writer){
        this.content=content;
        this.post=post;
        this.writer=writer;
    }

    public void updateComment(CommentModifyRequestDto requestDto) {
        this.content=requestDto.getContent();
    }
}
