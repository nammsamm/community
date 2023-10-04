package efub.assignment.community.post.domain;

import efub.assignment.community.post.dto.PostModifyRequestDto;
import efub.assignment.community.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/* 물리적 DB인 MySQL 을 이용하기 위하여 AutoConfigureTestDatabase.Replace.NONE 설정 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class PostTest {
    @Autowired
    public PostRepository postRepository;

    /* 성공 : 게시글 내용 수정 */
    @Test
    public void updatePostTest(){
        /* given */
        Long postId = 6L;
        Long memberId = 3L;
        String content = "게시글 수정 테스트입니다.";

        /* when */
        PostModifyRequestDto requestDto = new PostModifyRequestDto(memberId,content);
        Post post = postRepository.findPostByPostId(postId);
        post.updatePost(requestDto);

        /* then */
        assertThat(post.getContent()).isEqualTo(content);
    }

    /* 실패 : 존재하지 않는 게시글에 대해 수정 */
    @Test
    public void updatePost_GivenInvalidPost_ReturnNullPointerException(){
        /* given */
        final Long INVALID_POST_ID = 20L;
        Long memberId = 3L;
        String content = "게시글 수정 테스트입니다.";

        /* when , then */
        PostModifyRequestDto requestDto = new PostModifyRequestDto(memberId,content);
        Post post = postRepository.findPostByPostId(INVALID_POST_ID);
        NullPointerException e = assertThrows(NullPointerException.class ,
                ()-> post.updatePost(requestDto));
        assertThat(e.getMessage()).isEqualTo(null);
    }

    /* 실패 : 게시글의 내용을 null 로 수정하려 하는 경우 */
    @Test
    public void updatePost_GivenNullContent_ReturnNull(){
        /* given */
        Long postId= 6L;
        Long memberId = 3L;
        String content = null;

        /* when , then */
        PostModifyRequestDto requestDto = new PostModifyRequestDto(memberId,content);
        Post post = postRepository.findPostByPostId(postId);
        post.updatePost(requestDto);
        assertThat(post.getContent()).isNull();
    }
}