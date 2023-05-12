package efub.assignment.community.member.controller;

import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostListResponseDto;
import efub.assignment.community.post.dto.PostResponseDto;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/posts")
public class MemberPostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListResponseDto readMemberPosts(@PathVariable Long memberId){
        List<Post> postList = postService.findPostListByWriter(memberId);
        return PostListResponseDto.of(postList);
    }
}
