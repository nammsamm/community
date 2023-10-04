package efub.assignment.community.post.repository;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByPostIdAndWriter_MemberId(Long postId,Long memberID);

    List<Post> findAllByWriter(Member writer);
    Post findPostByPostId(Long postId);


}
