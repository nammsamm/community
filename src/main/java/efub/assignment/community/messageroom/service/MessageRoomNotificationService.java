//package efub.assignment.community.messageroom.service;

import efub.assignment.community.messageroom.domain.MessageRoom;
import efub.assignment.community.messageroom.domain.MessageRoomNotification;
import efub.assignment.community.messageroom.repository.MessageRoomNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageRoomNotificationService {
    private final MessageRoomService messageRoomService;
    private final MessageRoomNotificationRepository messageRoomNotificationRepository;

    public void create(Long messageRoomId){
        MessageRoom messageRoom=messageRoomService.findMessageRoomById(messageRoomId);
        String messageRoomNotificationContent="쪽지방, 새로운 쪽지방이 생겼어요"+messageRoom.getCreatedDate();

        MessageRoomNotification messageRoomNotification=MessageRoomNotification.builder()
                        .messageRoom(messageRoom)
                                .content(messageRoomNotificationContent)
                                        .build();

        messageRoomNotificationRepository.save(messageRoomNotification);
    }
}

 */
