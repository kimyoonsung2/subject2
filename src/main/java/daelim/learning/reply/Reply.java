package daelim.learning.reply;

import daelim.learning.board.Board;
import daelim.learning.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "tb_reply")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_no")
    private Long replyNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_no")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board boardNo;

    @Lob
    @Column(nullable = false, name = "comment")
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
