package efub.assignment.community.message.domain;

import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private java.lang.Long messageId;


    //여러개의 쪽지가 하나의 쪽지방에서 오고감.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messageRoom")
    private MessageRoom messageRoom;


    //멤버는 여러개의 쪽지를 보낼 수 있음.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    // 쪽지 내용
    @Column
    private String content;

    @Builder
    public Message(MessageRoom messageRoom, Member sender, String content){
        this.messageRoom=messageRoom;
        this.sender=sender;
        this.content=content;
    }

}
