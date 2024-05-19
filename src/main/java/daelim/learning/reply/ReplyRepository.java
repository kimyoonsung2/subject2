package daelim.learning.reply;

import daelim.learning.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByBoardNoBoardNo(Long boardNo);
}
