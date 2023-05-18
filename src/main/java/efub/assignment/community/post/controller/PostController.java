package efub.assignment.community.post.controller;

import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.heart.dto.HeartRequestDto;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import efub.assignment.community.post.dto.PostRequestDto;
import efub.assignment.community.post.dto.PostResponseDto;
import efub.assignment.community.post.service.PostHeartSerrvice;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/community-posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostHeartSerrvice postHeartSerrvice;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto){
        Post post = postService.addPost(requestDto);
        return new PostResponseDto(post);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind(){
        List<Post> postList=postService.findPostList();
        List<PostResponseDto> responseDtoList=new ArrayList<>();

        for(Post post:postList){
            responseDtoList.add(new PostResponseDto(post));
        }

        return responseDtoList;
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postFind(@PathVariable Long postId){
        Post post=postService.findPost(postId);
        return new PostResponseDto(post);
    }


    @DeleteMapping("/{postId}/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @PathVariable Long memberId){
        postService.removePost(postId,memberId);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto){
        Post post=postService.modifyPost(postId,requestDto);
        return new PostResponseDto(post);
    }

    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String  createPostHeart(@PathVariable final Long postId, @RequestBody final HeartRequestDto requestDto){
        postHeartSerrvice.create(postId,requestDto.getMemberId());
        return "좋아요를 눌렀습니다.";
    }

    @DeleteMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePostHeart(@PathVariable final Long postId, @RequestParam final  Long memberId){
        postHeartSerrvice.delete(postId,memberId);
        return "좋아요가 취소되었습니다.";
    }



}
