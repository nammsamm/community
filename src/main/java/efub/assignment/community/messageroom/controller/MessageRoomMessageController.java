package efub.assignment.community.messageroom.controller;

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.MessageFindRequestDto;
import efub.assignment.community.message.dto.MessageRequestDto;
import efub.assignment.community.message.dto.MessageResponseDto;
import efub.assignment.community.message.service.MessageService;
import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.dto.MessageRoomMessageResponseDto;
import efub.assignment.community.messageroom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message-rooms/{messageRoomId}/messages")
public class MessageRoomMessageController {
    private final MessageService messageService;
    private final MessageRoomService messageRoomService;

    // 쪽지방에 쪽지 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createMessage(@PathVariable Long messageRoomId , @RequestBody @Valid MessageRequestDto requestDto){
        Long messageId = messageService.addMessage(messageRoomId,requestDto);
        Message message=messageService.findMessageById(messageId);
        return MessageResponseDto.of(message);
    }

    // 쪽지방의 모든 쪽지 조회
    @GetMapping("/{finderId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageRoomMessageResponseDto readMessageRoomMessages(@PathVariable Long messageRoomId, @PathVariable Long finderId){

        MessageRoom messageRoom=messageRoomService.findMessageRoomById(messageRoomId);
        List<Message> messageList=messageService.findMessageList(messageRoomId);

        Long otherUserId=messageRoomService.findMessageRoomOtherMemberId(messageRoomId,finderId);
        List<Boolean> received=messageService.isReceived(finderId,messageList);
        List<MessageFindRequestDto> requestDtoList=messageService.createFindRequest(finderId,messageRoom,received,messageList);

        return MessageRoomMessageResponseDto.of(messageRoomId,otherUserId,requestDtoList);
    }

}
