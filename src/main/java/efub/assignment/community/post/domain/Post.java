package efub.assignment.community.post.domain;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
{
		"boardName": "벗들의 맛집",
		"writter" : "200"
		"writterShow" : "true",
		"content" : "낭만식탁 맛조항요~",
		"created": "2023-03-24",
		"updated": "2023-03-24"
		"postID" : "20"
}
 */
@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column
    private boolean writerShow;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String content;

    // mappedBy : 연관 관계의 주인(Owner)
    // cascade : 엔티티 삭제 시 연관된 엔티티의 처리 방식
    // orphanRemoval : 고아 객체의 처리 방식. -> true : 고아 객체가 되면 자식 엔티티도 삭제.
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> commentList=new ArrayList<>();


    //게시글 좋아요
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PostHeart> postHeartList=new ArrayList<>();


    @Builder
    public Post(Board board,Member writer,boolean writerShow,String content){
        this.board=board;
        this.writer=writer;
        this.writerShow=writerShow;
        this.content=content;
    }


    public void updatePost(PostModifyRequestDto requestDto) {
        this.content=requestDto.getContent();
    }
}
