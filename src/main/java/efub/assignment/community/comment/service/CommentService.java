package efub.assignment.community.comment.service;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.CommentModifyRequestDto;
import efub.assignment.community.comment.dto.CommentRequestDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor // 필수 필드를 매게변수로 갖는 생성자 생성 : DI 컨테이너가 생성자를 통해 DI를 수행.
public class CommentService {

    // 의존관계를 주입 받을 필드에 final 키워드
    private final CommentRepository commentRepository;

    private final PostService postService;
    private final MemberService memberService;

    // 댓글 작성(생성)
    public Long createComment(Long postId, CommentRequestDto requestDto){
        Post post=postService.findPost(postId);
        Member writer = memberService.findMemberById(requestDto.getMemberId());

        return commentRepository.save(requestDto.toEntity(post,writer)).getCommentId();
    }

    // 댓글 조회 - ID 별
    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 댓글입니다. ID ="+commentId));
    }

    // 댓글 목록 조회 - 작성자별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByWriter(Member writer){
        return commentRepository.findAllByWriter(writer);
    }

    // 댓글 목록 조회 - 게시글별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByPost(Long postId){
        Post post=postService.findPost(postId);
        return commentRepository.findAllByPost(post);
    }

    // 댓글 수정
    public Comment modifyComment(Long commentId, CommentModifyRequestDto requestDto){
        Comment comment= commentRepository.findByCommentIdAndWriter_MemberId(commentId,requestDto.getMemberId())
                .orElseThrow(()-> new IllegalArgumentException("잘못된 접근입니다."));
        comment.updateComment(requestDto);
        return comment;

    }



}
