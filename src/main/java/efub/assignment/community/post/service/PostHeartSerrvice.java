package efub.assignment.community.post.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.domain.PostHeart;
import efub.assignment.community.post.repository.PostHeartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostHeartSerrvice {
    private final PostHeartRepository postHeartRepository;
    private final PostService postService;
    private final MemberService memberService;


    //게시글 좋아요 생성
    public void create(Long postId, Long memberId){
        Member member=memberService.findMemberById(memberId);
        Post post=postService.findPost(postId);

        if(isExistsByWriterAndPost(member,post)){
            throw  new RuntimeException("이미 좋아요를 누르 게시물입니다.");
        }

        PostHeart postHeart=PostHeart.builder()
                .post(post)
                .member(member)
                .build();
        postHeartRepository.save(postHeart);
    }

    //게시글 좋아요 삭제
    public void delete(Long postId, Long memberId){
        Post post= postService.findPost(postId);
        Member member=memberService.findMemberById(memberId);
        PostHeart postHeart = postHeartRepository.findByWriterAndPost(member,post)
                .orElseThrow(()->new RuntimeException("좋아요가 존재하지 않습니다."));
        postHeartRepository.delete(postHeart);
    }

    //게시글 존재하는가?
    public boolean isHeart(Long memberId, Post post){
        Member member=memberService.findMemberById(memberId);
        return isExistsByWriterAndPost(member,post);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByWriterAndPost(Member member,Post post){
        return postHeartRepository.existsByWriterAndPost(member,post);
    }

    //게시글 좋아요 count
    @Transactional(readOnly = true)
    public Integer countPostHeart(Post post){
        Integer count=postHeartRepository.countByPost(post);
        return count;
    }

}
