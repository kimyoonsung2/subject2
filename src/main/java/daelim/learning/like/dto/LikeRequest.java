package daelim.learning.like.dto;

import daelim.learning.board.Board;
import daelim.learning.like.Likes;
import daelim.learning.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeRequest {
    private Long userNo;
    private Long boardNo;

    public Likes toEntity(User user, Board board) {
        return Likes.builder()
                .board(board)
                .user(user)
                .build();
    }
}
