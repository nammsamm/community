package efub.assignment.community.message.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.MessageFindRequestDto;
import efub.assignment.community.message.dto.MessageRequestDto;
import efub.assignment.community.message.repository.MessageRepository;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageroom.repository.MessageRoomRepository;
import efub.assignment.community.messageroom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageRoomRepository messageRoomRepository;

    private final MessageRoomService messageRoomService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 쪽지방에 쪽지 생성 -> 보내는 사람 ID 가 messageRoom 에 포함되는지도 해보면 좋을 것 같다.
    public Long addMessage(Long messageRoomId, MessageRequestDto requestDto) {
        MessageRoom messageRoom=messageRoomService.findMessageRoomById(messageRoomId);
        Member sender=memberService.findMemberById(requestDto.getSenderId());

        return messageRepository.save(requestDto.toEntity(messageRoom,sender)).getMessageId();
    }

    // ID 로 메시지 조회
    @Transactional(readOnly = true)
    public Message findMessageById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 쪽지입니다."));

    }

    // 쪽지방의 모든 메시지 조회
    @Transactional(readOnly = true)
    public List<Message> findMessageList(Long messageRoomId) {
        MessageRoom messageRoom=messageRoomService.findMessageRoomById(messageRoomId);
        return messageRepository.findAllByMessageRoom(messageRoom);
    }

    // 메시지를 보낸 것인지 보내지 않은 것인지 조회
    public List<Boolean> isReceived(Long finderId, List<Message> messageList) {
        List<Boolean> received=new ArrayList<>();
        for(int i=0;i<messageList.size();i++){
            if(finderId==messageList.get(i).getSender().getMemberId()){
                received.add(false);
            }
            else{
                received.add(true);
            }
        }
        return received;
    }


    //
    public List<MessageFindRequestDto> createFindRequest(Long finderID,MessageRoom messageRoom,List<Boolean> received, List<Message> messageList) {
        List<MessageFindRequestDto> messageFindRequestDtoList=new ArrayList<>();
        Boolean firstMessageReceived;
        if(finderID==messageRoom.getStarter().getMemberId()){
            firstMessageReceived=false;
        }
        else{
            firstMessageReceived=true;
        }
        MessageFindRequestDto firstDto=new MessageFindRequestDto(firstMessageReceived,messageRoom.getFirstMessage(),messageRoom.getCreatedDate());
        messageFindRequestDtoList.add(firstDto);
        for(int i=0;i<messageList.size();i++){
            MessageFindRequestDto requestDto=new MessageFindRequestDto(received.get(i),messageList.get(i).getContent(),messageList.get(i).getCreatedDate());
            messageFindRequestDtoList.add(requestDto);
        }

        return messageFindRequestDtoList;


    }
}
