package efub.assignment.community.board.repository;

import efub.assignment.community.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Long> {

    Optional<Board> findByBoardIdAndBoardOwner_MemberId(Long boardId, Long memberId);

}
