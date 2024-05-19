package daelim.learning.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByViewCountDesc();
    List<Board> findAllByOrderByCreatedAtDesc();
    Board findByBoardNo(Long boardNo);
    List<Board> findByWriterUserNoOrderByCreatedAtDesc(Long userNo);

    List<Board> findAllByBoardNoIn(List<Long> likedBoardIds);
}
