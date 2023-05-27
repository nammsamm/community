package efub.assignment.community.member.controller;

import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.dto.MessageRoomListResponseDto;
import efub.assignment.community.messageroom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/messageRooms")
public class MemberMessageRoomController {

    private final MessageRoomService messageRoomService;

    // 쪽지방 목록 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public MessageRoomListResponseDto findMessageRoomList(@PathVariable Long memberId){

        List<MessageRoom> messageRoomList=messageRoomService.findMessageRoomList(memberId);
        return MessageRoomListResponseDto.of(memberId,messageRoomList);

    }
}
