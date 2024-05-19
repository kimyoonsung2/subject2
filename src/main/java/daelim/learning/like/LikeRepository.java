package daelim.learning.like;

import daelim.learning.board.Board;
import daelim.learning.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    Likes findByUserAndBoard(User user, Board board);

    @Query("SELECT l.board.boardNo FROM Likes l WHERE l.user.userNo = :userNo")
    List<Long> findByUserNo(@Param("userNo") Long userNo);
}
