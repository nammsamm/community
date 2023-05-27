package efub.assignment.community.messageroom.domain;


import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_room_id")
    private Long messageRoomId;


    // 여러 쪽지방의 sender 일 수 있음..?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "starter_id")
    private Member starter;


    // 여러 쪽지방의 receiver 일 수 있음..?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    // 첫 번째 쪽지
    @Column(name = "first_message")
    private String firstMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 하나의 쪽지방에는 여러개의 쪽지가 오고감.
    @OneToMany(mappedBy = "messageRoom",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Message> messageList=new ArrayList<>();

    @Builder
    public MessageRoom(Member starter,Member receiver,Post post,String firstMessage){
        this.starter =starter;
        this.receiver=receiver;
        this.post=post;
        this.firstMessage=firstMessage;
    }




}
