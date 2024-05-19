package daelim.learning.user.dto;


import daelim.learning.board.Board;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyBoardListResponse {
    private String title;
    private Long boardNo;
    private Date createdAt;
    private String dueDate;

    public MyBoardListResponse(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.createdAt = board.getCreatedAt();
        this.dueDate = board.getDueDate();
    }
}
