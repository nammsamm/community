package efub.assignment.community.messageroom.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.dto.MessageRoomFindRequestDto;
import efub.assignment.community.messageroom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageroom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageRoomService {
    private final MemberRepository memberRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final PostRepository postRepository;

    //쪽지방 생성
    @Transactional
    public MessageRoom createMessageRoom(MessageRoomRequestDto requestDto) {
        Member starter=memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        Member receiver=memberRepository.findById(requestDto.getReceiverId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        Post post=postRepository.findById(requestDto.getPostId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));


        return messageRoomRepository.save(
                MessageRoom.builder()
                        .starter(starter)
                        .receiver(receiver)
                        .post(post)
                        .firstMessage(requestDto.getFirstMessage())
                        .build()
        );
    }


    // 쪽지방 조회: 조회하는 사람의 id, 받는 사람의 id, 시작 게시글의 id
    public Long findMessageRoom(MessageRoomFindRequestDto requestDto) {
        Member finder=memberRepository.findById(requestDto.getFinderId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        Member receiver=memberRepository.findById(requestDto.getReceiverId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        Post post=postRepository.findById(requestDto.getPostId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));

        //조회하는 계정이 쪽지방 생성 계정이 아닐 수도 있지 않을까?
        if(messageRoomRepository.existsByReceiverAndStarterAndPost(finder,receiver,post)){
            MessageRoom findMessageRoom= messageRoomRepository.findByReceiverAndStarterAndPost(finder,receiver,post)
                    .orElseThrow(()->new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
            return findMessageRoom.getMessageRoomId();
        }
        else if(messageRoomRepository.existsByStarterAndReceiverAndPost(finder,receiver,post)){
            MessageRoom findMessageRoom= messageRoomRepository.findByStarterAndReceiverAndPost(finder,receiver,post)
                    .orElseThrow(()->new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
            return findMessageRoom.getMessageRoomId();
        }
        else{
            return 0L;
        }

    }

    // 메시지룸 ID 로 메시지룸 찾기
    @Transactional(readOnly = true)
    public MessageRoom findMessageRoomById(Long messageRoomId){
        return messageRoomRepository.findById(messageRoomId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 쪽지방입니다."));
    }

    // 쪽지방 목록 조회 : 조회하는 사람 Id
    @Transactional(readOnly = true)
    public List<MessageRoom> findMessageRoomList(Long memberId) {
        Member member=memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        return messageRoomRepository.findAllByStarterOrReceiver(member,member);
    }

    // 쪽지방 삭제
    public void removeMessageRoom(Long messageRoomId) {
        MessageRoom messageRoom=messageRoomRepository.findById(messageRoomId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        messageRoomRepository.delete(messageRoom);
    }

    // 쪽지방의 상대 ID 조회
    public Long findMessageRoomOtherMemberId(Long messageRoomId,Long finderId) {
        MessageRoom messageRoom=messageRoomRepository.findById(messageRoomId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 쪽지방입니다."));
        if(messageRoom.getStarter().getMemberId()==finderId){
            return messageRoom.getReceiver().getMemberId();
        }
        else{
            return messageRoom.getStarter().getMemberId();
        }


    }

}
