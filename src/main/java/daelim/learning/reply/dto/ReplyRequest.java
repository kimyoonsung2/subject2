package daelim.learning.reply.dto;

import daelim.learning.board.Board;
import daelim.learning.reply.Reply;
import daelim.learning.user.User;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyRequest {

    private String comment;

    public Reply toEntity(Board board, User user) {
        return Reply.builder()
                .comment(comment)
                .userNo(user)
                .boardNo(board)
                .build();
    }
}