package efub.assignment.community.post.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import efub.assignment.community.post.dto.PostRequestDto;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    private final MemberService memberService;

    @Transactional
    public Post addPost(PostRequestDto requestDto){
        Member writer=memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));

        Board board=boardRepository.findById(requestDto.getBoardId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판입니다."));

        return postRepository.save(
                Post.builder()
                        .board(board)
                        .writer(writer)
                        .writerShow(requestDto.isWriterShow())
                        .content(requestDto.getContent())
                        .build()
        );
    }

    public List<Post> findPostList() {
        return postRepository.findAll();
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public void removePost(Long postId, Long memberId) {
        Post post=postRepository.findByPostIdAndWriter_MemberId(postId,memberId)
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        postRepository.delete(post);
    }


    public Post modifyPost(Long postId, PostModifyRequestDto requestDto) {
        Post post=postRepository.findByPostIdAndWriter_MemberId(postId,requestDto.getWriterId())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        post.updatePost(requestDto);
        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> findPostListByWriter(Long memberId) {
        Member writer = memberService.findMemberById(memberId);
        return postRepository.findAllByWriter(writer);
    }
}
