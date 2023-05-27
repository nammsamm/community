package efub.assignment.community.messageroom.repository;


import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
요청 내용 : 조회하는 사람의 id, 받는 사람의 id, 시작 게시글의 id
 */
public interface MessageRoomRepository extends JpaRepository<MessageRoom,Long> {
    boolean existsByStarterAndReceiverAndPost(Member finder, Member receiver, Post post);
    boolean existsByReceiverAndStarterAndPost(Member finder,Member receiver,Post post);

    Optional<MessageRoom> findByStarterAndReceiverAndPost(Member finder, Member receiver, Post post);
    Optional<MessageRoom> findByReceiverAndStarterAndPost(Member finder, Member receiver, Post post);

    List<MessageRoom> findAllByStarterOrReceiver(Member finder,Member receiver);
}
