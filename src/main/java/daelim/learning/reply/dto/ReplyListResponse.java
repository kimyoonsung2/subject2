package daelim.learning.reply.dto;
import daelim.learning.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReplyListResponse {

    private String comment;
    private String writer;
    private Long replyNo;


    @Builder
    public ReplyListResponse(String comment, User user, Long replyNo) {
        this.comment = comment;
        this.writer = user.getUserName();
        this.replyNo = replyNo;
    }
}
