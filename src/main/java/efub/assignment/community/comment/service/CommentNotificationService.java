package efub.assignment.community.comment.service;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.domain.CommentNotification;
import efub.assignment.community.comment.repository.CommentNotificationRepository;
import efub.assignment.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentNotificationService {
    private final CommentService commentService;
    private final CommentNotificationRepository commentNotificationRepository;
    private final MemberService memberService;

    // 댓글 생성 알림
    public String create(Long commentId) {
        Comment comment=commentService.findCommentById(commentId);
        CommentNotification commentNotification=CommentNotification.builder()
                .comment(comment)
                .build();
        commentNotificationRepository.save(commentNotification);
        return comment.getPost().getBoard().getBoardName()+", 새로운 댓글이 달렸어요! :"+comment.getContent()+" 시간: "+comment.getCreatedDate();
    }
}
